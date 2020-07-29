package br.com.gm.deveficiente.casadocodigo.novacategoria;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.gm.deveficiente.casadocodigo.validator.UniqueValue;

//1
public class NovaCategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome" )
	private String nome;
	
	@JsonCreator
	public NovaCategoriaRequest(@NotBlank String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
	
	//1
	public Categoria toModel() {
		return new Categoria(this.nome);
	}

	@Override
	public String toString() {
		return "NovaCategoriaRequest [nome=" + nome + "]";
	}
}
