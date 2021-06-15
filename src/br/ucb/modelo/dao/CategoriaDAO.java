package br.ucb.modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ucb.modelo.Categoria;

public class CategoriaDAO {
	EntityManager em;

	public CategoriaDAO() {
		this.em = ConnectionFactory.getEntityManager();
	}
	
	public boolean incluir(Categoria categoria) {
		this.em.getTransaction().begin();
		try {
			this.em.persist(categoria);   
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}
	}

	public boolean excluir(Categoria categoria) {
		this.em.getTransaction().begin();
		try {
			this.em.remove(categoria);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}		
	}

	public boolean alterar(Categoria categoria) {
		this.em.getTransaction().begin();
		try {
			this.em.merge(categoria);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}		
	}
	
	//@SuppressWarnings("unchecked")
	public Categoria consultar(long id) {
		return this.em.find(Categoria.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> listar() {
		Query q = this.em.createQuery("SELECT c FROM Categoria c");
		return q.getResultList();
	}
	
	
	
	
	
}

