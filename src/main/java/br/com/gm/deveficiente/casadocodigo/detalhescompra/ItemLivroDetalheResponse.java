package br.com.gm.deveficiente.casadocodigo.detalhescompra;

import br.com.gm.deveficiente.casadocodigo.novolivro.Livro;

public class ItemLivroDetalheResponse {

	private String titulo;
	private String isbn;
	
	public ItemLivroDetalheResponse(Livro livro) {
		this.titulo = livro.getTitulo();
		this.isbn = livro.getIsbn();
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getIsbn() {
		return isbn;
	}

}
