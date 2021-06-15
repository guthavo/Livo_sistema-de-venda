package br.ucb.console;

import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import br.ucb.modelo.Cliente;
import br.ucb.modelo.dao.ClienteDAO;

public class ClienteVC {
	private Scanner teclado;
	private Cliente cliente;
	private List<Cliente> clientes;
	private ClienteDAO clienteDAO;

	public ClienteVC(Scanner teclado) {
		this.teclado = teclado;
		this.clienteDAO = new ClienteDAO();
	}	

	public void menu() {
		int opcao;
		String resposta;
		long codigo;
		
		do {
			System.out.println("\n\n*** Cliente ***\n");
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
				this.cliente = new Cliente();
				this.editar();
				if (this.clienteDAO.incluir(this.cliente))
					System.out.println("Incluído com sucesso");
				else
					System.out.println("Erro de inclusão");
				break;
			case 2:
				System.out.println("\n\n*** Exclusão ***");
				System.out.print("Digite o código do cliente: ");
				codigo = teclado.nextLong();
				this.cliente = this.clienteDAO.consultar(codigo);
				if (this.cliente != null) {
					this.mostrar();
					System.out.print("Confirma exclusão? (s)im/(n)ão: ");
					resposta = this.teclado.next();
					if (resposta.equals("s"))
						this.clienteDAO.excluir(this.cliente);
				}
				else
					System.out.println("cliente não localizado!");
				break;
			case 3:
				System.out.println("\n\n*** Alteração ***");
				System.out.print("Digite o código do cliente: ");
				codigo = teclado.nextLong();
				this.cliente = this.clienteDAO.consultar(codigo);
				if (this.cliente != null) {
					this.mostrar();
					System.out.print("Deseja alterar? (s)im/(n)ão: ");
					resposta = this.teclado.next();
					if (resposta.equals("s")) {
						this.editar();
						this.clienteDAO.alterar(this.cliente);
					}
				}
				else
					System.out.println("cliente não localizado!");
				break;
			case 4:
				System.out.println("\n\n*** Consulta ***");
				System.out.print("Digite o código do cliente: ");
				codigo = teclado.nextLong();
				this.cliente = this.clienteDAO.consultar(codigo);
				if (this.cliente != null)
					this.mostrar();
				else 
					System.out.println("cliente não localizado!");
				break;
			case 5:
				System.out.println("\n\n*** Listagem ***");
				this.clientes = this.clienteDAO.listar();
				System.out.println("Código - Nome");
				for (Cliente c : this.clientes)
					System.out.println(c.getId() +" - "+ c.getNome());
			}
		} while (opcao != 0);		
	}

	public void editar() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.print("Digite o nome do cliente..............: ");
		this.cliente.setNome(this.teclado.next());
		System.out.print("Digite o telefone do cliente..........: ");
		this.cliente.setTelefone(this.teclado.nextInt());
		System.out.print("Digite o e-mail do cliente............: ");
		this.cliente.setEmail(this.teclado.next());
		System.out.print("Digite a data de nascimento do cliente: ");
		try {
			this.cliente.setDtaNasc(sdf.parse(this.teclado.next()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.print("Digite a senha do cliente.............: ");
		this.cliente.setSenha(this.teclado.next());
	}
	
	public void mostrar() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Nome do cliente..............: "+ this.cliente.getNome());
		System.out.println("Telefone do cliente..........: "+ this.cliente.getTelefone());
		System.out.println("E-mail do cliente............: "+ this.cliente.getEmail());
		System.out.println("Data de nascimento do cliente: "+ sdf.format(this.cliente.getDtaNasc()));
		System.out.println("Senha do cliente.............: *****");
	}

}
