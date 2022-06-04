package petshop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.model.entity.Servico;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class ServicoDAO extends GenericRepository{

    private static Logger LOG = LogManager.getLogger(ServicoDAO.class);

    public ServicoDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public void save(Servico servico) throws SQLException {
        LOG.info("Salvando SERVICO: "+servico.getNome());
        this.getEntityManager().persist(servico);
    }

    public List<Servico> findAll() {
        return this.getEntityManager().createQuery("SELECT s FROM Servico s").getResultList();
    }

    public void delete(Servico servico) throws SQLException {
        LOG.info("Deletando SERVICO: "+servico.getNome());
        this.getEntityManager().remove(servico);
    }

}
