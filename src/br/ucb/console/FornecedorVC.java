package br.ucb.console;


import java.util.List; 
import java.util.Scanner;

import br.ucb.modelo.Fornecedor;
import br.ucb.modelo.dao.FornecedorDAO;

public class FornecedorVC {
	private Scanner teclado;
	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores;
	private FornecedorDAO fornecedorDAO;

	public FornecedorVC(Scanner teclado) {
		this.teclado = teclado;
		this.fornecedorDAO = new FornecedorDAO();
	}	

	public void menu() {
		int opcao;
		String resposta;
		long codigo;
		
		do {
			System.out.println("\n\n*** Fornecedor ***\n");
			System.out.println("1. Incluir");
			System.out.println("2. Excluir");
			System.out.println("3. Alterar");
			System.out.println("4. Consultar");
			System.out.println("5. Listar");
			System.out.println("0. Voltar");
			System.out.print("\nDigite sua op��o: ");
			opcao = this.teclado.nextInt();
			switch (opcao) {
			case 1:
				System.out.println("\n\n*** Inclus�o ***");
				this.fornecedor = new Fornecedor();
				this.editar();
				if (this.fornecedorDAO.incluir(this.fornecedor))
					System.out.println("Inclu�do com sucesso");
				else
					System.out.println("Erro de inclus�o");
				break;
			case 2:
				System.out.println("\n\n*** Exclus�o ***");
				System.out.print("Digite o c�digo do fornecedor: ");
				codigo = teclado.nextLong();
				this.fornecedor = this.fornecedorDAO.consultar(codigo);
				if (this.fornecedor != null) {
					this.mostrar();
					System.out.print("Confirma exclus�o? (s)im/(n)�o: ");
					resposta = this.teclado.next();
					if (resposta.equals("s"))
						this.fornecedorDAO.excluir(this.fornecedor);
				}
				else
					System.out.println("cliente n�o localizado!");
				break;
			case 3:
				System.out.println("\n\n*** Altera��o ***");
				System.out.print("Digite o c�digo do fornecedor: ");
				codigo = teclado.nextLong();
				this.fornecedor = this.fornecedorDAO.consultar(codigo);
				if (this.fornecedor != null) {
					this.mostrar();
					System.out.print("Deseja alterar? (s)im/(n)�o: ");
					resposta = this.teclado.next();
					if (resposta.equals("s")) {
						this.editar();
						this.fornecedorDAO.alterar(this.fornecedor);
					}
				}
				else
					System.out.println("cliente n�o localizado!");
				break;
			case 4:
				System.out.println("\n\n*** Consulta ***");
				System.out.print("Digite o c�digo do fornecedor: ");
				codigo = teclado.nextLong();
				this.fornecedor = this.fornecedorDAO.consultar(codigo);
				if (this.fornecedor != null)
					this.mostrar();
				else
					System.out.println("cliente n�o localizado!");
				break;
			case 5:
				System.out.println("\n\n*** Listagem ***");
				this.fornecedores = this.fornecedorDAO.listar();
				System.out.println("C�digo - Nome");
				for (Fornecedor fornec : this.fornecedores)
					System.out.println(fornec.getId() +" - "+ fornec.getNome());
			}
		} while (opcao != 0);		
	}

	public void editar() {
		System.out.print("Digite o nome do fornecedor..............: ");
		this.fornecedor.setNome(this.teclado.next());
		System.out.print("Digite o e-mail do fornecedor............: ");
		this.fornecedor.setEmail(this.teclado.next());
		System.out.print("Digite a senha do fornecedor.............: ");
		this.fornecedor.setSenha(this.teclado.next());
	}
	
	public void mostrar() {
		System.out.println("Nome do fornecedor..............: "+ this.fornecedor.getNome());
		System.out.println("E-mail do fornecedor............: "+ this.fornecedor.getEmail());
		System.out.println("Senha do fornecedor.............: *****");
	}

}
