package br.com.gm.deveficiente.casadocodigo.novolivro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gm.deveficiente.casadocodigo.novacategoria.CategoriaRepository;
import br.com.gm.deveficiente.casadocodigo.novoautor.AutorRepository;

@RestController 
//4
public class LivroController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private AutorValidator autorValidator;
	@Autowired
	private CategoriaValidator categoriaValidator;
	
	@InitBinder
	protected void InitBinder( WebDataBinder binder) {
		binder.addValidators(autorValidator);
		binder.addValidators(categoriaValidator);
	}
	
	@Transactional
	@PostMapping(value = "livros")
	//2
	public String cria(@RequestBody @Valid LivroRequest request) {
		Livro novoLivro = request.toModel(entityManager);
		entityManager.persist(novoLivro);
		return novoLivro.toString();
	}

}
