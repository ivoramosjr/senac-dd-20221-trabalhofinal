package petshop.model.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.HorarioJaMarcadoException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.filtros.FiltroRelatorioAtendimento;
import petshop.model.dao.AtendimentoDAO;
import petshop.model.dao.PetDAO;
import petshop.model.dao.ServicoDAO;
import petshop.filtros.FiltroAtendimento;
import petshop.model.dtos.request.AtendimentoRequestDTO;
import petshop.model.dtos.response.AtendimentoRegistroDTO;
import petshop.model.dtos.response.AtendimentoResponseListagemDTO;
import petshop.model.dtos.response.RelatorioAtendimentoDTO;
import petshop.model.entity.Atendimento;
import petshop.model.entity.Pet;
import petshop.model.entity.Servico;

import javax.enterprise.context.ApplicationScoped;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

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

    public void save(AtendimentoRequestDTO atendimentoDTO) throws SQLException, AtributosInvalidosException, RegistroNaoEncontradoException, HorarioJaMarcadoException {
        LOG.info("Preparando para salvar o atendimento");

        validarAtributos(atendimentoDTO);

        verificarPetServico(atendimentoDTO);

        verificarHorarioAtendimento(atendimentoDTO);

        Atendimento atendimento = new Atendimento();
        setAtributosAtendimento(atendimentoDTO, atendimento);

        abrirConexaoBanco();

        Pet pet = petDAO.find(Pet.class, atendimentoDTO.getPetIdPet());
        Servico servico = servicoDAO.find(Servico.class, atendimentoDTO.getServicoIdServico());

        atendimento.setPet(pet);
        atendimento.setServico(servico);
        atendimentoDAO.save(atendimento);
        commitarTransacaoBanco();

        LOG.info("Atendimento salvo com sucesso!");
    }

    public List<AtendimentoResponseListagemDTO> listAll(){
        LOG.info("Procurando todos os atendimentos cadastrados");
        List<AtendimentoResponseListagemDTO> atendimentos = this.atendimentoDAO.findAll();

        return atendimentos;
    }

    public List<AtendimentoResponseListagemDTO> findWithFilter(FiltroAtendimento filtro){
        LOG.info("Preparando para pesquisar os atendimentos com filtro");

        List<AtendimentoResponseListagemDTO> atendimentos = this.atendimentoDAO.findWithFilter(filtro);

        return atendimentos;

    }

    private void verificarHorarioAtendimento(AtendimentoRequestDTO atendimentoDTO) throws HorarioJaMarcadoException {
        LOG.info("Verificando se horário do atendimento é valido");
        boolean horarioJaSelecionada = atendimentoDAO.horarioEstaMarcado(atendimentoDTO.getIdAtendimento(),atendimentoDTO.getDataAtendimento());

        if(horarioJaSelecionada)
            throw new HorarioJaMarcadoException(atendimentoDTO.getDataAtendimento());
    }

    private void setAtributosAtendimento(AtendimentoRequestDTO atendimentoDTO, Atendimento atendimento) {
        if(atendimentoDTO.getDataAtendimento() != null){
            atendimento.setDataAtendimento(atendimentoDTO.getDataAtendimento());
        }

        if(atendimentoDTO.getStatusAtendimentoEnum() != null){
            atendimento.setStatusAtendimento(atendimentoDTO.getStatusAtendimentoEnum());
        }
    }

    private void verificarPetServico(AtendimentoRequestDTO atendimentoDTO) throws RegistroNaoEncontradoException {
        LOG.info("Verificando se o Pet e o Serviço existem na base de dados");
        if(!petDAO.petExists(atendimentoDTO.getPetIdPet())){
            throw new RegistroNaoEncontradoException("Pet com ID "
                    +petDAO.petExists(atendimentoDTO.getPetIdPet())
                    +" não existe na base de dados!");
        }

        if(!servicoDAO.servicoExists(atendimentoDTO.getServicoIdServico())){
            throw new RegistroNaoEncontradoException("Serviço com ID "
                    +servicoDAO.servicoExists(atendimentoDTO.getServicoIdServico())
                    +" não existe na base de dados!");
        }
    }

    private void validarAtributos(AtendimentoRequestDTO atendimentoDTO) throws AtributosInvalidosException {
        LOG.info("Validando os atributos do Atendimento");
        String messages = "";

        if(atendimentoDTO.getPetIdPet() == null){
            messages = messages.concat("Por favor selecione um pet!\n");
        }

        if(atendimentoDTO.getServicoIdServico() == null){
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

    public RelatorioAtendimentoDTO gerarRelatorio(FiltroRelatorioAtendimento filtro) {
        LOG.info("Gerando relatório.");
        return atendimentoDAO.gerarRelatorio(filtro);
    }

    public List<AtendimentoRegistroDTO> listAllRegistrar() {
        LOG.info("Buscando todos os atendimentos agendados!");
        return atendimentoDAO.listAllRegistrar();
    }

    public void finalizarAtendimento(Long idAtendimento) throws RegistroNaoEncontradoException {
        LOG.info("Iniciando a finalização do atendimento.");

        LOG.info("Verificando se o id do atendimento existe");
        boolean existe = atendimentoDAO.atendimentoExist(idAtendimento);

        if(!existe)
            throw new RegistroNaoEncontradoException("Atendimento não existe!");

        abrirConexaoBanco();
        atendimentoDAO.finalizarAtendimento(idAtendimento);
        commitarTransacaoBanco();

        LOG.info("Atendimento finalizado com sucesso!");
    }

    public void deletarAtendimento(Long idAtendimento) throws RegistroNaoEncontradoException {
        LOG.info("Iniciando o cancelamento do atendimento.");

        LOG.info("Verificando se o id do atendimento existe");
        boolean existe = atendimentoDAO.atendimentoExist(idAtendimento);

        if(!existe)
            throw new RegistroNaoEncontradoException("Atendimento não existe!");

        abrirConexaoBanco();
        atendimentoDAO.delete(idAtendimento);
        commitarTransacaoBanco();

        LOG.info("Atendimento deletado com sucesso!");
    }
}
