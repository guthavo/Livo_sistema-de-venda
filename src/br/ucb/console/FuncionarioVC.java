package br.ucb.console;

import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import br.ucb.modelo.Funcionario;
import br.ucb.modelo.dao.FuncionarioDAO;

public class FuncionarioVC {
	private Scanner teclado;
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private FuncionarioDAO funcionarioDAO;

	public FuncionarioVC(Scanner teclado) {
		this.teclado = teclado;
		this.funcionarioDAO = new FuncionarioDAO();
	}	

	public void menu() {
		int opcao;
		String resposta;
		long codigo;
		
		do {
			System.out.println("\n\n*** Funcionario ***\n");
			System.out.println("1. Incluir");
			System.out.println("2. Excluir");
			System.out.println("3. Alterar");
			System.out.println("4. Consultar");
			System.out.println("5. Listar");
			System.out.println("0. Voltar");
			System.out.print("\nDigite sua opção: ");
			opcao = this.teclado.nextInt();
			switch (opcao) {
			case 1:
				System.out.println("\n\n*** Inclusão ***");
				this.funcionario = new Funcionario();
				this.editar();
				if (this.funcionarioDAO.incluir(this.funcionario))
					System.out.println("Incluído com sucesso");
				else
					System.out.println("Erro de inclusão");
				break;
			case 2:
				System.out.println("\n\n*** Exclusão ***");
				System.out.print("Digite o código do funcionario: ");
				codigo = teclado.nextLong();
				this.funcionario = this.funcionarioDAO.consultar(codigo);
				if (this.funcionario != null) {
					this.mostrar();
					System.out.print("Confirma exclusão? (s)im/(n)ão: ");
					resposta = this.teclado.next();
					if (resposta.equals("s"))
						this.funcionarioDAO.excluir(this.funcionario);
				}
				else
					System.out.println("funcionario não localizado!");
				break;
			case 3:
				System.out.println("\n\n*** Alteração ***");
				System.out.print("Digite o código do funcionario: ");
				codigo = teclado.nextLong();
				this.funcionario = this.funcionarioDAO.consultar(codigo);
				if (this.funcionario != null) {
					this.mostrar();
					System.out.print("Deseja alterar? (s)im/(n)ão: ");
					resposta = this.teclado.next();
					if (resposta.equals("s")) {
						this.editar();
						this.funcionarioDAO.alterar(this.funcionario);
					}
				}
				else
					System.out.println("funcionario não localizado!");
				break;
			case 4:
				System.out.println("\n\n*** Consulta ***");
				System.out.print("Digite o código do funcionario: ");
				codigo = teclado.nextLong();
				this.funcionario = this.funcionarioDAO.consultar(codigo);
				if (this.funcionario != null)
					this.mostrar();
				else
					System.out.println("funcionario não localizado!");
				break;
			case 5:
				System.out.println("\n\n*** Listagem ***");
				this.funcionarios = this.funcionarioDAO.listar();
				System.out.println("Código - Nome");
				for (Funcionario c : this.funcionarios)
					System.out.println(c.getId() +" - "+ c.getNome());
			}
		} while (opcao != 0);		
	}

	public void editar() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.print("Digite o nome do funcionario..............: ");
		this.funcionario.setNome(this.teclado.next());
		System.out.print("Digite o telefone do funcionario..........: ");
		this.funcionario.setTelefone(this.teclado.nextInt());
		System.out.print("Digite o e-mail do funcionario............: ");
		this.funcionario.setEmail(this.teclado.next());
		System.out.print("Digite a data de nascimento do funcionario: ");
		try {
			this.funcionario.setDtaNasc(sdf.parse(this.teclado.next()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.print("Digite a senha do funcionario.............: ");
		this.funcionario.setSenha(this.teclado.next());
	}
	
	public void mostrar() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Nome do funcionario..............: "+ this.funcionario.getNome());
		System.out.println("Telefone do funcionario..........: "+ this.funcionario.getTelefone());
		System.out.println("E-mail do funcionario............: "+ this.funcionario.getEmail());
		System.out.println("Data de nascimento do funcionario: "+ sdf.format(this.funcionario.getDtaNasc()));
		System.out.println("Senha do funcionario.............: *****");
	}

}
