package petshop.model.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import petshop.model.entity.Pet;

@Transactional
public class PetDAO extends GenericRepository{

	private static Logger LOG = LogManager.getLogger(PetDAO.class);
	
	public PetDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	public void save(Pet pet) throws SQLException{
		LOG.info("Salvando PET: "+pet.getNome());
		this.getEntityManager().persist(pet);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pet> findAll(){
		return this.getEntityManager().createQuery("SELECT p FROM Pet p").getResultList();
	}

	public boolean petExists(Long idPet) {
		try{
			this.getEntityManager().find(Pet.class, idPet);
			return true;
		}catch (NoResultException e){
			return false;
		}
	}
}
