package br.com.gm.deveficiente.casadocodigo.pais;

import javax.validation.constraints.NotBlank;

import br.com.gm.deveficiente.casadocodigo.validator.UniqueValue;

public class NovoPaisRequest {

	@NotBlank
	@UniqueValue(domainClass = Pais.class, fieldName = "nome")
	private String nome;

	public String getNome() {
		return nome;
	}
}
