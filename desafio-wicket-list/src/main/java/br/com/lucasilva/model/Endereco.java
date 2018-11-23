package br.com.lucasilva.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Endereco")
public class Endereco {
	
	@Id
	@GeneratedValue
	private int id_endereco;
	
	@Column(name="RUA", nullable=false)
	private String rua;
	
	@Column(name="COMPLEMENTO")
	private String complemento;
	
	@Column(name="NUMERO")
	private int num;
	
	@Column(name="UF", nullable=false)
	private String uf;
	
	@Column(name="CIDADE", nullable=false)
	private String cidade;
	
	@Column(name="CEP", nullable=false)
	private Long cep;
	
	@OneToOne(mappedBy="endereco")
	private User user;
	
	public Endereco() {
		rua = "";
		complemento="";
		uf="";
		cidade="";
		
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}
	
	
}
