package br.ucb.modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ucb.modelo.Funcionario;

public class FuncionarioDAO {
	EntityManager em;

	public FuncionarioDAO() {
		this.em = ConnectionFactory.getEntityManager();
	}
	
	public boolean incluir(Funcionario funcionario) {
		this.em.getTransaction().begin();
		try {
			this.em.persist(funcionario);   
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}
	}

	public boolean excluir(Funcionario funcionario) {
		this.em.getTransaction().begin();
		try {
			this.em.remove(funcionario);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}		
	}

	public boolean alterar(Funcionario funcionario) {
		this.em.getTransaction().begin();
		try {
			this.em.merge(funcionario);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			return false;
		}		
	}
	
	//@SuppressWarnings("unchecked")
	public Funcionario consultar(long id) {
		return this.em.find(Funcionario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listar() {
		Query q = this.em.createQuery("SELECT c FROM Funcionario c");
		return q.getResultList();
	}
	
	
	
	
	
}

