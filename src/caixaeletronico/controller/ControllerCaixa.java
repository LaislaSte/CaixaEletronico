package caixaeletronico.controller;

import caixaeletronico.model.Caixa;
import caixaeletronico.model.Cliente;
import caixaeletronico.model.Estatistica;
import caixaeletronico.model.Saque;

public class ControllerCaixa {

	private Caixa caixa;
	private int valorSaque;
	private int valorRetorno = 0;
	private int tamNotaAux=0;

	public ControllerCaixa(Caixa caixa) {
		super();
		this.caixa = caixa;
	}

	private void verificarConveniencia(Cliente cliente) throws Exception {
		if (valorSaque > cliente.getSaldo()) {
			throw new Exception("Saldo da conta insuficiente");
		} else if (valorSaque > caixa.getTotalNotas()) {
			throw new Exception("Notas insuficinete");
		} else if (valorSaque < 2) {
			throw new Exception("Nao ha notas abaixo de 2");
		} else if (caixa.getNotas() == null ||  caixa.getNotas()[0] ==0
				&& caixa.getNotas()[1] ==0 && caixa.getNotas()[2] ==0
				&& caixa.getNotas()[3] ==0 && caixa.getNotas()[4] ==0
				&& caixa.getNotas()[5] ==0) 
		{
			throw new Exception("Caixa Vazio");
		} else {
			return;
		}
	}
	
	private int verificarDecrementoTESTE(int valorDeSaqueDecrementado, int valorNota, int tamanhoListaNota) throws Exception {
		
		if (valorDeSaqueDecrementado >= valorNota &&  (tamanhoListaNota*valorNota) > valorNota ) {
			valorDeSaqueDecrementado = retirarNotaListaTESTE(tamanhoListaNota, valorNota);
			setarListaReferenteTESTE(valorNota);
		}
		return valorDeSaqueDecrementado;
	}
	
	private int retirarNotaListaTESTE(int tamanhoLista, int nota) throws Exception {
		for (int i = 0; i < tamanhoLista; i++) {
			this.valorRetorno += nota;
			System.out.println("nota "+ nota);
			tamanhoLista--;
			if (valorRetorno > valorSaque) {
				this.valorRetorno -= nota;
				tamanhoLista++;
				break;
			}else if (valorRetorno == valorSaque) {
				break;
			} else if (tamanhoLista == 0) {
				break;
			}
			
		}
		
		
		this.tamNotaAux = tamanhoLista;
		return valorSaque - valorRetorno;
		
	}
	
	private void setarListaReferenteTESTE(int valorNota) throws Exception {
		switch (valorNota) {
		case 100:
			this.caixa.setNotas100(tamNotaAux);
			break;
		case 50:
			this.caixa.setNotas50(tamNotaAux);
			break;
		case 20:
			this.caixa.setNotas20(tamNotaAux);
			break;
		case 10:
			this.caixa.setNotas10(tamNotaAux);
			break;
		case 5:
			this.caixa.setNotas5(tamNotaAux);
			break;
		case 2:
			this.caixa.setNotas2(tamNotaAux);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + valorNota);
		}
	}
	
	public Saque testSacar(Cliente cliente, int valorSaque) throws Exception {
	
		int valorDeSaqueDecrementadoRESTO = valorSaque;
		this.valorSaque = valorSaque;
		verificarConveniencia(cliente);
		
		valorDeSaqueDecrementadoRESTO = verificarDecrementoTESTE(valorDeSaqueDecrementadoRESTO, 100, caixa.getNotas()[0]);
		valorDeSaqueDecrementadoRESTO = verificarDecrementoTESTE(valorDeSaqueDecrementadoRESTO, 50, caixa.getNotas()[1]);
		valorDeSaqueDecrementadoRESTO = verificarDecrementoTESTE(valorDeSaqueDecrementadoRESTO, 20, caixa.getNotas()[2]);
		valorDeSaqueDecrementadoRESTO = verificarDecrementoTESTE(valorDeSaqueDecrementadoRESTO, 10, caixa.getNotas()[3]);
		valorDeSaqueDecrementadoRESTO = verificarDecrementoTESTE(valorDeSaqueDecrementadoRESTO, 5, caixa.getNotas()[4]);
		valorDeSaqueDecrementadoRESTO = verificarDecrementoTESTE(valorDeSaqueDecrementadoRESTO, 2, caixa.getNotas()[5]);
		if (valorDeSaqueDecrementadoRESTO == 1) {
			throw new Exception("Não é possível sacar o solicidado, não há notas para esse valor: " + valorSaque);
		}

		cliente.setSaldo(cliente.getSaldo() - valorRetorno);
		return  new Saque(cliente, valorRetorno);
		
	}
	
	//TODO: diminuir a funcao, quebrar em metodos menores as verificacoes
	public Estatistica gerarEstatistica(Saque[] listaSaques) throws Exception {

		int maior = listaSaques[0].getValorSacado(), menor = listaSaques[0].getValorSacado(), media = 0;
		Cliente maiorCli = listaSaques[0].getCliente(), menorCli = listaSaques[0].getCliente();
		int countItau=0, countBradesco=0, countSantander=0, countHBCS=0;

		if (listaSaques[0] != null) {
			for(int i=0; i < listaSaques.length; i++) {
				System.out.println("primeiro saque feito "+listaSaques[i]);
				if(listaSaques[i] == null) {
					break;
				}
				if(listaSaques[i].getCliente().getBanco().equals("Itau")){
					countItau ++;
				}else if(listaSaques[i].getCliente().getBanco().equals("Bradesco")){
					countBradesco ++;
				}else if(listaSaques[i].getCliente().getBanco().equals("Santander")){
					countSantander ++;
				}else {
					countHBCS++;
				}
				
				maior = listaSaques[i].getValorSacado() > maior ? listaSaques[i].getValorSacado() : maior;
				maiorCli = listaSaques[i].getValorSacado() > maior ? listaSaques[i].getCliente() : maiorCli;
				
				menor = listaSaques[i].getValorSacado() < menor ? listaSaques[i].getValorSacado() : menor;
				menorCli = listaSaques[i].getValorSacado() < menor ? listaSaques[i].getCliente() : menorCli;
				
				media += listaSaques[i].getValorSacado();
			}

			media = media / listaSaques.length;
			Saque maiorSaque = new Saque(maiorCli, maior), menorSaque = new Saque(menorCli, menor);
			String maiorBanco = "", menorBanco="";
			
			if(countItau > countBradesco && countItau > countHBCS && countItau > countSantander ) {
				maiorBanco = "Itau";
			}else if(countBradesco > countItau && countBradesco > countHBCS && countBradesco > countSantander ) {
				maiorBanco = "Bradesco";
			}else if(countSantander> countBradesco && countSantander > countHBCS && countSantander > countItau ) {
				maiorBanco = "Santander";
			}else {
				maiorBanco = "IHBS";
			}
			
			if(countItau < countBradesco && countItau < countHBCS && countItau < countSantander ) {
				menorBanco = "Itau";
			}else if(countBradesco < countItau && countBradesco < countHBCS && countBradesco < countSantander ) {
				menorBanco = "Bradesco";
			}else if(countSantander< countBradesco && countSantander < countHBCS && countSantander < countItau ) {
				menorBanco = "Santander";
			}else {
				menorBanco = "IHBS";
			}
			
			return new Estatistica(menorSaque, maiorSaque, media, maiorBanco, menorBanco);
		} else {
			throw new Exception("Não há saques para gerar estatísticas");
		}

	}

	
	public Caixa getCaixa() {
		return caixa;
	}
}
