package br.com.gm.deveficiente.casadocodigo.novolivro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
//2
public class LivroController {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@PostMapping(value = "livros")
	//2
	public String cria(@RequestBody @Valid LivroRequest request) {
		Livro novoLivro = request.toModel(entityManager);
		entityManager.persist(novoLivro);
		return novoLivro.toString();
	}

}
