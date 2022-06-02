package petshop.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Scanner;
import petshop.model.entity.Pet;
import petshop.model.enums.TipoAnimal;
import petshop.model.service.PetService;

public class Application {

	private static Logger LOG = LogManager.getLogger(Application.class);
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		//EXEMPLO DE INSERÇÃO E VISUALIZAÇÃO
		LOG.info("Iniciando a aplicação");
		PetService servicePet = new PetService();
		
		//EXEMPLO UTILIZAR DTO NO LUGAR DA ENTIDADE
		Pet pet = new Pet();
		pet.setNome("Jorge");
		pet.setNomeDono("Jorjão");
		pet.setDataNascimento(LocalDate.now());
		pet.setRaca("Pastor Alemão");
		pet.setTipoAnimal(TipoAnimal.CACHORRO);
		
		servicePet.save(pet);
		
		scan.nextLine();
		
		System.out.println(pet.getIdPet());
		
		scan.nextLine();
	}

}
