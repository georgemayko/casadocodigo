package br.com.gm.deveficiente.casadocodigo.novolivro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import br.com.gm.deveficiente.casadocodigo.novacategoria.Categoria;
import br.com.gm.deveficiente.casadocodigo.novoautor.Autor;
import br.com.gm.deveficiente.casadocodigo.validator.ExistId;
import br.com.gm.deveficiente.casadocodigo.validator.UniqueValue;

public class LivroRequest {

	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "titulo")
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String resumo;
	private String sumario;
	@NotNull
	@Min(value = 20)
	private BigDecimal preco;
	@NotNull
	@Min(value = 100)
	private Integer paginas;
	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "isbn")
	private String isbn;
	@Future
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataPublicacao;
	@NotNull
	@ExistId(domainClass = Autor.class)
	private Long idAutor;
	@NotNull
	@ExistId(domainClass = Categoria.class)
	private Long idCategoria;
	
	@JsonCreator//(mode = Mode.PROPERTIES)
	public LivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 250) String resumo, String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer paginas, @NotBlank String isbn,
			@NotNull @Future LocalDate dataPublicacao, @NotNull Long idAutor, @NotNull Long idCategoria) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.paginas = paginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.idAutor = idAutor;
		this.idCategoria = idCategoria;
	}
	
//	public void setDataPublicacao(LocalDate dataPublicacao) {
//		this.dataPublicacao = dataPublicacao;
//	}
	
	public Long getIdAutor() {
		return idAutor;
	}
	
	public Long getIdCategoria() {
		return idCategoria;
	}
	
	public Livro toModel(EntityManager entityManager) {
		@NotNull Autor autor = entityManager.find(Autor.class, this.idAutor);
		@NotNull Categoria categoria = entityManager.find(Categoria.class,this.idCategoria);
		
		Assert.state(autor != null, "Autor não encontrado para o Id: " + this.idAutor);
		Assert.state(categoria != null, "Categoria não encontrada para o Id: " + this.idCategoria);
		
		return new Livro(titulo, resumo, sumario, preco,
				paginas, isbn, dataPublicacao, 
				autor, categoria);
	}

	
}
