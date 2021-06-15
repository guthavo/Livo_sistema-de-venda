package br.ucb.modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ucb.modelo.Produto;

public class ProdutoDAO {
	EntityManager em;

	public ProdutoDAO() {
		this.em = ConnectionFactory.getEntityManager();
	}
	
	public boolean incluir(Produto produto) {
		this.em.getTransaction().begin();
		try {
			this.em.persist(produto);   
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}
	}

	public boolean excluir(Produto produto) {
		this.em.getTransaction().begin();
		try {
			this.em.remove(produto);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}		
	}

	public boolean alterar(Produto produto) {
		this.em.getTransaction().begin();
		try {
			this.em.merge(produto);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}		
	}
	
	//@SuppressWarnings("unchecked")
	public Produto consultar(long id) {
		return this.em.find(Produto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listar() {
		Query q = this.em.createQuery("SELECT c FROM Produto c");
		return q.getResultList();
	}
	
	
	
	
	
}
