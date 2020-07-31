package br.com.gm.deveficiente.casadocodigo.estado;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.gm.deveficiente.casadocodigo.pais.Pais;
import br.com.gm.deveficiente.casadocodigo.validator.ExistId;
import br.com.gm.deveficiente.casadocodigo.validator.UniqueValue;

public class NovoEstadoRequest {

	@NotBlank
	@UniqueValue(domainClass = Estado.class, fieldName = "nome")
	private String nome;
	@NotNull
	@ExistId(domainClass = Pais.class)
	private Long paisId;
	
	
	public NovoEstadoRequest(@NotBlank String nome, Long paisId) {
		super();
		this.nome = nome;
		this.paisId = paisId;
	}


	public Estado toModel(EntityManager manager) {
		// TODO Auto-generated method stub
		Pais pais = manager.find(Pais.class, this.paisId);
		
		Assert.state(pais != null, "Pais nao encontrado Id :" + this.paisId);
		return new Estado(this.nome, pais);
	}
	
	
	
}
