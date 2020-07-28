package br.com.gm.deveficiente.casadocodigo.novacategoria;

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

@RestController
//3
public class CategoriasController {
	
	@PersistenceContext
	EntityManager entityManager;
	//1
	@Autowired
	NomeCategoriaUnicoValidator nomeCategoriaUnicoValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(nomeCategoriaUnicoValidator);
	}

	@PostMapping(value = "categorias")
	@Transactional
	//2
	public String cria(@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria categoria = request.toModel();
		entityManager.persist(categoria);
		return categoria.toString();
	}

}
