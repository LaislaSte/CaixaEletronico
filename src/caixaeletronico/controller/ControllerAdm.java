package caixaeletronico.controller;

import javax.swing.JOptionPane;

import caixaeletronico.model.Adiministrador;

public class ControllerAdm {

	private Adiministrador[] admsCadastrados = { 
			new Adiministrador("julia", "123456"),
			new Adiministrador("Luciano dos Santos", "123456") 
			};

	public boolean validarAdm(Adiministrador adm) {
		boolean admValido = false;
		int counter=0;
		
		while(counter <3) {
			
			if(counter == 0) {
				if(acharAdm(adm) != null) {
					admValido =true;
					break;
				}else {
					JOptionPane.showMessageDialog(null, "Login e senha invalidos, tente novamente");
					counter++;
				}				
			}else {
				String loginAdm = JOptionPane.showInputDialog(null, "Login do ADM");
				String senhaAdm = JOptionPane.showInputDialog(null, "Senha do ADM");
				if(acharAdm(new Adiministrador(loginAdm, senhaAdm)) != null) {
					admValido =true;
					break;
				}else {
					JOptionPane.showMessageDialog(null, "Login e senha invalidos, tente novamente");
					counter++;
				}
			}
			
		}

		return admValido;
	}
	
	private Adiministrador acharAdm(Adiministrador adm) {
		Adiministrador admr =null;
		for(int i=0; i< admsCadastrados.length; i++) {
			if(admsCadastrados[i].getSenha().equals(adm.getSenha()) && admsCadastrados[i].getLogin().equals(adm.getLogin())) {
				admr = admsCadastrados[i];
				break;
			}else {
				continue;
			}
		}
		return admr;
	}
}
