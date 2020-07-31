package br.com.gm.deveficiente.casadocodigo.novolivro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.gm.deveficiente.casadocodigo.novoautor.Autor;
import br.com.gm.deveficiente.casadocodigo.novoautor.AutorRepository;

@Component
public class AutorValidator implements Validator{

	@Autowired
	private AutorRepository repository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NovoLivroRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors())
			return;
		NovoLivroRequest request = (NovoLivroRequest) target;
		Long id = request.getIdAutor();
		Optional<Autor> possivelAutor = repository.findById(id);
		if(!possivelAutor.isPresent())
			errors.rejectValue("idAutor", null, "Autor não encontrado para o Id: " + id);
	}

}
