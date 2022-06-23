package petshop.model.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.filtros.FiltroServico;
import petshop.model.dao.ServicoDAO;
import petshop.model.dtos.request.ServicoRequestDTO;
import petshop.model.dtos.response.RelatorioServicoDTO;
import petshop.model.dtos.response.ServicoResponseDTO;
import petshop.model.dtos.response.ServicoResponseRelatorioDTO;
import petshop.model.entity.Servico;

import java.sql.SQLException;
import java.util.List;

public class ServicoBusiness {

    private static Logger LOG = LogManager.getLogger(ServicoBusiness.class);

    private ServicoDAO servicoDAO;

    public ServicoBusiness(){
        this.servicoDAO = new ServicoDAO();
    }

    public void save(ServicoRequestDTO servicoDTO) throws SQLException, AtributosInvalidosException {
        LOG.info("Preparando para salvar o servico de nome: "+servicoDTO.getNome());
        validarAtributos(servicoDTO);

        Servico servico = new Servico();

        setAtributosServico(servicoDTO, servico);

        abrirConexaoBanco();
        this.servicoDAO.save(servico);
        commitarTransacaoBanco();
        LOG.info("Serviço adicionado com sucesso!");
    }

    public void update(Long idServico, ServicoRequestDTO servicoDTO) throws RegistroNaoEncontradoException, AtributosInvalidosException {
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

    public List<ServicoResponseDTO> listAll(){
        LOG.info("Procurando todos os serviços cadastrados");

        List<ServicoResponseDTO> servicos = this.servicoDAO.findAll();

        return servicos;
    }

    public RelatorioServicoDTO gerarRelatorio(){
        LOG.info("Preparando para gerar relatório");
        return this.servicoDAO.gerarRelatorio();
    }

    public List<ServicoResponseDTO> findWithFilter(FiltroServico filtro){
        LOG.info("Preparando para pesquisar os serviços com filtro");

        List<ServicoResponseDTO> servicos = this.servicoDAO.findWithFilter(filtro);

        return servicos;
    }

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

    private void setAtributosServico(ServicoRequestDTO servicoDTO, Servico servico) {
        if(servicoDTO.getNome() != null)
            servico.setNome(servicoDTO.getNome());

        if(servicoDTO.getDescricao() != null)
            servico.setDescricao(servicoDTO.getDescricao());

        if(servicoDTO.getValor() != null)
            servico.setValor(servicoDTO.getValor());
    }

    private void validarAtributos(ServicoRequestDTO servico) throws AtributosInvalidosException {
        LOG.info("Validando os atributos do Serviço");
        String messages = "";

        if(servico.getNome() == null || servico.getNome().isBlank() || servico.getNome().length() > 100){
            messages = messages.concat("Nome não pode estar em branco ou ter mais que 100 caracteres!\n");
        }

        if(servico.getValor() == null || servico.getValor().toString().isEmpty() || servico.getValor() <= 0){
            messages = messages.concat("Valor não pode estar em branco ou menor que zero!\n");
        }

        if(servico.getDescricao() == null || servico.getDescricao().isBlank() || servico.getDescricao().length() > 255){
            messages = messages.concat("Descrição não pode estar em branco ou ter mais que 255 caracteres!\n");
        }

        if(!messages.isEmpty()){
            LOG.error("Erro ao validar os atributos. Algum atributo inválido!\n");
            throw new AtributosInvalidosException(messages);
        }
    }

    public ServicoRequestDTO findByIdToEdit(Long idServico) throws AtributosInvalidosException, RegistroNaoEncontradoException {
        LOG.info("Preparando para procurar serviço de ID: "+idServico);

        if(idServico == null || idServico.equals(0))
            throw new AtributosInvalidosException("Atributo inválido!");

        if(!servicoDAO.servicoExists(idServico)){
            throw new RegistroNaoEncontradoException("Serviço não encontrado na base de dados!");
        }

        return servicoDAO.findByIdToEdit(idServico);
    }

    private void commitarTransacaoBanco() {
        this.servicoDAO.getEntityManager().getTransaction().commit();
    }

    private void abrirConexaoBanco() {
        this.servicoDAO.getEntityManager().getTransaction().begin();
    }

}
