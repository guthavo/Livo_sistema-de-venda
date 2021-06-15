package br.ucb.modelo;

import java.io.Serializable; 
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="nome")
	private String nome;
	@Column(name="telefone")
	private int telefone;
	@Column(name="email")
	private String email;
	@Temporal(TemporalType.DATE)
	private Date dtaNasc;
	@Column(name="senha")
	private String senha;
	
	
	public Cliente () {
		
	}	
	
	public boolean logar(String senha) {
		return this.senha.equals(senha);
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDtaNasc() {
		return dtaNasc;
	}
	public void setDtaNasc(Date dtaNasc) {
		this.dtaNasc = dtaNasc;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	


	
	
}
