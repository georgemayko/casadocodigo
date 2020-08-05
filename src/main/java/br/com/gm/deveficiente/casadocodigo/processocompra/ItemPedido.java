package br.com.gm.deveficiente.casadocodigo.processocompra;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.gm.deveficiente.casadocodigo.novolivro.Livro;

@Embeddable
public class ItemPedido {

	@ManyToOne
	private @Valid @NotNull Livro livro;
	private @NotNull @Positive Integer quantidade;
	private @Positive BigDecimal precoMomento;

	@Deprecated
	public ItemPedido() {	}
	
	public ItemPedido(@Valid @NotNull Livro livro, @NotNull @Positive Integer quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
		this.precoMomento = livro.getPreco();
	}

	public BigDecimal total() {
		return this.precoMomento.multiply(new BigDecimal(this.quantidade));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((livro == null) ? 0 : livro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (livro == null) {
			if (other.livro != null)
				return false;
		} else if (!livro.equals(other.livro))
			return false;
		return true;
	}

	public ItemPedido(@Valid @NotNull Livro livro, @NotNull @Positive Integer quantidade,
			@Positive BigDecimal precoMomento) {
		super();
		this.livro = livro;
		this.quantidade = quantidade;
		this.precoMomento = precoMomento;
	}

	@Override
	public String toString() {
		return "ItemPedido [livro=" + livro + ", quantidade=" + quantidade + ", precoMomento=" + precoMomento + "]";
	}
}
