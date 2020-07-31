package br.com.gm.deveficiente.casadocodigo.novolivro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.gm.deveficiente.casadocodigo.novacategoria.Categoria;
import br.com.gm.deveficiente.casadocodigo.novoautor.Autor;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String titulo;
	private @NotBlank @Size(max = 500) String resumo;
	@Lob
	private String sumario;
	private @NotNull @Min(20) BigDecimal preco;
	private @NotNull @Min(100) Integer paginas;
	private @NotBlank String isbn;
	private @Future @NotNull LocalDate dataPublicacao;
	@ManyToOne
	private Autor autor;
	@ManyToOne
	private Categoria categoria;
	
	@Deprecated
	public Livro() { }

	public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer paginas, @NotBlank String isbn,
			@Future @NotNull LocalDate dataPublicacao, @Valid Autor autor, @Valid Categoria categoria) {
				this.titulo = titulo;
				this.resumo = resumo;
				this.sumario = sumario;
				this.preco = preco;
				this.paginas = paginas;
				this.isbn = isbn;
				this.dataPublicacao = dataPublicacao;
				this.autor = autor;
				this.categoria = categoria;
	}

	public Long getId() {
		return id;
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
	
	public Integer getPaginas() {
		return paginas;
	}
	
	public Autor getAutor() {
		return autor;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", resumo=" + resumo + ", sumario=" + sumario + ", preco="
				+ preco + ", paginas=" + paginas + ", isbn=" + isbn + ", dataLancamento=" + dataPublicacao + ", autor="
				+ autor + ", categoria=" + categoria + "]";
	}
	
	

}
