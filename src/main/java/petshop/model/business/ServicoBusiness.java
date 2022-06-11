package petshop.model.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.dao.ServicoDAO;
import petshop.filtros.FiltroServico;
import petshop.model.dtos.ServicoDTO;
import petshop.model.entity.Servico;

import java.sql.SQLException;
import java.util.List;

public class ServicoBusiness {

    private static final Logger LOG = LogManager.getLogger(ServicoBusiness.class);

    private ServicoDAO servicoDAO;

    public ServicoBusiness(){
        this.servicoDAO = new ServicoDAO();
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
        return this.servicoDAO.findAll();
    }

    public List<ServicoDTO> findWithFilter(FiltroServico filtro){
        LOG.info("Preparando para pesquisar os serviços com filtro");
        return this.servicoDAO.findWithFilter(filtro);
    }

    //TODO fazer delete lógico do serviço

    public void delete(Long idServico) throws RegistroNaoEncontradoException {
        LOG.info("Preparando para deletar o serviço de id: "+idServico);

        if(!servicoDAO.servicoExists(idServico)){
            throw new RegistroNaoEncontradoException("Serviço de ID: "+ idServico +" não encontrado na base de dados!");
        }

        Servico servico = servicoDAO.find(Servico.class, idServico);

        servico.setStatus(false);

        abrirConexaoBanco();
        this.servicoDAO.merge(servico);
        commitarTransacaoBanco();

        LOG.info("Serviço deletado com sucesso!");
    }

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
