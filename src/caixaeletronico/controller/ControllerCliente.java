package caixaeletronico.controller;

import javax.swing.JOptionPane;

import caixaeletronico.model.Adiministrador;
import caixaeletronico.model.Cliente;

public class ControllerCliente {

	private Cliente[] listaCliente;
	private Cliente clienteEncontrado = null;
	private int idxClienteEncontrado = 0;

	public ControllerCliente(Cliente[] clientes) {
		listaCliente = clientes;
	}

	public boolean validarCliente(Cliente cliente) {
		boolean result = false;

		for (int i = 0; i < listaCliente.length; i++) {
			
			if (listaCliente[i].getCpf().equals(cliente.getCpf()) && listaCliente[i].getSenha().equals(cliente.getSenha()) ) {
				this.clienteEncontrado = listaCliente[i]; 
				this.idxClienteEncontrado = i;
				result=true; 
				break; 
			} else { 
				result = false; 
				continue; 
			} 
		}
		 return result;
	}

	public Cliente consultarCliente(Adiministrador adm) throws Exception {
		Cliente clienteSaida = null;
		String cpf = JOptionPane.showInputDialog(null, "CPF para consulta");
		Cliente clienteVeficacao = new Cliente(cpf);

		for (int i = 0; i < listaCliente.length; i++) {
			if (listaCliente[i].getCpf().equals(clienteVeficacao.getCpf())) {
				clienteSaida = listaCliente[i];
				break;
			} else {
				continue;
			}
		}

		if (clienteSaida == null) {
			throw new Exception("Cliente nao encontrado");
		} else {
			return clienteSaida;
		}

	}

	public Cliente getClienteEncontrado() {
		return clienteEncontrado;
	}

	public int getIdxClienteEncontrado() {
		return idxClienteEncontrado;
	}

	public Cliente[] getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(Cliente[] listaCliente) {
		this.listaCliente = listaCliente;
	}

	public ControllerCliente() {
		super();
	}

	public String getBanco() {
		int option = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha um banco de acordo com as opcoes \n"
				+ "1 - Itau \n" + "2 - Santander \n" + "3 - Bradesco \n" + "4 - HSBC"));
		switch (option) {
		case 1: {
			return "Itau";
		}
		case 2: {
			return "Santander";
		}
		case 3: {
			return "Bradesco";
		}
		case 4: {
			return "HSBC";
		}
		default:
			throw new IllegalArgumentException("Valor Inesperado: " + option);
		}
	}

}
