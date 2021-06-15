package br.ucb.console;

import java.util.List;
import java.util.Scanner;

import br.ucb.modelo.Categoria;
import br.ucb.modelo.dao.CategoriaDAO;

public class CategoriaVC {
	private Scanner teclado;
	private Categoria categoria;
	private List<Categoria> categorias;
	private CategoriaDAO categoriaDAO;

	public CategoriaVC(Scanner teclado) {
		this.teclado = teclado;
		this.categoriaDAO = new CategoriaDAO();
	}	

	public void menu() {
		int opcao;
		String resposta;
		long codigo;
		
		do {
			System.out.println("\n\n*** Categoria ***\n");
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
				this.categoria = new Categoria();
				this.editar();
				if (this.categoriaDAO.incluir(this.categoria))
					System.out.println("Inclu�do com sucesso");
				else
					System.out.println("Erro de inclus�o");
				break;
			case 2:
				System.out.println("\n\n*** Exclus�o ***");
				System.out.print("Digite o c�digo do categoria: ");
				codigo = teclado.nextLong();
				this.categoria = this.categoriaDAO.consultar(codigo);
				if (this.categoria != null) {
					this.mostrar();
					System.out.print("Confirma exclus�o? (s)im/(n)�o: ");
					resposta = this.teclado.next();
					if (resposta.equals("s"))
						this.categoriaDAO.excluir(this.categoria);
				} 
				else
					System.out.println("cliente n�o localizado!");
				break;
			case 3:
				System.out.println("\n\n*** Altera��o ***");
				System.out.print("Digite o c�digo do categoria: ");
				codigo = teclado.nextLong();
				this.categoria = this.categoriaDAO.consultar(codigo);
				if (this.categoria != null) {
					this.mostrar();
					System.out.print("Deseja alterar? (s)im/(n)�o: ");
					resposta = this.teclado.next();
					if (resposta.equals("s")) {
						this.editar();
						this.categoriaDAO.alterar(this.categoria);
					}
				}
				else
					System.out.println("cliente n�o localizado!");
				break;
			case 4:
				System.out.println("\n\n*** Consulta ***");
				System.out.print("Digite o c�digo do categoria: ");
				codigo = teclado.nextLong();
				this.categoria = this.categoriaDAO.consultar(codigo);
				if (this.categoria != null)
					this.mostrar();
				else
					System.out.println("cliente n�o localizado!");
				break;
			case 5:
				System.out.println("\n\n*** Listagem ***");
				this.categorias = this.categoriaDAO.listar();
				System.out.println("C�digo - Nome");
				for (Categoria cat : this.categorias)
					System.out.println(cat.getId() +" - "+ cat.getNome());
			}
		} while (opcao != 0);		
	}

	public void editar() {
		System.out.print("Digite o nome do categoria..............: ");
		this.categoria.setNome(this.teclado.next());
		
	}
	
	public void mostrar() {
		System.out.println("Nome do cliente..............: "+ this.categoria.getNome());

	}

}


