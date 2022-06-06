package petshop.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.connection.JpaConnectionFactory;
import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.dao.ServicoDAO;
import petshop.model.dtos.FiltroServicoDTO;
import petshop.model.dtos.ServicoDTO;
import petshop.model.entity.Servico;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoService {

    private static Logger LOG = LogManager.getLogger(ServicoService.class);

    private EntityManager entityManager = new JpaConnectionFactory().getEntityManager();

    private ServicoDAO servicoDAO;

    public ServicoService(){
        this.servicoDAO = new ServicoDAO(this.entityManager);
    }

    public void save(ServicoDTO servicoDTO) throws SQLException, AtributosInvalidosException {
        LOG.info("Preparando para salvar o servico de nome: "+servicoDTO.getNome());
        validarAtributos(servicoDTO);

        Servico servico = new Servico();

        setAtributosServico(servicoDTO, servico);

        abrirConexaoBanco();
        this.servicoDAO.save(servico);
        commitarTransacaoBanco();
        LOG.info("Serviço adicionado com sucesso!");
    }

    public void update(Long idServico, ServicoDTO servicoDTO) throws RegistroNaoEncontradoException, AtributosInvalidosException {
        LOG.info("Preparando para atualizar o serviço de id: "+idServico);

        if(!servicoDAO.servicoExists(idServico)){
            throw new RegistroNaoEncontradoException("Serviço de ID: "+ idServico +" não encontrado na base de dados!");
        }

        validarAtributos(servicoDTO);

        Servico servico = servicoDAO.find(Servico.class, idServico);

        setAtributosServico(servicoDTO, servico);

        abrirConexaoBanco();
        this.servicoDAO.merge(servico);
        commitarTransacaoBanco();

        LOG.info("Serviço atualizado com sucesso!");
    }

    public List<ServicoDTO> listAll(){
        LOG.info("Procurando todos os serviços cadastrados");
        List<Servico> servicosEntity = this.servicoDAO.findAll();
        List<ServicoDTO> servicos = new ArrayList<>();

        for(Servico servico : servicosEntity){
            ServicoDTO servicoDTO = new ServicoDTO(servico);
            servicoDTO
                    .setQuantidadeAtendimentos(this.servicoDAO
                            .getQuantidadeAtendimentosServico(servico.getIdServico()).intValue());
            servicos.add(servicoDTO);
        }

        LOG.info("Foram encontrados "+servicos.size()+" serviços.");
        return servicos;
    }

    public List<ServicoDTO> findWithFilter(FiltroServicoDTO filtro){
        LOG.info("Preparando para pesquisar os serviços com filtro");

        List<ServicoDTO> servicos = this.servicoDAO.findWithFilter(filtro);

        return servicos;
    }

    //TODO fazer delete lógico do serviço

    private void setAtributosServico(ServicoDTO servicoDTO, Servico servico) {
        if(servicoDTO.getNome() != null)
            servico.setNome(servicoDTO.getNome());

        if(servicoDTO.getDescricao() != null)
            servico.setDescricao(servicoDTO.getDescricao());

        if(servicoDTO.getValor() != null)
            servico.setValor(servicoDTO.getValor());
    }

    private void validarAtributos(ServicoDTO servico) throws AtributosInvalidosException {
        LOG.info("Validando os atributos do Serviço");
        String messages = "";

        if(servico.getNome() == null || servico.getNome().isBlank()){
            messages = messages.concat("Nome não pode estar em branco ou nulo!\n");
        }

        if(servico.getValor() == null || servico.getValor() <= 0){
            messages = messages.concat("Valor não pode ser nulo ou menor que zero!\n");
        }

        if(servico.getDescricao() == null || servico.getDescricao().isBlank()){
            messages = messages.concat("Descrição não pode estar em branco ou nulo!\n");
        }

        if(!messages.isEmpty()){
            LOG.error("Erro ao validar os atributos. Algum atributo inválido!\n");
            throw new AtributosInvalidosException(messages);
        }
    }

    private void commitarTransacaoBanco() {
        this.servicoDAO.getEntityManager().getTransaction().commit();
    }

    private void abrirConexaoBanco() {
        this.servicoDAO.getEntityManager().getTransaction().begin();
    }

}
