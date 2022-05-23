package petshop.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import petshop.connection.JpaConnectionFactory;

public class Application {

	private static Logger LOG = LogManager.getLogger(Application.class);
	
	private static EntityManager entityManager;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		LOG.info("Iniciando a aplicação");
		entityManager = new JpaConnectionFactory().getEntityManager();
	}

}
