package br.com.gm.deveficiente.casadocodigo.pais;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaisController {

	@PersistenceContext
	EntityManager manager;
	
	@PostMapping(value = "pais")
	@Transactional
	public String cria(@RequestBody @Valid NovoPaisRequest request) {
		Pais novoPais = new Pais(request.getNome());
		manager.persist(novoPais);
		return novoPais.toString();
	}

}
