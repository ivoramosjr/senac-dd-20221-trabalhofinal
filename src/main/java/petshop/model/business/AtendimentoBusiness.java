package petshop.model.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.HorarioJaMarcadoException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.dao.AtendimentoDAO;
import petshop.model.dao.PetDAO;
import petshop.model.dao.ServicoDAO;
import petshop.model.dtos.AtendimentoDTO;
import petshop.filtros.FiltroAtendimento;
import petshop.model.entity.Atendimento;
import petshop.model.entity.Pet;
import petshop.model.entity.Servico;

import javax.enterprise.context.ApplicationScoped;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AtendimentoBusiness {

    private static Logger LOG = LogManager.getLogger(AtendimentoBusiness.class);

    private AtendimentoDAO atendimentoDAO;

    private PetDAO petDAO;

    private ServicoDAO servicoDAO;

    public AtendimentoBusiness(){
        this.atendimentoDAO = new AtendimentoDAO();
        this.petDAO = new PetDAO();
        this.servicoDAO = new ServicoDAO();
    }

    public void save(AtendimentoDTO atendimentoDTO) throws SQLException, AtributosInvalidosException, RegistroNaoEncontradoException, HorarioJaMarcadoException {
        LOG.info("Preparando para salvar o atendimento");

        validarAtributos(atendimentoDTO);

        verificarPetServico(atendimentoDTO);

        verificarHorarioAtendimento(atendimentoDTO);

        Atendimento atendimento = new Atendimento();
        setAtributosAtendimento(atendimentoDTO, atendimento);

        abrirConexaoBanco();

        Pet pet = petDAO.find(Pet.class, atendimentoDTO.getPet().getIdPet());
        Servico servico = servicoDAO.find(Servico.class, atendimentoDTO.getServico().getIdServico());

        atendimento.setPet(pet);
        atendimento.setServico(servico);
        atendimentoDAO.save(atendimento);
        commitarTransacaoBanco();

        LOG.info("Atendimento salvo com sucesso!");
    }

    public void update(Long idAtendimento, AtendimentoDTO atendimentoDTO) throws RegistroNaoEncontradoException, HorarioJaMarcadoException, SQLException {
        LOG.info("Preparando para atualizar o atendimento");

        if(!atendimentoDAO.atendimentoExist(idAtendimento)){
            throw new RegistroNaoEncontradoException("Atendimento de ID: "
                    +idAtendimento+" não encontrado na base de dados!");
        }

        verificarPetServico(atendimentoDTO);

        verificarHorarioAtendimento(atendimentoDTO);

        Atendimento atendimento = atendimentoDAO.find(Atendimento.class, atendimentoDTO.getIdAtendimento());
        setAtributosAtendimento(atendimentoDTO, atendimento);

        abrirConexaoBanco();

        Pet pet = petDAO.find(Pet.class, atendimentoDTO.getPet().getIdPet());
        Servico servico = servicoDAO.find(Servico.class, atendimentoDTO.getServico().getIdServico());

        atendimento.setPet(pet);
        atendimento.setServico(servico);
        atendimentoDAO.merge(atendimento);
        commitarTransacaoBanco();

        LOG.info("Atendimento atualizado com sucesso!");
    }

    public List<AtendimentoDTO> listAll(){
        LOG.info("Procurando todos os atendimentos cadastrados");
        List<Atendimento> atendimentosEntity = this.atendimentoDAO.findAll();
        List<AtendimentoDTO> atendimentos = new ArrayList<>();

        atendimentos = atendimentosEntity.stream().map(atendimento -> new AtendimentoDTO(atendimento)).collect(Collectors.toList());

        LOG.info("Foram encontrados "+atendimentos.size()+" atendimentos.");

        return atendimentos;
    }

    public List<AtendimentoDTO> findWithFilter(FiltroAtendimento filtro){
        LOG.info("Preparando para pesquisar os atendimentos com filtro");

        List<Atendimento> atendimentosEntity = this.atendimentoDAO.findWithFilter(filtro);

        List<AtendimentoDTO> atendimentos = atendimentosEntity.stream()
                .map(a -> new AtendimentoDTO(a))
                .collect(Collectors.toList());

        return atendimentos;

    }

    private void verificarHorarioAtendimento(AtendimentoDTO atendimentoDTO) throws HorarioJaMarcadoException {
        LOG.info("Verificando se horário do atendimento é valido");
        boolean horarioJaSelecionada = atendimentoDAO.horarioEstaMarcado(atendimentoDTO.getIdAtendimento(),atendimentoDTO.getDataAtendimento());

        if(horarioJaSelecionada)
            throw new HorarioJaMarcadoException(atendimentoDTO.getDataAtendimento());
    }

    private void setAtributosAtendimento(AtendimentoDTO atendimentoDTO, Atendimento atendimento) {
        if(atendimentoDTO.getDataAtendimento() != null){
            atendimento.setDataAtendimento(atendimentoDTO.getDataAtendimento());
        }

        if(atendimentoDTO.getStatusAtendimento() != null){
            atendimento.setStatusAtendimento(atendimentoDTO.getStatusAtendimento());
        }
    }

    private void verificarPetServico(AtendimentoDTO atendimentoDTO) throws RegistroNaoEncontradoException {
        LOG.info("Verificando se o Pet e o Serviço existem na base de dados");
        if(!petDAO.petExists(atendimentoDTO.getPet().getIdPet())){
            throw new RegistroNaoEncontradoException("Pet com ID "
                    +petDAO.petExists(atendimentoDTO.getPet().getIdPet())
                    +" não existe na base de dados!");
        }

        if(!servicoDAO.servicoExists(atendimentoDTO.getServico().getIdServico())){
            throw new RegistroNaoEncontradoException("Serviço com ID "
                    +servicoDAO.servicoExists(atendimentoDTO.getServico().getIdServico())
                    +" não existe na base de dados!");
        }
    }

    private void validarAtributos(AtendimentoDTO atendimentoDTO) throws AtributosInvalidosException {
        LOG.info("Validando os atributos do Atendimento");
        String messages = "";

        if(atendimentoDTO.getPet() == null || atendimentoDTO.getPet().getIdPet() == null){
            messages = messages.concat("Por favor selecione um pet!\n");
        }

        if(atendimentoDTO.getServico() == null || atendimentoDTO.getServico().getIdServico() == null){
            messages = messages.concat("Por favor selecione um serviço!\n");
        }

        if(atendimentoDTO.getDataAtendimento() == null || atendimentoDTO.getDataAtendimento().isBefore(LocalDateTime.now())){
            messages = messages.concat("Por favor selecione uma data e horário válida!");
        }

        if(!messages.isEmpty()){
            LOG.error("Erro ao validar os atributos. Algum atributo inválido!\n");
            throw new AtributosInvalidosException(messages);
        }
    }

    private void commitarTransacaoBanco() {
        this.atendimentoDAO.getEntityManager().getTransaction().commit();
    }

    private void abrirConexaoBanco() {
        this.atendimentoDAO.getEntityManager().getTransaction().begin();
    }
}
