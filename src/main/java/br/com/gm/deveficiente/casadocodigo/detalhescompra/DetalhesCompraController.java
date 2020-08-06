package br.com.gm.deveficiente.casadocodigo.detalhescompra;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.gm.deveficiente.casadocodigo.processocompra.Compra;

@RestController
public class DetalhesCompraController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@GetMapping(value = "compras/{id}")
	public ResponseEntity<DetalheCompraResponse> getMethodName(@PathVariable("id") Long idCompra) {
		Optional<Compra> possivelCompra = Optional.ofNullable(entityManager.find(Compra.class, idCompra));
		Optional<DetalheCompraResponse> possivelDetalheCompra = possivelCompra.map( compra -> new DetalheCompraResponse(compra) );
		return ResponseEntity.of(possivelDetalheCompra);
	}

}
