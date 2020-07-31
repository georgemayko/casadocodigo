package br.com.gm.deveficiente.casadocodigo.novolivro;

public class LivroListagemResponse {

	private Long id;
	private String titulo;
	
	public LivroListagemResponse(Livro livro) {
		super();
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
	}

	public Long getId() {
		return id;
	}
	
	public String getTitulo() {
		return titulo;
	}
}
