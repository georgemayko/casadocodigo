package br.com.gm.deveficiente.casadocodigo.processocompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NovaCompraController {

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	CupomRepository cupomRepository;
	
	@Autowired
	private PaisEstadoCompraValidator paisEstadoCompraValidator;
	@Autowired
	private TotalPedidoValidator  totalPedidoValitor;
	@Autowired
	private CupomValidoValidator cupomValidoValitator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(paisEstadoCompraValidator, totalPedidoValitor, cupomValidoValitator);
	}

	@PostMapping(value = "compra")
	@Transactional
	@ResponseStatus(code = HttpStatus.CREATED)
	public String cria(@RequestBody @Valid NovaCompraRequest request) {
		Compra compra = request.toModel(entityManager, cupomRepository);
		entityManager.persist(compra);
		return compra.toString();
	}

}
