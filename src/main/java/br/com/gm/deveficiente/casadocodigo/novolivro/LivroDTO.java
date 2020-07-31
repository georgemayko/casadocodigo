package br.com.gm.deveficiente.casadocodigo.novolivro;

public class LivroDTO {

	private Long id;
	private String titulo;
	
	public LivroDTO(Long id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}
	
	public String getTitulo() {
		return titulo;
	}
}
