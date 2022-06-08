package petshop.model.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import petshop.model.dtos.FiltroPetDTO;
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
		Pet pet = this.getEntityManager().find(Pet.class, idPet);

		if(pet == null)
			return false;

		return true;
	}

	public List<Pet> findWithFilter(FiltroPetDTO filtro) {
		String hql = geracaoHQL(filtro);

		Query query = this.getEntityManager().createQuery(hql, Pet.class);

		montandoQuery(filtro, query);

		return query.getResultList();
	}

	private void montandoQuery(FiltroPetDTO filtro, Query query) {
		if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
			String nome = "%"+filtro.getNome().toLowerCase()+"%";
			query.setParameter("nome", nome);
		}

		if(filtro.getNomeRaca() != null && !filtro.getNomeRaca().isEmpty()){
			String nome = "%"+filtro.getNomeRaca().toLowerCase()+"%";
			query.setParameter("nomeRaca", nome);
		}
	}

	private String geracaoHQL(FiltroPetDTO filtro) {
		String hql = "SELECT p FROM Pet p ";

		String andOrWhere = "WHERE ";

		if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
			hql = hql.concat(andOrWhere).concat("LOWER(p.nome) LIKE :nome ");
			andOrWhere = "AND ";
		}

		if(filtro.getNomeRaca() != null && !filtro.getNomeRaca().isEmpty()){
			hql = hql.concat(andOrWhere).concat("LOWER(p.raca) LIKE :nomeRaca ");
			andOrWhere = "AND ";
		}

		hql = hql.concat("ORDER BY p.pontosFidelidade ").concat(filtro.getOrdemFidelidade().getDescricao());
		return hql;
	}
}
