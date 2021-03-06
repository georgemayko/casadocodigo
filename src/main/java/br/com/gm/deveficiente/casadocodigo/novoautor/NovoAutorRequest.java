package br.com.gm.deveficiente.casadocodigo.novoautor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.gm.deveficiente.casadocodigo.validator.UniqueValue;

//1
public class NovoAutorRequest {

	@NotBlank
	private String nome;
	@NotBlank
	@Email
	@UniqueValue(domainClass = Autor.class, fieldName = "email")
	private String email;
	@NotBlank
	private String descricao;
	
	@JsonCreator
	public NovoAutorRequest(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	//1
	public Autor toModel() {
		return new Autor(this.nome, this.email, this.descricao);
	}

}
