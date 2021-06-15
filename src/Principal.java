import java.util.Scanner;

import br.ucb.console.CategoriaVC;
import br.ucb.console.ClienteVC;
import br.ucb.console.FornecedorVC;
import br.ucb.console.FuncionarioVC;
import br.ucb.console.ProdutoVC;
import br.ucb.modelo.dao.ConnectionFactory;

public class Principal {
	public static void main(String[] args) {
		
		int opcao;
		Scanner teclado = new Scanner(System.in);
		ClienteVC clienteVC = new ClienteVC(teclado);
		FornecedorVC fornecedorVC = new FornecedorVC(teclado);
		CategoriaVC categoriaVC = new CategoriaVC(teclado);
		FuncionarioVC funcionarioVC = new FuncionarioVC(teclado);
		ProdutoVC produtoVC = new ProdutoVC(teclado);
		
			do {
				opcao = Principal.menu(teclado);
				switch (opcao) {
					case 1: 
						clienteVC.menu(); 
						break;	
					case 2: 
						fornecedorVC.menu();
						break;
					case 3:
						categoriaVC.menu();
						break;
					case 4:
						funcionarioVC.menu();
						break;
					case 5:
						produtoVC.menu();
						break;
				}
			}while (opcao != 0);
			
			System.out.println("Uso do sistema encerrado!");
			ConnectionFactory.close();
	}	

	public static int menu(Scanner teclado) {
		int opcao;
		System.out.println("\n\n*** MENU ***\n");
		System.out.println("1. Cliente");
		System.out.println("2. Fornecedor");
		System.out.println("3. Categoria");
		System.out.println("4. Funcionario");
		System.out.println("5. Produto");		
		System.out.println("0. Sair");
		System.out.print("\nDigite sua opção: ");
		opcao = teclado.nextInt();
		return opcao;
	}
	   
}  


