package petshop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.model.entity.Servico;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class ServicoDAO extends GenericRepository{

    private static final Logger LOG = LogManager.getLogger(ServicoDAO.class);

    public ServicoDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public void save(Servico servico) throws SQLException {
        LOG.info("Salvando SERVICO: "+servico.getIdServico());
        this.getEntityManager().persist(servico);
    }

    @SuppressWarnings("unchecked")
    public List<Servico> findAll() {
        LOG.info("Buscando todos os SERVICOS");
        return this.getEntityManager().createQuery("SELECT s FROM Servico s").getResultList();
    }

    public void update(Servico servico) throws SQLException {
        LOG.info("Atualizando SERVICO: "+servico.getIdServico());
        this.getEntityManager().merge(servico);
    }

    public void delete(Servico servico) throws SQLException {
        LOG.info("Deletando logicamente SERVICO: "+servico.getIdServico());
        this.getEntityManager().merge(servico);
    }

}
