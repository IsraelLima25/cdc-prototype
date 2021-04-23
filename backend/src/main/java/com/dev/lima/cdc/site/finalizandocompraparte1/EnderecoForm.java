package com.dev.lima.cdc.site.finalizandocompraparte1;

import javax.validation.constraints.NotBlank;

import com.dev.lima.cdc.model.Endereco;

public class EnderecoForm {
	
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
	public Endereco toEndereco() {
		
		return new Endereco(enderecoLogradouro,complemento,pais,estado,cidade,cep);
	}
}
