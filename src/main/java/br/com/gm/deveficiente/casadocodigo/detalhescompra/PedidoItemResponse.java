package br.com.gm.deveficiente.casadocodigo.detalhescompra;

import java.math.BigDecimal;

import br.com.gm.deveficiente.casadocodigo.processocompra.ItemPedido;

public class PedidoItemResponse {

	private Integer quantidade;
	private BigDecimal preco;
	private ItemLivroDetalheResponse livro;

	public PedidoItemResponse(ItemPedido item) {
		livro = new ItemLivroDetalheResponse(item.getLivro());
		preco = item.getPrecoMomento();
		quantidade = item.getQuantidade();
	}
	
	public ItemLivroDetalheResponse getLivro() {
		return livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
}
