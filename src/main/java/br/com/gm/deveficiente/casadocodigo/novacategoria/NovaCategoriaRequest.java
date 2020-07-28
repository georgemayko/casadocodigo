package br.com.gm.deveficiente.casadocodigo.novacategoria;

import javax.validation.constraints.NotBlank;

//1
public class NovaCategoriaRequest {

	@NotBlank
	private String nome;

	public String getNome() {
		return this.nome;
	}
	
	//1
	public Categoria toModel() {
		return new Categoria(this.nome);
	}
}
