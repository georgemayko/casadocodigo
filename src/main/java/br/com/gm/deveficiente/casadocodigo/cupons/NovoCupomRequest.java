package br.com.gm.deveficiente.casadocodigo.cupons;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.gm.deveficiente.casadocodigo.validator.UniqueValue;

public class NovoCupomRequest {

	@NotNull
	@UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
	private String codigo;
	@NotNull
	@Max(100)
	@Positive
	private BigDecimal percentual;
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataValidade;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public NovoCupomRequest(@NotNull String codigo, @NotNull @Max(100) @Positive BigDecimal percentual,
			@Future LocalDate dataValidade) {
		super();
		this.codigo = codigo;
		this.percentual = percentual;
		this.dataValidade = dataValidade;
	}


	public Cupom toModel() {
		return new Cupom(this.codigo, this.percentual, this.dataValidade);
	}
	
	
	
	
	
}
