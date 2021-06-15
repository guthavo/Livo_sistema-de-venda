package br.ucb.modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ucb.modelo.Cliente;


public class ClienteDAO {
	EntityManager em;

	public ClienteDAO() {
		this.em = ConnectionFactory.getEntityManager();
	}
	
	public boolean incluir(Cliente cliente) {
		this.em.getTransaction().begin();
		try {
			this.em.persist(cliente);   
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}
	}

	public boolean excluir(Cliente cliente) {
		this.em.getTransaction().begin();
		try {
			this.em.remove(cliente);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}		
	}

	public boolean alterar(Cliente cliente) {
		this.em.getTransaction().begin();
		try {
			this.em.merge(cliente);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}		
	}
	
	//@SuppressWarnings("unchecked")
	public Cliente consultar(long id) {
		return this.em.find(Cliente.class, id);
	}

	public Cliente consultar(String email) {
		try {
			Query q = this.em.createQuery("SELECT c FROM Cliente c WHERE c.email = :filtroEmail");
			q.setParameter("filtroEmail", email);
			return (Cliente) q.getSingleResult();
		} catch(NoResultException e) {
	        return null;
	    }		
	}		
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listar() {
		Query q = this.em.createQuery("SELECT c FROM Cliente c");
		return q.getResultList();
	}
	
	
	
}
