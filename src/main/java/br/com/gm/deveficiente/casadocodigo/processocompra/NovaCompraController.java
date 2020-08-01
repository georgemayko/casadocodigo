package br.com.gm.deveficiente.casadocodigo.processocompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NovaCompraController {

	@PersistenceContext
	EntityManager entityManager;
	

	@PostMapping(value = "compra")
	public String cria(@RequestBody @Valid NovaCompraRequest request) {
		return request.toModel(entityManager).toString();
	}

}
