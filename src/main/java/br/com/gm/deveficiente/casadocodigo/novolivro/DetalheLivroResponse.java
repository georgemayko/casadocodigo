package br.com.gm.deveficiente.casadocodigo.novolivro;

import java.math.BigDecimal;

public class DetalheLivroResponse {

	private String titulo;
	private String resumo;
	private BigDecimal preco;
	private String sumario;
	private String nomeAutor;
	private String isbn;
	private int numeroPaginas;
	
	public DetalheLivroResponse(Livro livro) {
		this.titulo = livro.getTitulo();
		this.resumo = livro.getResumo();
		this.preco = livro.getPreco();
		this.sumario = livro.getSumario();
		this.nomeAutor = livro.getAutor().getNome();
		this.isbn = livro.getIsbn();
		this.numeroPaginas = livro.getPaginas();
	}
	
	public String getTitulo() {
		return titulo;
	}
	public String getResumo() {
		return resumo;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public String getSumario() {
		return sumario;
	}
	public String getNomeAutor() {
		return nomeAutor;
	}
	public String getIsbn() {
		return isbn;
	}
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	
}
