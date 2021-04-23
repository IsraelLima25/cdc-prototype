package com.dev.lima.cdc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_endereco")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String enderecoLogradouro;
	@NotBlank
	private String complemento;
	@NotBlank
	private String pais;
	@NotBlank
	private String estado;
	@NotBlank
	private String cidade;
	@NotBlank
	private String cep;
	
	@Deprecated
	public Endereco() {
		
	}
	
	public Endereco(@NotBlank String enderecoLogradouro, @NotBlank String complemento, @NotBlank String pais,
			@NotBlank String estado, @NotBlank String cidade, @NotBlank String cep) {
		this.enderecoLogradouro = enderecoLogradouro;
		this.complemento = complemento;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.cep = cep;
	}
	
	public Long getId() {
		return id;
	}

	public String getEnderecoLogradouro() {
		return enderecoLogradouro;
	}

	public void setEnderecoLogradouro(String enderecoLogradouro) {
		this.enderecoLogradouro = enderecoLogradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
}
