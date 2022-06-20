package petshop.model.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import petshop.filtros.FiltroPet;
import petshop.filtros.FiltroRelatorioPet;
import petshop.model.dtos.request.PetRequestDTO;
import petshop.model.dtos.response.*;
import petshop.model.entity.Pet;
import petshop.model.enums.StatusAtendimentoEnum;
import petshop.model.enums.TipoAnimal;

@Transactional
public class PetDAO extends GenericRepository{

	private static Logger LOG = LogManager.getLogger(PetDAO.class);

	public PetDAO() {
		super();
	}
	
	public void save(Pet pet) throws SQLException{
		LOG.info("Salvando PET: "+pet.getNome());
		this.getEntityManager().persist(pet);
	}
	
	@SuppressWarnings("unchecked")
	public List<PetResponseListagemDTO> findAll(){
		String sql = "SELECT new petshop.model.dtos.response.PetResponseListagemDTO(p.idPet, p.nome, p.nomeDono, p.raca, p.sexo) " +
				"FROM Pet p WHERE p.ativo = true";
		return this.getEntityManager().createQuery(sql, PetResponseListagemDTO.class).getResultList();
	}

	public boolean petExists(Long idPet) {
		String hql = "SELECT p FROM Pet p WHERE p.idPet = :idPet AND p.ativo = true";

		Pet pet = this.getEntityManager().createQuery(hql, Pet.class)
				.setParameter("idPet", idPet)
				.getSingleResult();

		if(pet == null)
			return false;

		return true;
	}

	public List<PetResponseListagemDTO> findWithFilter(FiltroPet filtro) {
		String hql = geracaoHQL(filtro);

		Query query = this.getEntityManager().createQuery(hql, PetResponseListagemDTO.class);

		montandoQuery(filtro, query);

		return query.getResultList();
	}

	private void montandoQuery(FiltroPet filtro, Query query) {
		if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
			String nome = "%"+filtro.getNome().toLowerCase()+"%";
			query.setParameter("nome", nome);
		}

		if(filtro.getNomeRaca() != null && !filtro.getNomeRaca().isEmpty()){
			String nome = "%"+filtro.getNomeRaca().toLowerCase()+"%";
			query.setParameter("nomeRaca", nome);
		}

		if(filtro.getSexoEnum() != null){
			query.setParameter("sexo", filtro.getSexoEnum());
		}
	}

	private String geracaoHQL(FiltroPet filtro) {
		String hql = "SELECT new petshop.model.dtos.response.PetResponseListagemDTO(p.idPet, p.nome, p.nomeDono, p.raca, p.sexo)" +
				"FROM Pet p WHERE p.ativo = true ";

		String and = "AND ";

		if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
			hql = hql.concat(and).concat("LOWER(p.nome) LIKE :nome ");
		}

		if(filtro.getNomeRaca() != null && !filtro.getNomeRaca().isEmpty()){
			hql = hql.concat(and).concat("LOWER(p.raca) LIKE :nomeRaca ");
		}

		if(filtro.getSexoEnum() != null){
			hql = hql.concat(and).concat("p.sexo = :sexo ");
		}

		hql = hql.concat("ORDER BY p.pontosFidelidade ").concat(filtro.getOrdemFidelidade().getDescricao());
		return hql;
	}

	public PetRequestDTO findByIdToEdit(Long idPet) {
		String hql = "SELECT new petshop.model.dtos.request.PetRequestDTO(p.idPet, p.nome, p.dataNascimento, p.nomeDono, p.raca, p.tipoAnimal, p.sexo) " +
				"FROM Pet p WHERE p.idPet = :idPet AND p.ativo = true";

		Query query = this.getEntityManager().createQuery(hql, PetRequestDTO.class).setParameter("idPet", idPet);

		return (PetRequestDTO) query.getSingleResult();
	}

	public List<String> getRacas() {
		String hql = "SELECT DISTINCT p.raca FROM Pet p WHERE p.ativo = true";

		Query query = this.getEntityManager().createQuery(hql, String.class);

		return query.getResultList();
	}

	public List<PetResponseRelatorioDTO> listAllRelatorio() {
		String hql = "SELECT new petshop.model.dtos.response.PetResponseRelatorioDTO(p) FROM Pet p ";

		Query query = this.getEntityManager().createQuery(hql, PetResponseRelatorioDTO.class);

		return query.getResultList();
	}

    public RelatorioPetDTO gerarRelatorio(FiltroRelatorioPet filtro) {
		Long numeroPetsInativos = getPetsInativos();
		Long totalPetsCadastrados = getTotalPets();
		System.out.println(numeroPetsInativos);
		TipoAnimal tipoAnimal = getTipoMaisCadastrado();
		System.out.println(tipoAnimal.toString());
		String petComMaiorPontoDeFidelidade = getMaiorPontoDeFidelidade();
		System.out.println(petComMaiorPontoDeFidelidade);

		List<PetResponseRelatorioDTO> listaPets = new ArrayList<>();


		if(filtro.isGerarTabela()){
			listaPets = this.listAllRelatorio();
		}
		RelatorioPetDTO relatorioPetdto = new RelatorioPetDTO(
				numeroPetsInativos, tipoAnimal, totalPetsCadastrados,petComMaiorPontoDeFidelidade, listaPets
		);
		return relatorioPetdto;
    }

	private Long getTotalPets() {
		return (Long) this.getEntityManager()
				.createQuery("SELECT COUNT(p) FROM Pet p")
				.getSingleResult();
	}

	private String getMaiorPontoDeFidelidade() {
		try{
			String sql = "SELECT p.nome,p.pontosFidelidade AS pontos FROM Pet p ORDER BY pontos DESC";
			Object[] result = (Object[]) this.getEntityManager()
					.createQuery(sql)
					.setMaxResults(1)
					.getSingleResult();
			String maiorFidelidade = (String) result[0];
			return maiorFidelidade;
		}catch (NoResultException e){
			return null;
		}
	}

	private TipoAnimal getTipoMaisCadastrado() {
		try{
			String sql = "SELECT p.tipoAnimal, COUNT(p.tipoAnimal) AS QUANTIDADE FROM Pet p " +
					"GROUP BY p.tipoAnimal ORDER BY QUANTIDADE DESC";
			Object[] result = (Object[]) this.getEntityManager()
					.createQuery(sql)
					.setMaxResults(1)
					.getSingleResult();
			TipoAnimal tipoAnimal = (TipoAnimal) result[0];
			return tipoAnimal;
		}catch (NoResultException e){
			return null;
		}
	}

	private Long getPetsInativos() {

		return (Long) this.getEntityManager()
				.createQuery("SELECT COUNT(p) FROM Pet p WHERE p.ativo = :ativo")
				.setParameter("ativo", false)
				.getSingleResult();
	}
}
