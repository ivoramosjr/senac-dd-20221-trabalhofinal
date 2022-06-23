package petshop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.filtros.FiltroServico;
import petshop.model.dtos.request.ServicoRequestDTO;
import petshop.model.dtos.response.*;
import petshop.model.entity.Servico;
import petshop.model.enums.OrdemPesquisa;
import petshop.model.enums.StatusAtendimentoEnum;
import petshop.model.enums.TipoAnimal;

import javax.persistence.Query;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServicoDAO extends GenericRepository{

    private static Logger LOG = LogManager.getLogger(ServicoDAO.class);

    public ServicoDAO() {
        super();
    }

    public void save(Servico servico) throws SQLException {
        LOG.info("Salvando serviço: "+servico.getNome());
        this.getEntityManager().persist(servico);
    }

    public List<ServicoResponseDTO> findAll(){
        String hql = generateBaseHQL();
        List<ServicoResponseDTO> servicos = this.getEntityManager().createQuery(hql, ServicoResponseDTO.class).getResultList();
        return servicos;
    }

    private List<ServicoResponseRelatorioDTO> getServices(){
        String sql = "SELECT new petshop.model.dtos.response.ServicoResponseRelatorioDTO(s) FROM Servico s";

        Query query = this.getEntityManager().createQuery(sql, ServicoResponseRelatorioDTO.class);

        return query.getResultList();
    }

    public boolean servicoExists(Long idServico){
        Servico servico = this.getEntityManager().find(Servico.class, idServico);

        if(servico == null)
            return false;

        return true;
    }

    public List<ServicoResponseDTO> findWithFilter(FiltroServico filtro){
        String hql = geracaoHQL(filtro);

        Query query = this.getEntityManager().createQuery(hql, ServicoResponseDTO.class);

        montandoQuery(filtro, query);

        List<ServicoResponseDTO> servicos = query.getResultList();

        if(filtro.getOrdemQuantidadeAtendimentos().equals(OrdemPesquisa.DESC)){
            servicos = servicos.stream()
                    .sorted(Comparator
                            .comparingLong(ServicoResponseDTO::getQuantidadeAtendimentos)
                            .reversed())
                    .collect(Collectors.toList());
        }else{
            servicos = servicos.stream()
                    .sorted(Comparator
                            .comparingLong(ServicoResponseDTO::getQuantidadeAtendimentos))
                    .collect(Collectors.toList());
        }

        return servicos;
    }

    public ServicoRequestDTO findByIdToEdit(Long idServico) {
        String hql = "SELECT new petshop.model.dtos.request.ServicoRequestDTO(s.idServico, s.nome, s.valor, s.descricao) " +
                "FROM Servico s WHERE s.idServico = :idServico ";
        Query query = this.getEntityManager().createQuery(hql, ServicoRequestDTO.class).setParameter("idServico",idServico);

        return (ServicoRequestDTO) query.getSingleResult();
    }

    private void montandoQuery(FiltroServico filtro, Query query) {
        if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
            String nome = "%"+filtro.getNome().toLowerCase()+"%";
            query.setParameter("nome", nome);
        }
    }

    private String geracaoHQL(FiltroServico filtro) {
        String hql = generateBaseHQL();

        if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
            hql = hql.concat("AND LOWER(s.nome) LIKE :nome ");
        }

        hql = hql.concat("ORDER BY s.valor ").concat(filtro.getOrdemValor().getDescricao());

        return hql;
    }

    private String generateBaseHQL() {
        return "SELECT new petshop.model.dtos.response.ServicoResponseDTO(s.id, s.nome, s.valor, " +
                "(SELECT COUNT(a.idAtendimento) AS QTD_ATENDIMENTOS FROM Atendimento a WHERE a.servico.idServico = s.idServico)) " +
                "FROM Servico s " +
                "WHERE s.status = true ";
    }

    public RelatorioServicoDTO gerarRelatorio() {
        Long numeroServicos = getNumeroServicos();
        Long numeroServicosInativos = getNumeroServicosInativos();
        String servicoMaisCaro = getServicoMaisCaro();
        String servicoMaisUtilizado = getServicoMaisUtilizado();
        String servicoMaisBarato = getServicoMaisBarato();
        String ultimoServicoCadastrado = getUltimoServicoCadastrado();
        List<ServicoResponseRelatorioDTO> listaServicos = new ArrayList<>();

        listaServicos = this.getServices();

        RelatorioServicoDTO relatorioServicoDTO = new RelatorioServicoDTO(
                numeroServicos, numeroServicosInativos, servicoMaisCaro, servicoMaisUtilizado,
                servicoMaisBarato, ultimoServicoCadastrado, listaServicos
        );

        return relatorioServicoDTO;
    }

    private String getUltimoServicoCadastrado() {
        return (String) this.getEntityManager()
                .createQuery("select s.nome from Servico s order by s.idServico desc")
                .setMaxResults(1)
                .getSingleResult();
    }

    private String getServicoMaisBarato() {
        return (String) this.getEntityManager()
                .createQuery("SELECT s.nome FROM Servico s ORDER BY s.valor asc")
                .setMaxResults(1)
                .getSingleResult();
    }

    private String getServicoMaisUtilizado() {
        List<Object[]> resultList = this.getEntityManager()
                .createQuery("select a.servico.nome, count(a.servico.idServico) as ocorrencias from Atendimento a group by a.servico.idServico order by ocorrencias desc")
                .setMaxResults(1)
                .getResultList();

        String nomeServico = resultList.isEmpty()?"":(String) resultList.get(0)[0];
        return nomeServico;
    }

    private String getServicoMaisCaro() {
        return (String) this.getEntityManager()
                .createQuery("SELECT s.nome FROM Servico s ORDER BY s.valor desc")
                .setMaxResults(1)
                .getSingleResult();
    }

    private Long getNumeroServicosInativos() {
        return (Long) this.getEntityManager()
                .createQuery("SELECT COUNT(s.idServico) FROM Servico s WHERE s.status is false")
                .getSingleResult();
    }

    private Long getNumeroServicos() {
        return (Long) this.getEntityManager()
                .createQuery("SELECT COUNT(s.idServico) FROM Servico s")
                .getSingleResult();
    }
}
