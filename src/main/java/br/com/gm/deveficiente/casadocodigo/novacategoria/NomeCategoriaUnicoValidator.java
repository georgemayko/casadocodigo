package br.com.gm.deveficiente.casadocodigo.novacategoria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
//5
public class NomeCategoriaUnicoValidator implements Validator{

	@Autowired
	//1
	private CategoriaRepository repository;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCategoriaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	//4
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors())
			return;
		NovaCategoriaRequest request =(NovaCategoriaRequest) target;
		Optional<Categoria> possivelCategoria = repository.findByNome(request.getNome());
		if(possivelCategoria.isPresent())
			errors.reject("nome", null, "Nome de catogoria já existe!");
	}
	
	
}
