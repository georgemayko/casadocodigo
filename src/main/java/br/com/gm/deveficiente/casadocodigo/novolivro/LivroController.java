package br.com.gm.deveficiente.casadocodigo.novolivro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
//5
public class LivroController {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@PostMapping(value = "livros")
	//3
	public LivroCriadoResponse cria(@RequestBody @Valid NovoLivroRequest request) {
		Livro novoLivro = request.toModel(entityManager);
		entityManager.persist(novoLivro);
		return new LivroCriadoResponse(novoLivro);
	}
	
	
	@GetMapping(value = "livros")
	//1
	public List<LivroListagemResponse> lista() {
		List<Livro> listaDeLivro = entityManager.createQuery("FROM Livro", Livro.class).getResultList();
		List<LivroListagemResponse> listaDeLivroListagemResponse = new ArrayList<>();
		listaDeLivro.forEach(umLivro -> listaDeLivroListagemResponse.add(new LivroListagemResponse(umLivro)));
		return listaDeLivroListagemResponse;
	}
	
	@GetMapping(value = "livros/{id}")
	//1
	public ResponseEntity<DetalheLivroResponse> detalha(@PathVariable("id") Long livroId) {
		Optional<Livro> possivelLivro = Optional.ofNullable(entityManager.find(Livro.class, livroId));
		Optional<DetalheLivroResponse> possivelDetalheLivroResponse = possivelLivro.map( livro -> new DetalheLivroResponse(livro));
		return ResponseEntity.of(possivelDetalheLivroResponse);
	}
	
}
