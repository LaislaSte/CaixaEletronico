package caixaeletronico.model;

public class Adiministrador {
	private String login;
	private String senha;

	public Adiministrador() {
		// TODO Auto-generated constructor stub
	}

	public Adiministrador(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Adiministrador [login=" + login + ", senha=" + senha + "]";
	}
	

}
