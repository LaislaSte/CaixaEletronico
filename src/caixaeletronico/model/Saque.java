package caixaeletronico.model;

public class Saque {
	private Cliente cliente;
	private int valorSacado;

	public Saque(Cliente cliente, int valorSacado) {
		super();
		this.cliente = cliente;
		this.valorSacado = valorSacado;
	}

	public Saque() {
		// TODO Auto-generated constructor stub
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getValorSacado() {
		return valorSacado;
	}

	public void setValorSacado(int valorSacado) {
		this.valorSacado = valorSacado;
	}

	@Override
	public String toString() {
		return "Saque [cliente=" + cliente + ", valorSacado=" + valorSacado + "]";
	}

}
