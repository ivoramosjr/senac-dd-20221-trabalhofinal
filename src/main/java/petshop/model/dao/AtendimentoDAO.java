package petshop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.model.entity.Atendimento;
import petshop.model.entity.Pet;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class AtendimentoDAO extends  GenericRepository{

    private static Logger LOG = LogManager.getLogger(PetDAO.class);

    public AtendimentoDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public void save(Atendimento atendimento) throws SQLException {
        LOG.info("Salvando PET: "+atendimento.getServico());
        this.getEntityManager().persist(atendimento);
    }

    @SuppressWarnings("unchecked")
    public List<Pet> findAll(){
        return this.getEntityManager().createQuery("SELECT a FROM Atendimento a").getResultList();
    }

}
