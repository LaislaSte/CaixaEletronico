package caixaeletronico.view;

import javax.swing.JOptionPane;

import caixaeletronico.controller.ControllerAdm;
import caixaeletronico.controller.ControllerCaixa;
import caixaeletronico.controller.ControllerCliente;
import caixaeletronico.model.Adiministrador;
import caixaeletronico.model.Caixa;
import caixaeletronico.model.Cliente;
import caixaeletronico.model.Estatistica;
import caixaeletronico.model.Saque;

public class ManterCaixa {

	private Caixa caixaCheio;
	private Cliente[] clientes = new Cliente[50];
	private int idxAuxCli = 0;
	private Saque[] saquesFeitos = new Saque[50];
	private int idxAuxSaq = 0;

	public void cadastrarCliente() {
		ControllerAdm ca = new ControllerAdm();
		ControllerCliente cc = new ControllerCliente(clientes);

		String loginAdm = JOptionPane.showInputDialog(null, "Login do ADM");
		String senhaAdm = JOptionPane.showInputDialog(null, "Senha do ADM");
		boolean acessoLiberado = ca.validarAdm(new Adiministrador(loginAdm, senhaAdm));

		if (acessoLiberado) {
			String nome = JOptionPane.showInputDialog(null, "Nome");
			String rg = JOptionPane.showInputDialog(null, "RG");
			String cpf = JOptionPane.showInputDialog(null, "CPF");
			String endereco = JOptionPane.showInputDialog(null, "Endereço");
			String telefone = JOptionPane.showInputDialog(null, "Telefone");
			String banco = cc.getBanco();
			String conta = JOptionPane.showInputDialog(null, "Conta");
			String senha = JOptionPane.showInputDialog(null, "Senha");
			double saldo = Double.parseDouble(JOptionPane.showInputDialog(null, "Saldo"));
			Cliente cli = new Cliente(nome, rg, cpf, endereco, telefone, banco, conta, senha, saldo);

			clientes[idxAuxCli] = cli;
			JOptionPane.showMessageDialog(null, "Cliente adicionado \n" + clientes[idxAuxCli].toString());
			this.idxAuxCli++;

		} else {
			JOptionPane.showMessageDialog(null, "Acesso negado!");
		}
	}

