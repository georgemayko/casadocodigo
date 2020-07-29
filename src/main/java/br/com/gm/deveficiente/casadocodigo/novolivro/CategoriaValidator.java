package br.com.gm.deveficiente.casadocodigo.novolivro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.gm.deveficiente.casadocodigo.novacategoria.Categoria;
import br.com.gm.deveficiente.casadocodigo.novacategoria.CategoriaRepository;

@Component
public class CategoriaValidator implements Validator{

	@Autowired
	private CategoriaRepository repository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LivroRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors())
			return;
		LivroRequest request = (LivroRequest) target;
		Long id = request.getIdCategoria();
		Optional<Categoria> possivelCategoria = repository.findById(id);
		if(!possivelCategoria.isPresent())
			errors.rejectValue("idCategoria", null, "Autor não encontrado para o Id: " + id);
	}

}
