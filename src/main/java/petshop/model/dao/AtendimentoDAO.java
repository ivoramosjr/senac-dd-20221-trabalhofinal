package petshop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.model.dtos.FiltroAtendimentoDTO;
import petshop.model.entity.Atendimento;
import petshop.model.enums.StatusAtendimentoEnum;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Transactional
public class AtendimentoDAO extends GenericRepository{

    private static Logger LOG = LogManager.getLogger(AtendimentoDAO.class);

    public AtendimentoDAO() {
        super();
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

    public boolean atendimentoExist(Long idAtendimento){
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
        if(filtro.getNomePet() != null && !filtro.getNomePet().isEmpty()){
            String nome = "%"+ filtro.getNomePet().toLowerCase()+"%";
            query.setParameter("nomePet", nome);
        }

        if(filtro.getNomeServico() != null && !filtro.getNomeServico().isEmpty()){
            String nome = "%"+ filtro.getNomeServico().toLowerCase()+"%";
            query.setParameter("nomeServico", nome);
        }

        query.setParameter("statusAtendimento", filtro.getStatusAntedimento().getNome().toLowerCase());
    }

    private String geracaoSQL(FiltroAtendimentoDTO filtro) {
        String sql = "SELECT * FROM Atendimento a " +
                "INNER JOIN Pet p ON " +
                "a.ID_PET = p.ID_PET " +
                "INNER JOIN Servico s ON " +
                "a.ID_SERVICO = s.ID_SERVICO ";

        String andOrWhere = "WHERE ";

        if(filtro.getNomePet() != null && !filtro.getNomePet().isEmpty()){
            sql = sql.concat(andOrWhere).concat("LOWER(p.nome) like :nomePet ");
            andOrWhere = "AND ";
        }

        if(filtro.getNomeServico() != null && !filtro.getNomeServico().isEmpty()){
            sql = sql.concat(andOrWhere).concat("LOWER(s.nome) like :nomeServico ");
            andOrWhere = "AND ";
        }

        sql = sql.concat(andOrWhere).concat("LOWER(a.statusAtendimento) = :statusAtendimento ");

        sql = sql.concat("ORDER BY a.dataatendimento ").concat(filtro.getOrdemData().getDescricao());

        return sql;
    }

    public boolean horarioEstaMarcado(Long idAtendimento, LocalDateTime dataAtendimento) {
        String sql = "SELECT ID_ATENDIMENTO FROM Atendimento " +
                "WHERE dataAtendimento = :dataAtendimento ";

        if(idAtendimento != null)
            sql = sql.concat("AND ID_ATENDIMENTO = :idAtendimento");

        Query query = this.getEntityManager()
                .createNativeQuery(sql)
                .setParameter("dataAtendimento", dataAtendimento);

        if(idAtendimento != null)
            query.setParameter("idAtendimento", idAtendimento);

        try {
            BigInteger id = (BigInteger) query.getSingleResult();

            if(idAtendimento != null && idAtendimento == id.longValue())
                return false;

            return true;
        }catch (NoResultException e){
            return false;
        }
    }

}
