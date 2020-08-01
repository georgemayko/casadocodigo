package br.com.gm.deveficiente.casadocodigo.processocompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NovaCompraController {

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	private PaisEstadoCompraValidator paisEstadoCompraValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(paisEstadoCompraValidator);
	}

	@PostMapping(value = "compra")
	public String cria(@RequestBody @Valid NovaCompraRequest request) {
		//TODO Validar Se documento valido (CPF ou CNPJ) OK!
		//TODO validar Se pais informado possi estado, o campo estado deve ser obrigatorio
		//TODO Salvar
		return request.toModel(entityManager).toString();
	}

}
