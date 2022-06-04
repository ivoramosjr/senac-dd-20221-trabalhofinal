package petshop.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;
import petshop.model.entity.Servico;
import petshop.model.service.ServicoService;

public class Application {

	private static Logger LOG = LogManager.getLogger(Application.class);
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		//EXEMPLO DE INSERÇÃO E VISUALIZAÇÃO
		LOG.info("Iniciando a aplicação");
		ServicoService servicoService = new ServicoService();
		
		//EXEMPLO UTILIZAR DTO NO LUGAR DA ENTIDADE
		Servico servico = new Servico();
		servico.setNome("Banho e tosa");
		servico.setDescricao("Banho e tosa com bônus de patas feitas");
		servico.setValor(89.90);

		servicoService.save(servico);
		List<Servico> servicoList = servicoService.findAll();
		servicoService.delete(servicoList.get(0));
		
		scan.nextLine();
		servicoList = servicoService.findAll();
		System.out.println(servicoList.size());
		
		scan.nextLine();
	}

}
