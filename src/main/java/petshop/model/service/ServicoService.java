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

    private static final Logger LOG = LogManager.getLogger(ServicoService.class);
    private EntityManager entityManager = new JpaConnectionFactory().getEntityManager();

    private ServicoDAO servicoDAO;

    public ServicoService() {
        this.servicoDAO = new ServicoDAO(this.entityManager);
    }

    public void save(Servico servico) {
        try {
            openTransaction();
            this.servicoDAO.save(servico);
            commitTransaction();
        }catch (SQLException e) {
            LOG.error("Falha ao salvar serviço no banco: "+e.getMessage());
        }
    }

    public void delete(Servico servico) {
        try {
            openTransaction();
            this.servicoDAO.delete(servico);
            commitTransaction();
        } catch (SQLException e) {
            LOG.error("Falha ao deletar serviço no banco: "+e.getMessage());
        }
    }

    public List<Servico> findAll() {
        try {
            return this.servicoDAO.findAll();
        } catch (Exception e) {
            LOG.error("Falha ao buscar serviços no banco: "+e.getMessage());
        }
        return null;
    }

    public void update(Servico servico) {
        try {
            openTransaction();
            this.servicoDAO.update(servico);
            commitTransaction();
        }catch (SQLException e) {
            LOG.error("Falha ao atualizar serviço no banco: "+e.getMessage());
        }
    }

    private void openTransaction() {
        this.servicoDAO.getEntityManager().getTransaction().begin();
    }

    private void commitTransaction() {
        this.servicoDAO.getEntityManager().getTransaction().commit();
    }
}
