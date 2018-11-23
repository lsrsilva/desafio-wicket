package br.com.lucasilva.model;

public class Medico extends User {

	private static final long serialVersionUID = 6209365040567430329L;

	private int numCRM;
	private int ufCRM;
	private String especialidade;
	
	public Medico() {
	}

	public int getNumCRM() {
		return numCRM;
	}

	public void setNumCRM(int numCRM) {
		this.numCRM = numCRM;
	}

	public int getUfCRM() {
		return ufCRM;
	}

	public void setUfCRM(int ufCRM) {
		this.ufCRM = ufCRM;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
}
