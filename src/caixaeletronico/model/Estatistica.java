package caixaeletronico.model;

public class Estatistica {

	private Saque saqueMenor;
	private Saque saqueMaior;
	private int saqueMedia;
	private String maiorBanco;
	private String menorBanco;

	public Estatistica() {
		// TODO Auto-generated constructor stub
	}

	public Estatistica(Saque saqueMenor, Saque saqueMaior, int saqueMedia, String maiorBanco, String menorBanco) {
		super();
		this.saqueMenor = saqueMenor;
		this.saqueMaior = saqueMaior;
		this.saqueMedia = saqueMedia;
		this.maiorBanco = maiorBanco;
		this.menorBanco = menorBanco;
	}

	public String getMaiorBanco() {
		return maiorBanco;
	}

	public String getMenorBanco() {
		return menorBanco;
	}

	public Saque getSaqueMenor() {
		return saqueMenor;
	}

	public Saque getSaqueMaior() {
		return saqueMaior;
	}

	public int getSaqueMedia() {
		return saqueMedia;
	}

}
