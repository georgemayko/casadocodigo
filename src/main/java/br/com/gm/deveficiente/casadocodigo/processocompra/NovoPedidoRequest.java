package br.com.gm.deveficiente.casadocodigo.processocompra;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

public class NovoPedidoRequest {

	@NotNull
	@Positive
	private BigDecimal total;
	@Size(min = 1)
	@Valid
	private List<NovoPedidoItemRequest> itens;

	
	public NovoPedidoRequest(@NotNull @Positive BigDecimal total, @NotEmpty List<NovoPedidoItemRequest> itens) {
		super();
		this.total = total;
		this.itens = itens;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
	public List<NovoPedidoItemRequest> getItens() {
		return itens;
	}
	
	public Function<Compra, Pedido> toModel(EntityManager manager){
		Set<ItemPedido> itensCalculados = itens.stream().map(item -> item.toModel(manager)).collect(Collectors.toSet());
		return (compra) ->{
			Pedido pedido = new Pedido(compra, total, itensCalculados);
			Assert.isTrue(pedido.totalIgual(this.total), "Total do pedido, não reflete o total real");
			return pedido;
		};
	}
}
