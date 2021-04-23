package com.dev.lima.cdc.site.finalizandocompraparte1;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

import com.dev.lima.cdc.model.Compra;
import com.dev.lima.cdc.model.Endereco;
import com.dev.lima.cdc.model.ItemCompra;

public class DadosCompradorForm {

	@Email
	@NotBlank
	private String email;
	private String nome;	
	@NotBlank
	private String sobreNome;
	@CpfCnpj
	private String documento;
	@NotBlank
	private String telefone;	
	private String codigoCupom;
	@Valid
	@NotNull
	private EnderecoForm endereco;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobreNome() {
		return sobreNome;
	}
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public EnderecoForm getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoForm endereco) {
		this.endereco = endereco;
	}
	
	public String getCodigoCupom() {
		return codigoCupom;
	}
	
	public void setCodigoCupom(String codigoCupom) {
		this.codigoCupom = codigoCupom;
	}
	
	public Compra novaCompra(Set<ItemCompra> itens,CupomRepository cupomRepository) {
		
		Endereco enderecoCompra = endereco.toEndereco();
		Compra compra = new Compra(email,nome,sobreNome,documento,telefone,enderecoCompra,itens);
		
		if(StringUtils.hasText(codigoCupom)) {
			compra.setCupom(cupomRepository.findByCodigo(codigoCupom).get());
			compra.aplicarCupom();
		}
		return compra;
	}
	
	
}
