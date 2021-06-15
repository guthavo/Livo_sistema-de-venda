package br.ucb.console;


import java.util.List;
import java.util.Scanner;

import br.ucb.modelo.Categoria;
import br.ucb.modelo.Fornecedor;
import br.ucb.modelo.Produto;
import br.ucb.modelo.dao.CategoriaDAO;
import br.ucb.modelo.dao.FornecedorDAO;
import br.ucb.modelo.dao.ProdutoDAO;

public class ProdutoVC {
	private Scanner teclado;
	private int id;
	private Produto produto;
	private List<Produto> produtos;
	private ProdutoDAO produtoDAO;
	private List<Fornecedor> fornecedores;
	private FornecedorDAO fornecedorDAO;
	private List<Categoria> categorias;
	private CategoriaDAO categoriaDAO;
	
	public ProdutoVC(Scanner teclado) {
		this.teclado = teclado;
		this.produtoDAO = new ProdutoDAO();
		this.categoriaDAO = new CategoriaDAO();
		this.fornecedorDAO = new FornecedorDAO();
	}	

	public void menu() {
		int opcao;
		String resposta;
		long codigo;
		
		do {
			System.out.println("\n\n*** Produto ***\n");
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
				this.produto = new Produto();
				this.editar();
				if (this.produtoDAO.incluir(this.produto))
					System.out.println("Incluído com sucesso");
				else
					System.out.println("Erro de inclusão");
				break;
			case 2:
				System.out.println("\n\n*** Exclusão ***");
				System.out.print("Digite o código do produto: ");
				codigo = teclado.nextLong();
				this.produto = this.produtoDAO.consultar(codigo);
				if (this.produto != null) {
					this.mostrar();
					System.out.print("Confirma exclusão? (s)im/(n)ão: ");
					resposta = this.teclado.next();
					if (resposta.equals("s"))
						this.produtoDAO.excluir(this.produto);
				}
				else
					System.out.println("produto não localizado!");
				break;
			case 3:
				System.out.println("\n\n*** Alteração ***");
				System.out.print("Digite o código do produto: ");
				codigo = teclado.nextLong();
				this.produto = this.produtoDAO.consultar(codigo);
				if (this.produto != null) {
					this.mostrar();
					System.out.print("Deseja alterar? (s)im/(n)ão: ");
					resposta = this.teclado.next();
					if (resposta.equals("s")) {
						this.editar();
						this.produtoDAO.alterar(this.produto);
					}
				}
				else
					System.out.println("produto não localizado!");
				break;
			case 4:
				System.out.println("\n\n*** Consulta ***");
				System.out.print("Digite o código do produto: ");
				codigo = teclado.nextLong();
				this.produto = this.produtoDAO.consultar(codigo);
				if (this.produto != null)
					this.mostrar();
				else 
					System.out.println("produto não localizado!");
				break;
			case 5:
				System.out.println("\n\n*** Listagem ***");
				this.produtos = this.produtoDAO.listar();
				System.out.println("Código - Nome");
				for (Produto c : this.produtos)
					System.out.println(c.getId() +" - "+ c.getModelo());
			}
		} while (opcao != 0);		
	}

	public void editar() {
		System.out.print("Digite o modelo do produto..............: ");
		this.produto.setModelo(this.teclado.next());
		System.out.print("Digite a descrição do produto..........: ");
		this.produto.setDescricao(this.teclado.next());
		System.out.print("Digite o valor do produto............: ");
		this.produto.setValor(this.teclado.nextInt());
		System.out.print("Digite a quantidade em estoque do produto............: ");
		this.produto.setQtdEstoque(this.teclado.nextInt());
		this.fornecedores = this.fornecedorDAO.listar();
		System.out.println("Código - Nome");
		for (Fornecedor fornec : this.fornecedores)
			System.out.println(fornec.getId() +" - "+ fornec.getNome());
		System.out.print("Digite o Id do fornecedor que deseja.............: ");
		this.id = teclado.nextInt();
		for (Fornecedor fornec : this.fornecedores)
			if(id == fornec.getId())
				this.produto.setFornecedor(fornec);
		this.categorias = this.categoriaDAO.listar();
		System.out.println("Código - Nome");
		for (Categoria cat : this.categorias)
			System.out.println(cat.getId() +" - "+ cat.getNome());
		System.out.print("Digite o Id da categoria que deseja.............: ");
		this.id = teclado.nextInt();
		for (Categoria cat : this.categorias)
			if(id == cat.getId())
				this.produto.setCategoria(cat);
		
	}
	
	public void mostrar() {
		System.out.println("Modelo do produto..............: "+ this.produto.getModelo());
		System.out.println("Descricao do produto..........: "+ this.produto.getDescricao());
		System.out.println("valor do produto............: "+ this.produto.getValor());
		System.out.println("Quantidade em estoque do produto........: "+ this.produto.getQtdEstoque());
		System.out.println("Fornecedor do produto............: "+ this.produto.getFornecedor());
		System.out.println("Categoria do produto............: "+ this.produto.getCategoria());
		
	}

}
