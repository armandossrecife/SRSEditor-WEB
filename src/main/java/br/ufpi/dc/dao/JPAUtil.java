package br.ufpi.dc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManager entityManager;
    private static EntityManagerFactory factory=null;

    public static EntityManager getEntityManager() {
        try {
            if (factory == null) {
                factory = Persistence.createEntityManagerFactory("SRSEditor");
                entityManager = factory.createEntityManager();
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        
        return entityManager;
    }

}
