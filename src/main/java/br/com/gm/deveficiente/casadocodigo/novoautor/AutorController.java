package br.com.gm.deveficiente.casadocodigo.novoautor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//2 pontos
public class AutorController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@PostMapping(value = "/autores")
	//2 pontos
	public String cria(@RequestBody @Valid NovoAutorRequest request) {
		Autor autor = request.toModel();
		entityManager.persist(autor);
		return autor.toString();
	}

}
