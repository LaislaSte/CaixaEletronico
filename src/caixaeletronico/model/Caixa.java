package caixaeletronico.model;

public class Caixa {

	private int[] notas = new int[5];
	private int totalNotas;

	public Caixa(int[] notas) {
		super();
		this.notas = notas;
		this.totalNotas = calcularTotal();
	}

	public int[] getNotas() {
		return notas;
	}

	public void setNotas(int[] notas) {
		this.notas = notas;
	}


	public int getTotalNotas() {
		return totalNotas;
	}

	public void setTotalNotas(int totalNotas) {
		this.totalNotas = totalNotas;
	}

	public int calcularTotal() {
		int total = 0;
		total += notas[0]*100;
		total += notas[1]*50;
		total += notas[2]*20;
		total += notas[3]*10;
		total += notas[4]*5;
		total += notas[5]*2;
		return total;
	}

	public void setNotas2(int notas2) {
		this.notas[5] = notas2;
	}

	public void setNotas5(int notas5) {
		this.notas[4] = notas5;
	}

	public void setNotas10(int notas10) {
		this.notas[3] = notas10;
	}

	public void setNotas20(int notas20) {
		this.notas[2] = notas20;
	}


	public void setNotas50(int notas50) {
		this.notas[1] = notas50;
	}

	public void setNotas100(int notas100) {
		this.notas[0] = notas100;
	}

}
