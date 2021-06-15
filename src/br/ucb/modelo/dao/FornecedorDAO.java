package br.ucb.modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ucb.modelo.Fornecedor;

public class FornecedorDAO {
	EntityManager em;

	public FornecedorDAO() {
		this.em = ConnectionFactory.getEntityManager();
	}
	
	public boolean incluir(Fornecedor fornecedor) {
		this.em.getTransaction().begin();
		try {
			this.em.persist(fornecedor);   
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}
	}

	public boolean excluir(Fornecedor fornecedor) {
		this.em.getTransaction().begin();
		try {
			this.em.remove(fornecedor);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}		
	}

	public boolean alterar(Fornecedor fornecedor) {
		this.em.getTransaction().begin();
		try {
			this.em.merge(fornecedor);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}		
	}
	
	//@SuppressWarnings("unchecked")
	public Fornecedor consultar(long id) {
		return this.em.find(Fornecedor.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listar() {
		Query q = this.em.createQuery("SELECT c FROM Fornecedor c");
		return q.getResultList();
	}
	
	
	
	
	
}
