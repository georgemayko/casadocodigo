package br.com.gm.deveficiente.casadocodigo.novoautor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
//5
public class NovoAutorComEmailCadastradoValidator implements Validator{

	@Autowired
	//1
	private AutorRepository repository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NovoAutorRequest.class.isAssignableFrom(clazz);
	}

	@Override
	//4
	public void validate(Object target, Errors errors) {
		NovoAutorRequest request = (NovoAutorRequest) target;
		if(errors.hasErrors())
			return;
		Optional<Autor> possivelAutor = repository.findByEmail(request.getEmail());
		if(possivelAutor.isPresent())
			errors.rejectValue("email", null, "Email já cadastrado!");	
	}

}
