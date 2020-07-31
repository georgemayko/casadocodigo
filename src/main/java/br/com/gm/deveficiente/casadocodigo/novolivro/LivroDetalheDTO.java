package br.com.gm.deveficiente.casadocodigo.novolivro;

import java.math.BigDecimal;

public class LivroDetalheDTO {

	private String titulo;
	private String resumo;
	private BigDecimal preco;
	private String sumario;
	private String nomeAutor;
	private String isbn;
	private int numeroPaginas;
	
	public LivroDetalheDTO(String titulo, String resumo, BigDecimal preco, String sumario, String nomeAutor,
			String isbn, int numeroPaginas) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.preco = preco;
		this.sumario = sumario;
		this.nomeAutor = nomeAutor;
		this.isbn = isbn;
		this.numeroPaginas = numeroPaginas;
	}
	
	public LivroDetalheDTO(Livro livro) {
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
