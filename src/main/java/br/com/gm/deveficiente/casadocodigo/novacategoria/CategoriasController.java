package br.com.gm.deveficiente.casadocodigo.novacategoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//2
public class CategoriasController {
	
	@PersistenceContext
	EntityManager entityManager;

	//2
	@PostMapping(value = "categorias")
	@Transactional
	public String cria(@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria categoria = request.toModel();
		entityManager.persist(categoria);
		return categoria.toString();
	}

}
