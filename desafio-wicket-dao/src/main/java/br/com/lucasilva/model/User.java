package br.com.lucasilva.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "User", uniqueConstraints = { @UniqueConstraint(columnNames = { "cpf", "email" }) })
public class User implements Serializable {// extends Tabela

	private static final long serialVersionUID = 7206208032888565095L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id_user;

	@Column(name = "NOME", nullable = false, length = 100)
	protected String nome;

	@Column(name = "SBNM", nullable = false, length = 150)
	protected String sbnm;

	@Column(name = "CPF", nullable = false, length = 11)
	protected Long cpf;

	@Column(name = "RG", nullable = false, length = 8)
	protected Long rg;

	@Column(name = "TELEFONE", nullable = false, length = 11)
	protected Long telefone;

	@Column(name = "EMAIL", nullable = false, length = 200)
	protected String email;

	@Column(name = "SEXO", nullable = false, length = 1)
	protected String sexo;

	@Column(name = "SENHA", nullable = false)
	protected String senha;

	@OneToOne
	@MapsId
	@ForeignKey(name = "FK_ID_ENDERECO")
	protected Endereco endereco;

	protected boolean termos;

	@Column(name = "OBS")
	protected String obs;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATANASC")
	protected Date dataNasc;

	public User() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSbnm() {
		return sbnm;
	}

	public void setSbnm(String sbnm) {
		this.sbnm = sbnm;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Long getRg() {
		return rg;
	}

	public void setRg(Long rg) {
		this.rg = rg;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * public int getIdade() { return idade; }
	 * 
	 * public void setIdade(int idade) { this.idade = idade; }
	 */

	public String getSexo() {
		if (sexo == "Masculino")
			return "M";
		else
			return "F";
	}

	public void setSexo(String sexo) {
		if (sexo == "Masculino")
			this.sexo = "M";
		else
			this.sexo = "F";
	}

	@Column(name = "TERMOS", nullable = false)
	public String getTermos() {
		return termos == true ? "A" : "NA";
	}

	public void setTermos(boolean termos) {
		this.termos = termos;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		DateFormat format = DateFormat.getInstance();
		format.format(dataNasc);
		this.dataNasc = dataNasc;
	}
}