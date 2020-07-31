package br.com.gm.deveficiente.casadocodigo.novolivro;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class DetalheLivroResponse {

	private String titulo;
	private String resumo;
	private BigDecimal preco;
	private String sumario;
	private DetalheAutorResponse autor;
	private String isbn;
	private int numeroPaginas;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataPublicacao;
	
	public DetalheLivroResponse(Livro livro) {
		this.titulo = livro.getTitulo();
		this.resumo = livro.getResumo();
		this.preco = livro.getPreco();
		this.sumario = livro.getSumario();
		this.autor = new DetalheAutorResponse(livro.getAutor());
		this.isbn = livro.getIsbn();
		this.numeroPaginas = livro.getPaginas();
		dataPublicacao = livro.getDataPublicacao();
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
	public DetalheAutorResponse getAutor() {
		return autor;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	
}
