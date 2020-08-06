package br.com.gm.deveficiente.casadocodigo.processocompra;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.gm.deveficiente.casadocodigo.cupons.Cupom;

@Embeddable
public class CupomAplicado {

	@ManyToOne
	private @Valid Cupom cupom;
	@Positive
	@Max(100)
	@NotNull
	private BigDecimal percentualDescontoMomento;
	@Future
	@NotNull
	private LocalDate dataValidadeMomento;

	public CupomAplicado(@Valid Cupom cupom) {
		this.cupom = cupom;
		this.percentualDescontoMomento = cupom.getPercentual();
		this.dataValidadeMomento = cupom.getDataValidade();
	}
	
}
