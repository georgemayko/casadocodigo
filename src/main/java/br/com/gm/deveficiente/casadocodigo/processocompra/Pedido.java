package br.com.gm.deveficiente.casadocodigo.processocompra;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotNull @Positive BigDecimal total;
	@ElementCollection
	private @Size(min = 1) Set<ItemPedido> itens;
	@OneToOne
	private @NotNull Compra compra;
	
	@Deprecated
	public Pedido() {	}

	public Pedido(@NotNull @Valid Compra compra,@NotNull @Positive BigDecimal total,
			@Size(min = 1) Set<ItemPedido> itens) {
		Assert.notEmpty(itens, "Todo pedido deve ter pelo menos 1 item"); 
		this.total = total;
		this.itens = itens;
		this.compra = compra;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
	public Set<ItemPedido> getItens() {
		return itens;
	};

	public boolean totalIgual(@NotNull @Positive BigDecimal total) {
		BigDecimal totalPedido = this.itens.stream().map(ItemPedido::total).reduce(BigDecimal.ZERO, (atual, proximo) -> atual.add(proximo));
		return totalPedido.compareTo(total) == 0;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", total=" + total + ", itens=" + itens + "]";
	}
	
}
