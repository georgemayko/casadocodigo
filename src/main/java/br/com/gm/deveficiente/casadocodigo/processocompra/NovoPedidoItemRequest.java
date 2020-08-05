package br.com.gm.deveficiente.casadocodigo.processocompra;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.gm.deveficiente.casadocodigo.novolivro.Livro;
import br.com.gm.deveficiente.casadocodigo.validator.ExistId;

public class NovoPedidoItemRequest {

	@NotNull
	@ExistId(domainClass = Livro.class)
	private Long idLivro;
	@NotNull
	@Positive
	private Integer quantidade;
	
	public NovoPedidoItemRequest(@NotNull Long idLivro, @NotNull @Positive Integer quantidade) {
		super();
		this.idLivro = idLivro;
		this.quantidade = quantidade;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public Long getIdLivro() {
		return idLivro;
	}
	
	
	public ItemPedido toModel(EntityManager manager) {
		Livro livro = manager.find(Livro.class, this.idLivro);
		Assert.notNull(livro, "Livro não encontrado para o id: " + this.idLivro);
		return new ItemPedido(livro, this.quantidade);
	}
	
	
}
