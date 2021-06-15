package br.ucb.modelo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	private static EntityManagerFactory emF = null; 	
	private static EntityManager em = null; 
	
	public static EntityManager getEntityManager() { 
		if (emF == null) 
			emF = Persistence.createEntityManagerFactory("loja"); 
		if (em == null) 
			em = emF.createEntityManager(); 
		return em; 
	} 
	
	public static void close() { 
		em.close(); 
		emF.close(); 
	}
	
} 