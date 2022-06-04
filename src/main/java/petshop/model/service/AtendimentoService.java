package petshop.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.connection.JpaConnectionFactory;
import petshop.model.dao.AtendimentoDAO;
import petshop.model.dao.PetDAO;
import petshop.model.entity.Atendimento;
import petshop.model.entity.Pet;

import javax.persistence.EntityManager;
import java.sql.SQLException;

public class AtendimentoService {

    private static Logger LOG = LogManager.getLogger(PetService.class);
    private EntityManager entityManager = new JpaConnectionFactory().getEntityManager();

    private AtendimentoDAO atendimentoDAO;

    public AtendimentoService() {
        this.atendimentoDAO = new AtendimentoDAO(this.entityManager);
    }

    //TODO vai receber um AtendimentoDTO no lugar do atendimento
    public void save(Atendimento atendimento) {
        //TODO validações

        try {
            this.atendimentoDAO.getEntityManager().getTransaction().begin();
            this.atendimentoDAO.save(atendimento);
            this.atendimentoDAO.getEntityManager().getTransaction().commit();
        }catch (SQLException e) {
            LOG.error("Deu ruim: "+e.getMessage());
        }
    }
}
