package petshop.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaConnectionFactory {
	
	private EntityManagerFactory factory;
	
	public JpaConnectionFactory() {
		this.factory = Persistence.createEntityManagerFactory("petshop");
	}
	
	public EntityManager getEntityManager() {
		return this.factory.createEntityManager();
	}
}