	public void consultarConta() {
		if (clientes[0] != null) {

			int simOuNao = JOptionPane.showConfirmDialog(null, "Acessar como Administrador?");
			Cliente cli = null;
			ControllerCliente cc = new ControllerCliente(clientes);
			try {

				if (simOuNao == 0) {
					ControllerAdm ca = new ControllerAdm();
					String loginAdm = JOptionPane.showInputDialog(null, "Login do ADM");
					String senhaAdm = JOptionPane.showInputDialog(null, "Senha do ADM");
					boolean acessoLiberado = ca.validarAdm(new Adiministrador(loginAdm, senhaAdm));

					if (acessoLiberado) {
						cli = cc.consultarCliente(new Adiministrador(loginAdm, senhaAdm));
					}else {
						JOptionPane.showInputDialog(null, "Acesso negado!");
					}

				} else if (simOuNao == 1) {
					String login = JOptionPane.showInputDialog(null, "Login (CPF)");
					String senha = JOptionPane.showInputDialog(null, "Senha");
					boolean cliValidado = cc.validarCliente(new Cliente(login, senha));
					if (cliValidado == true) {
						cli = cc.getClienteEncontrado();
					}

				} else {
					JOptionPane.showMessageDialog(null, "opcao invalida. Processo encerrado!");
				}


			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "nao encontrado cliente: " + cli);
				System.out.println("Ocorreu um erro: " + e);
			}


			JOptionPane.showMessageDialog(null, cli);
		} else {
			System.out.println("Não ha nenhum cliente cadastrado ainda");
		}
	}

	public void carregarCaixa() {
		ControllerAdm ca = new ControllerAdm();
		String loginAdm = JOptionPane.showInputDialog(null, "Login do ADM");
		String senhaAdm = JOptionPane.showInputDialog(null, "Senha do ADM");
		boolean acessoLiberado = ca.validarAdm(new Adiministrador(loginAdm, senhaAdm));

		if (acessoLiberado) {
			int nota2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de notas de R$2,00"));
			int nota5 = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de notas de R$5,00"));
			int nota10 = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de notas de R$10,00"));
			int nota20 = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de notas de R$20,00"));
			int nota50 = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de notas de R$50,00"));
			int nota100 = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de notas de R$100,00"));
			
			int notas[] = {nota100, nota50, nota20, nota10, nota5, nota2};
			this.caixaCheio = new Caixa(notas);
			
			//this.caixaCheio = new Caixa(nota2, nota5, nota10, nota20, nota50, nota100);
			JOptionPane.showMessageDialog(null, "Caixa cheio!");
		} else {
			JOptionPane.showMessageDialog(null, "Acesso negado!");
		}
	}

	public void sacar() {
		ControllerCliente cc = new ControllerCliente(clientes);
		ControllerCaixa ccx = new ControllerCaixa(caixaCheio);
		
		
		if (clientes[0] != null) {

				String login = JOptionPane.showInputDialog(null, "CPF do Cliente");
				String senha = JOptionPane.showInputDialog(null, "Senha do Cliente");
				Cliente cliente = new Cliente(login, senha);
				
				boolean cliValidado = cc.validarCliente(new Cliente(login, senha));
				
				if (cliValidado) {
					cliente = cc.getClienteEncontrado();

					int saldoRetirar = Integer.parseInt(JOptionPane.showInputDialog(null,
							"Saldo disponivel: " + cliente.getSaldo() + "\n " + "Quantidade disponivel no caixa: \n"
									+ ccx.getCaixa().getTotalNotas()
									+ "\n \n Por favor digite o Valor de Saque Desejado(numero inteiro!)"));
					Saque result=null;
					
					try {
						result = ccx.testSacar(cliente, saldoRetirar);						
						saquesFeitos[idxAuxSaq] = result;
						this.idxAuxSaq++;
						this.caixaCheio = ccx.getCaixa();
						this.caixaCheio.setTotalNotas(this.caixaCheio.calcularTotal());
						
						cliente.setSaldo(result.getCliente().getSaldo());
						this.clientes[cc.getIdxClienteEncontrado()] = cliente;
						
						JOptionPane.showMessageDialog(null,
								"Valor Sacado: " + result.getValorSacado() + "\n Total atual do caixa apos saque"
										+ caixaCheio.getTotalNotas() + "\n salto atual do cliente"
										+ result.getCliente().getSaldo());
					} catch (Exception e) {
						System.out.println("Erro do saque: "+e);
					}

				} else {
					System.out.println("Login ou senha invalidos");
				}

			
		} else {
			System.out.println("nao ha conveniencia, nao ha cliente ou alguma nota em caixa");
		}

	}

	public void gerarEstatistica() {
		ControllerAdm ca = new ControllerAdm();
		ControllerCaixa ccx = new ControllerCaixa(caixaCheio);
		String loginAdm = JOptionPane.showInputDialog(null, "Login do ADM");
		String senhaAdm = JOptionPane.showInputDialog(null, "Senha do ADM");
		boolean acessoLiberado = ca.validarAdm(new Adiministrador(loginAdm, senhaAdm));

		if (acessoLiberado) {
			try {
				Estatistica estatisticas = ccx.gerarEstatistica(saquesFeitos);
				JOptionPane.showMessageDialog(null, 
						"Maior saque: "+ estatisticas.getSaqueMaior() + "\n" 
						+ "Menor saque: "+ estatisticas.getSaqueMenor() + "\n"
						+ "Media total dos saques: "+ estatisticas.getSaqueMedia() + "\n"
						+ "Maior numero de saques do banco: "+ estatisticas.getMaiorBanco() + "\n"
						+ "Menor numero de saques do banco: "+ estatisticas.getMenorBanco() + "\n"
				);

			} catch (Exception e) {
				System.out.println("ManterCaixa.gerarEstatistica() Erro: " + e);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Login ou Senha inválidos");
		}
	}

}
