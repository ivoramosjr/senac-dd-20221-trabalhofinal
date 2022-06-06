package petshop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.model.dtos.FiltroAtendimentoDTO;
import petshop.model.entity.Atendimento;
import petshop.model.enums.StatusAtendimentoEnum;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
public class AtendimentoDAO extends GenericRepository{

    private static Logger LOG = LogManager.getLogger(AtendimentoDAO.class);

    public AtendimentoDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public void save(Atendimento atendimento) throws SQLException {
        LOG.info("Salvando atendimento");
        this.getEntityManager().persist(atendimento);
    }

    public List<Atendimento> findAll(){
        return this.getEntityManager()
                .createQuery("SELECT a FROM Atendimento a ")
                .getResultList();
    }

    public boolean atendimentoExixst(Long idAtendimento){
        Atendimento atendimento = this.find(Atendimento.class, idAtendimento);

        if(atendimento == null)
            return false;

        return true;
    }

    public void delete(Long idAtendimento){
        LOG.info("Delentando atendimento: "+idAtendimento);

        Atendimento atendimento = this.find(Atendimento.class, idAtendimento);

        atendimento.setStatusAtendimento(StatusAtendimentoEnum.DESMARCADO);

        this.merge(atendimento);
    }

    public List<Atendimento> findWithFilter(FiltroAtendimentoDTO filtro){
        String sql = geracaoSQL(filtro);

        Query query = this.getEntityManager().createNativeQuery(sql, Atendimento.class);

        montandoQuery(filtro, query);

        return query.getResultList();
    }

    private void montandoQuery(FiltroAtendimentoDTO filtro, Query query) {
        if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
            String nome = "%"+ filtro.getNome().toLowerCase()+"%";
            query.setParameter("nome", nome);
        }

        if(filtro.getNomeRaca() != null && !filtro.getNomeRaca().isEmpty()){
            String nome = "%"+ filtro.getNomeRaca().toLowerCase()+"%";
            query.setParameter("nomeRaca", nome);
        }

        query.setParameter("statusAtendimento", filtro.getStatusAntedimento().getNome().toLowerCase());
    }

    private String geracaoSQL(FiltroAtendimentoDTO filtro) {
        String sql = "SELECT * FROM Antedimento a INNER JOIN Pet p ON " +
                "a.ID_PET = p.ID_PET " +
                "INNER JOIN Servico s ON " +
                "a.ID_SERVICO = s.ID_SERVICO ";

        String andOrWhere = "WHERE ";

        if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
            sql = sql.concat(andOrWhere).concat("LOWER(p.nome) = :nome ");
            andOrWhere = "AND ";
        }

        if(filtro.getNomeRaca() != null && !filtro.getNomeRaca().isEmpty()){
            sql = sql.concat(andOrWhere).concat("LOWER(p.raca) = :nomeRaca ");
            andOrWhere = "AND ";
        }

        sql = sql.concat(andOrWhere).concat("LOWER(a.statusAtendimento) = :statusAtendimento ");

        sql = sql.concat("ORDER BY a.dataatendimento ").concat(filtro.getOrdemData().getDescricao());

        return sql;
    }

    public boolean horarioEstaMarcado(LocalDateTime dataAtendimento) {
        String sql = "SELECT COUNT(ID_ATENDIMENTO) FROM Atendimento " +
                "WHERE dataAtendimento = :dataAtendimento ";

        Query query = this.getEntityManager().createNativeQuery(sql).setParameter("dataAtendimento", dataAtendimento);

        return query.getResultList().size() > 0? true: false;
    }
}
