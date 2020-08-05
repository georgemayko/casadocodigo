package br.com.gm.deveficiente.casadocodigo.cupons;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Cupom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotNull String codigo;
	private @NotNull @Max(100) @Positive BigDecimal percentual;
	private @Future LocalDate dataValidade;
	
	@Deprecated
	public Cupom() {	}

	public Cupom(@NotNull String codigo, @NotNull @Max(100) @Positive BigDecimal percentual,
			@Future LocalDate dataValidade) {
				this.codigo = codigo;
				this.percentual = percentual;
				this.dataValidade = dataValidade;
	}

	@Override
	public String toString() {
		return "Cupom [id=" + id + ", codigo=" + codigo + ", percentual=" + percentual + ", dataValidade="
				+ dataValidade + "]";
	}
}
