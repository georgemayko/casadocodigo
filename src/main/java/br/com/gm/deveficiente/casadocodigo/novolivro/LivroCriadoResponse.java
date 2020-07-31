package br.com.gm.deveficiente.casadocodigo.novolivro;

public class LivroCriadoResponse {

	private Long id;
	private String titulo;

	public LivroCriadoResponse(Livro livro) {
		id = livro.getId();
		titulo = livro.getTitulo();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getTitulo() {
		return titulo;
	}

}
