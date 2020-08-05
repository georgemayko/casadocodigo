package br.com.gm.deveficiente.casadocodigo.cupons;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CupomContoller {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@PostMapping(value = "cumpons")
	@Transactional
	public String cria(@RequestBody @Valid NovoCupomRequest request) {
		Cupom cupom = request.toModel();
		entityManager.persist(cupom);
		return cupom.toString();
	}

}
