package petshop.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import petshop.model.entity.Atendimento;
import petshop.model.entity.Pet;
import petshop.model.entity.Servico;
import petshop.model.enums.StatusAtendimentoEnum;
import petshop.model.enums.TipoAnimal;
import petshop.model.service.AtendimentoService;
import petshop.model.service.PetService;
import petshop.model.entity.Servico;
import petshop.model.service.ServicoService;

public class Application {

	private static Logger LOG = LogManager.getLogger(Application.class);
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		//EXEMPLO DE INSERÇÃO E VISUALIZAÇÃO
		LOG.info("Iniciando a aplicação");
		ServicoService servicoService = new ServicoService();
		PetService servicePet = new PetService();
		//EXEMPLO UTILIZAR DTO NO LUGAR DA ENTIDADE
		Pet pet = new Pet();
		pet.setNome("Jorge");
		pet.setNomeDono("Jorjão");
		pet.setDataNascimento(LocalDate.now());
		pet.setRaca("Pastor Alemão");
		pet.setTipoAnimal(TipoAnimal.CACHORRO);
		
		servicePet.save(pet);
		List<Pet> petList = servicePet.listAll();

		Servico servico = new Servico();
		servico.setNome("Banho e tosa");
		servico.setDescricao("Banho e tosa com bônus de patas feitas");
		servico.setValor(89.90);

		servicoService.save(servico);
		List<Servico> servicoList = servicoService.findAll();

		servico.setNome("Banho e tosa TOPZERA");

		scan.nextLine();

		System.out.println(servicoList.get(0).getNome());
		Atendimento atendimento = new Atendimento();

		scan.nextLine();
		servicoList = servicoService.findAll();
//		atendimento.setDataAtendimento(LocalDateTime.parse("2000-30-10 00:00:00"));
		atendimento.setStatusAtendimento(StatusAtendimentoEnum.DESMARCADO);
		atendimento.setPet(pet);
		atendimento.setServico(servico);
		AtendimentoService atendimentoService = new AtendimentoService();
		atendimentoService.save(atendimento);

		scan.nextLine();

		System.out.println(servicoList.size());

		scan.nextLine();
	}

}
