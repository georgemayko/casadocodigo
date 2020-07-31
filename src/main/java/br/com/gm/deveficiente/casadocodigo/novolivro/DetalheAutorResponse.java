package br.com.gm.deveficiente.casadocodigo.novolivro;

import br.com.gm.deveficiente.casadocodigo.novoautor.Autor;

public class DetalheAutorResponse {

	private String nome;
	private String descricao;

	public DetalheAutorResponse(Autor autor) {
		nome = autor.getNome();
		descricao = autor.getDescricao();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
