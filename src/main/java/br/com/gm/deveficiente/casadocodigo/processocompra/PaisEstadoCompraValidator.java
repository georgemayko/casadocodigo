package br.com.gm.deveficiente.casadocodigo.processocompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.gm.deveficiente.casadocodigo.estado.Estado;
import br.com.gm.deveficiente.casadocodigo.pais.Pais;

@Component
public class PaisEstadoCompraValidator implements Validator{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	//4
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors())
			return;
		NovaCompraRequest request = (NovaCompraRequest) target;
		
		if(isParametrosEstadoEPaisPreenchidos(request)) {
			validaSeEstadoPertenceAoPais(errors, request);
			return;
		}
		
		Pais paisBuscado = entityManager.find(Pais.class, request.getPaisId());
		Assert.state(paisBuscado != null, "Pais não encontrado para o id: " + request.getPaisId());
		
		if(paisBuscado.possuiEstados()) {
			errors.rejectValue("estadoId", null, "Preenchimento do estado é obrigatório para o país selecionado");
		}
	}

	private void validaSeEstadoPertenceAoPais(Errors errors, NovaCompraRequest request) {
		Estado estadoBuscado = entityManager.find(Estado.class, request.getEstadoId());
		Assert.state(estadoBuscado != null, "Estado não encontrado para o id: " + request.getEstadoId());
		if(estadoBuscado.getPais().getId() != request.getPaisId()) {
			errors.rejectValue("estadoId", null, "Estado informado não pertence o país informado");
		}
	}

	private boolean isParametrosEstadoEPaisPreenchidos(NovaCompraRequest request) {
		return request.getEstadoId() != null && request.getPaisId() != null;
	}

}
