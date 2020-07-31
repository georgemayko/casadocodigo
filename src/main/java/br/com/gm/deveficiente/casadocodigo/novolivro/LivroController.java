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
//4
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
	
	
	@GetMapping(value = "livros")
	//1
	public List<LivroDTO> lista() {
		List<Livro> lista = entityManager.createQuery("FROM Livro", Livro.class).getResultList();
		List<LivroDTO> listaDto = new ArrayList<>();
		lista.forEach(umLivro -> listaDto.add(new LivroDTO(umLivro.getId(), umLivro.getTitulo())));
		return listaDto;
	}
	
	@GetMapping(value = "livros/{id}")
	//1
	public ResponseEntity<LivroDetalheDTO> getMethodName(@PathVariable("id") Long livroId) {
		Optional<Livro> livro = Optional.ofNullable(entityManager.find(Livro.class, livroId));
		Optional<LivroDetalheDTO> livroDetalheDto = livro.map( l -> new LivroDetalheDTO(l));
		return ResponseEntity.of(livroDetalheDto);
	}


	

}
