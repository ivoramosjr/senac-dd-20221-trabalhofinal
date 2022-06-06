package petshop.model.service;

import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import petshop.connection.JpaConnectionFactory;
import petshop.model.dao.PetDAO;
import petshop.model.entity.Pet;

@ApplicationScoped
public class PetService {
	
	private static Logger LOG = LogManager.getLogger(PetService.class);
	private EntityManager entityManager = new JpaConnectionFactory().getEntityManager();
	
	private PetDAO petDAO;
	
	public PetService() {
		this.petDAO = new PetDAO(this.entityManager);
	}
	
	//TODO vai receber um PetDTO no lugar do pet
	public void save(Pet pet) {
		//TODO validações
		
    	try {
    		this.petDAO.getEntityManager().getTransaction().begin();
    		this.petDAO.save(pet);
    		this.petDAO.getEntityManager().getTransaction().commit();
    	}catch (SQLException e) {
    		LOG.error("Deu ruim: "+e.getMessage());
		}
	}

	public List<Pet> listAll(){
		return this.petDAO.findAll();
	}

	public Pet getPetById(Long id){
		return this.petDAO.find(Pet.class,id);
	}

}
