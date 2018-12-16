package br.com.linkdata.myApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Funcionario {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(nullable = false, length = 11)
	private String cpf;

	@Column(nullable = false, length = 100)
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_empresa", nullable = false)
	private Empresa empresa;

	public Funcionario() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Funcionario [codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + "]";
	}

}
