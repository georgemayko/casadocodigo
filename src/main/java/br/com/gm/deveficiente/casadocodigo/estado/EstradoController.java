package br.com.gm.deveficiente.casadocodigo.estado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstradoController {

	@PersistenceContext
	EntityManager manager;
	
	@PostMapping(value = "estado")
	@Transactional
	public String cria(@RequestBody @Valid NovoEstadoRequest request) {
		Estado novoEstado = request.toModel(manager);
		manager.persist(novoEstado);
		return novoEstado.toString();
	}

}
