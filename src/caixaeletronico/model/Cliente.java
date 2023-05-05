package caixaeletronico.model;

public class Cliente {
	private String nome;
	private String rg;
	private String cpf;
	private String endereco;
	private String telefone;
	private String banco;
	private String conta;
	private String senha;
	private double saldo;

	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	

	public Cliente(String cpf, String senha) {
		super();
		this.cpf = cpf;
		this.senha = senha;
	}

	public Cliente(String nome, String cpf, String banco, double saldo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.banco = banco;
		this.saldo = saldo;
	}

	public Cliente(String nome, String rg, String cpf, String endereco, String telefone, String banco, String conta,
			String senha, double saldo) {
		super();
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.banco = banco;
		this.conta = conta;
		this.senha = senha;
		this.saldo = saldo;
	}

	public Cliente(String cpf) {
		super();
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", rg=" + rg + ", cpf=" + cpf + ", endereço=" + endereco + ", telefone="
				+ telefone + ", banco=" + banco + ", conta=" + conta + ", senha=" + senha + ", saldo=" + saldo + "]";
	}

}
