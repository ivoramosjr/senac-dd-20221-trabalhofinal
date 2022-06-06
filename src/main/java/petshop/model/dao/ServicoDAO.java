package petshop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.model.dtos.FiltroServicoDTO;
import petshop.model.dtos.ServicoDTO;
import petshop.model.entity.Servico;
import petshop.model.enums.OrdemPesquisa;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServicoDAO extends GenericRepository{

    private static Logger LOG = LogManager.getLogger(ServicoDAO.class);

    public ServicoDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public void save(Servico servico) throws SQLException {
        LOG.info("Salvando serviço: "+servico.getNome());
        this.getEntityManager().persist(servico);
    }

    public List<Servico> findAll(){
        return this.getEntityManager().createQuery("SELECT s FROM Servico s WHERE s.status = true").getResultList();
    }

    public boolean servicoExists(Long idServico){
        Servico servico = this.getEntityManager().find(Servico.class, idServico);

        if(servico == null)
            return false;

        return true;
    }

    public List<ServicoDTO> findWithFilter(FiltroServicoDTO filtro){
        String hql = geracaoHQL(filtro);

        Query query = this.getEntityManager().createQuery(hql, Servico.class);

        montandoQuery(filtro, query);

        List<Servico> servicosEntity = query.getResultList();

        List<ServicoDTO> servicos = new ArrayList<>();

        servicos = servicosEntity
                .stream()
                .map(s -> new ServicoDTO(s))
                .collect(Collectors.toList());
        for(ServicoDTO servico : servicos){
            servico.setQuantidadeAtendimentos(
                    this.getQuantidadeAtendimentosServico(servico.getIdServico()).intValue()
            );
        }

        if(filtro.getOrdemQuantidadeAtendimentos().equals(OrdemPesquisa.DESC)){
            servicos = servicos.stream()
                    .sorted(Comparator
                            .comparingInt(ServicoDTO::getQuantidadeAtendimentos)
                            .reversed())
                    .collect(Collectors.toList());
        }else{
            servicos = servicos.stream()
                    .sorted(Comparator
                            .comparingInt(ServicoDTO::getQuantidadeAtendimentos))
                    .collect(Collectors.toList());
        }

        return servicos;
    }

    public BigInteger getQuantidadeAtendimentosServico(Long idServico){
        String sql = "SELECT COUNT(s.ID_SERVICO) " +
                "FROM servico s " +
                "INNER JOIN atendimento a ON " +
                "s.ID_SERVICO = a.ID_SERVICO";

        Query query = this.getEntityManager().createNativeQuery(sql);

        return (BigInteger) query.getSingleResult();
    }

    private void montandoQuery(FiltroServicoDTO filtro, Query query) {
        if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
            String nome = "%"+filtro.getNome().toLowerCase()+"%";
            query.setParameter("nome", nome);
        }
    }

    private String geracaoHQL(FiltroServicoDTO filtro) {
        String hql = "SELECT s FROM Servico s ";

        if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
            hql = hql.concat("WHERE ").concat("LOWER(s.nome) LIKE :nome ");
        }

        hql = hql.concat("ORDER BY s.valor ").concat(filtro.getOrdemValor().getDescricao());

        return hql;
    }

    public void delete(ServicoDTO servicoDTO) {

    }
}
