package petshop.model.business;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.dao.PetDAO;
import petshop.filtros.FiltroPet;
import petshop.model.dtos.request.PetRequestDTO;
import petshop.model.dtos.response.PetResponseListagemDTO;
import petshop.model.dtos.response.PetResponseRelatorioDTO;
import petshop.model.entity.Pet;

@ApplicationScoped
public class PetBusiness {
	
	private static Logger LOG = LogManager.getLogger(PetBusiness.class);
	
	private PetDAO petDAO;
	
	public PetBusiness() {
		this.petDAO = new PetDAO();
	}

	public void save(PetRequestDTO petDTO) throws SQLException, AtributosInvalidosException {
		LOG.info("Preparando para salvar o Pet de nome: "+petDTO.getNome());
		validarAtributos(petDTO);

		Pet pet = new Pet();

		setAtributosPet(petDTO, pet);

		abrirConexaoBanco();
		this.petDAO.save(pet);
		commitarTransacaoBanco();
		LOG.info("Pet adicionado com sucesso!");
	}

	public void update(Long idPet, PetRequestDTO petDTO) throws RegistroNaoEncontradoException {
		LOG.info("Preparando para atualizar o Pet de id:"+idPet);

		if(!petDAO.petExists(idPet)){
			throw new RegistroNaoEncontradoException("Pet de ID: "+ idPet +" não encontrado na base de dados!");
		}

		Pet pet = petDAO.find(Pet.class, idPet);

		setAtributosPet(petDTO, pet);

		abrirConexaoBanco();
		this.petDAO.merge(pet);
		commitarTransacaoBanco();

		LOG.info("Pet atualizado com sucesso!");
	}

	public List<PetResponseListagemDTO> listAll(){
		LOG.info("Procurando todos os pets cadastrados");
		List<PetResponseListagemDTO> pets = this.petDAO.findAll();

		LOG.info("Foram encontrados "+pets.size()+" pets.");

		return pets;
	}

	public List<PetResponseListagemDTO> findWithFilter(FiltroPet filtro){
		LOG.info("Preparando para pesquisar os pets com filtro");

		List<PetResponseListagemDTO> pets = this.petDAO.findWithFilter(filtro);

		return pets;
	}

	public void delete(Long idPet) throws RegistroNaoEncontradoException {
		LOG.info("Preparando para deletar o pet de id: "+idPet);

		if(!petDAO.petExists(idPet)){
			throw new RegistroNaoEncontradoException("Pet não encontrado na base de dados!");
		}

		Pet pet = petDAO.find(Pet.class, idPet);

		pet.setAtivo(false);

		abrirConexaoBanco();
		this.petDAO.merge(pet);
		commitarTransacaoBanco();

		LOG.info("Pet deletado com sucesso!");
	}

	public PetRequestDTO findByIdToEdit(Long idPet) throws AtributosInvalidosException, RegistroNaoEncontradoException {
		LOG.info("Preparando para encontrar o pet com ID: "+idPet);

		if(idPet == null || idPet.equals(0))
			throw new AtributosInvalidosException("Atributo inválido!");

		if(!petDAO.petExists(idPet))
			throw new RegistroNaoEncontradoException("Pet não encontrado na base de dados!");

		return petDAO.findByIdToEdit(idPet);
	}

	public List<String> getRacas() {
		return petDAO.getRacas();
	}

	private void setAtributosPet(PetRequestDTO petDTO, Pet pet) {
		if(petDTO.getNome() != null)
			pet.setNome(petDTO.getNome());

		if(petDTO.getDataNascimento() != null)
			pet.setDataNascimento(petDTO.getDataNascimento());

		if(petDTO.getNomeDono() != null)
			pet.setNomeDono(petDTO.getNomeDono());

		if(petDTO.getRaca() != null)
			pet.setRaca(petDTO.getRaca());

		if(petDTO.getTipoAnimal() != null)
			pet.setTipoAnimal(petDTO.getTipoAnimal());

		if(petDTO.getSexo() != null)
			pet.setSexo(petDTO.getSexo());

	}

	private void validarAtributos(PetRequestDTO pet) throws AtributosInvalidosException {
		LOG.info("Validando os atributos do Pet");
		String messages = "";

		if(pet.getNome() == null || pet.getNome().isBlank() || pet.getNome().length() > 100){
			messages = messages.concat("Nome não pode estar em branco,nulo ou maior que 100 caracteres!\n");
		}

		if(pet.getNomeDono() == null || pet.getNomeDono().isBlank() || pet.getNomeDono().length() > 100){
			messages = messages.concat("Nome do dono não pode estar em branco,nulo ou maior que 100 caracteres!\n");
		}

		if(pet.getDataNascimento() == null){
			messages = messages.concat("Informe a data de nascimento!\n");
		}

		if(pet.getRaca() == null || pet.getRaca().isBlank() || pet.getRaca().length() > 50){
			messages = messages.concat("Raça não pode estar em branco,nulo ou maior que 50 caracteres!!\n");
		}

		if(pet.getTipoAnimal() == null){
			messages = messages.concat("O tipo de animal deve ser selecionado!\n");
		}

		if(pet.getSexo() == null){
			messages = messages.concat("Selecione um sexo para o animal!\n");
		}

		if(!messages.isEmpty()){
			LOG.error("Erro ao validar os atributos. Algum atributo inválido!\n");
			throw new AtributosInvalidosException(messages);
		}
	}

	private void commitarTransacaoBanco() {
		this.petDAO.getEntityManager().getTransaction().commit();
	}

	private void abrirConexaoBanco() {
		this.petDAO.getEntityManager().getTransaction().begin();
	}

	public List<PetResponseRelatorioDTO> listAllRelatorio() {
		return petDAO.listAllRelatorio();
	}
}
