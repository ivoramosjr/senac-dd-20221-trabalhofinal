package petshop.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.connection.JpaConnectionFactory;
import petshop.model.dao.ServicoDAO;
import petshop.model.entity.Servico;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class ServicoService {

    private static Logger LOG = LogManager.getLogger(ServicoService.class);
    private EntityManager entityManager = new JpaConnectionFactory().getEntityManager();

    private ServicoDAO servicoDAO;

    public ServicoService() {
        this.servicoDAO = new ServicoDAO(this.entityManager);
    }

    //TODO vai receber um ServicoDTO no lugar do servico
    public void save(Servico servico) {
        //TODO validações

        try {
            this.servicoDAO.getEntityManager().getTransaction().begin();
            this.servicoDAO.save(servico);
            this.servicoDAO.getEntityManager().getTransaction().commit();
        }catch (SQLException e) {
            LOG.error("Deu ruim: "+e.getMessage());
        }
    }

    public void delete(Servico servico) {
        try {
            this.servicoDAO.getEntityManager().getTransaction().begin();
            this.servicoDAO.delete(servico);
            this.servicoDAO.getEntityManager().getTransaction().commit();
        } catch (SQLException e) {
            LOG.error("Deu ruim: "+e.getMessage());
        }
    }

    public List<Servico> findAll() {
        try {
            return this.servicoDAO.findAll();
        } catch (Exception e) {
            LOG.error("Deu ruim: "+e.getMessage());
        }
        return null;
    }

}
