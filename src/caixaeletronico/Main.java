package caixaeletronico;

import javax.swing.JOptionPane;

import caixaeletronico.view.ManterCaixa;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public int callOption() {
		int option = Integer.parseInt(JOptionPane.showInputDialog("Insira a opção desejada \n" 
				+ "1 - Cadastrar Cliente \n"
				+ "2 - Consultar Conta(acessada pela senha do administrador) \n"
				+ "3 - Carregar Caixa(acessada pela senha do administrador) \n"
				+ "4 - Saque(acessada pela senha do cliente)\n" 
				+ "5 - Estatística(acessada pela senha do administrador) \n"
				+ "0 - Finalizar operação"));
		return option;
	}

	public static void main(String[] args) {
		ManterCaixa mcx = new ManterCaixa();
		Main m = new Main();

		int option = m.callOption();
		
		System.out.println(option);
		while (option != 0) {
			switch (option) {
			case 1:
				mcx.cadastrarCliente();
				break;
			case 2:
				mcx.consultarConta();
				break;
			case 3:
				mcx.carregarCaixa();
				break;
			case 4:
				mcx.sacar();
				break;
			case 5:
				mcx.gerarEstatistica();
			case 0:
				option = 0;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + option);
			}
			option = m.callOption();
		}
	}

}
