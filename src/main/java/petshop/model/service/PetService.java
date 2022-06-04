package petshop.model.service;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jetbrains.annotations.NotNull;
import petshop.connection.JpaConnectionFactory;
import petshop.exceptions.AtributosInvalidosException;
import petshop.model.dao.PetDAO;
import petshop.model.dtos.PetDTO;
import petshop.model.entity.Pet;

@ApplicationScoped
public class PetService {
	
	private static Logger LOG = LogManager.getLogger(PetService.class);

	private EntityManager entityManager = new JpaConnectionFactory().getEntityManager();
	
	private PetDAO petDAO;
	
	public PetService() {
		this.petDAO = new PetDAO(this.entityManager);
	}

	public void save(PetDTO petDTO) throws SQLException, AtributosInvalidosException {
		LOG.info("Preparando para salvar o Pet de nome: "+petDTO.getNome());
		validarAtributos(petDTO);

		Pet pet = new Pet();

		setAtributosPet(petDTO, pet);

		this.petDAO.getEntityManager().getTransaction().begin();
		this.petDAO.save(pet);
		this.petDAO.getEntityManager().getTransaction().commit();
	}

	private void setAtributosPet(PetDTO petDTO, Pet pet) {
		pet.setNome(petDTO.getNome());
		pet.setDataNascimento(petDTO.getDataNascimento());
		pet.setNomeDono(petDTO.getNomeDono());
		pet.setRaca(petDTO.getRaca());
		pet.setTipoAnimal(petDTO.getTipoAnimal());
	}

	private void validarAtributos(PetDTO pet) throws AtributosInvalidosException {
		LOG.info("Validando os atributos do Pet");
		List<String> messages = new ArrayList<>();

		if(pet.getNome().isBlank()){
			messages.add("Nome não pode estar em branco ou nulo!");
		}

		if(pet.getDataNascimento() == null){
			messages.add("A data de nascimento não pode estar nula!");
		}

		if(pet.getRaca().isBlank()){
			messages.add("Raça não pode estar em branco ou nulo!");
		}

		if(pet.getTipoAnimal() == null){
			messages.add("O tipo de animal deve ser selecionado!");
		}

		if(!messages.isEmpty()){
			LOG.error("Erro ao validar os atributos. Algum atributo inválido!");
			throw new AtributosInvalidosException(messages.toString());
		}
	}

}
