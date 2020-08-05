package br.com.gm.deveficiente.casadocodigo.processocompra;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.gm.deveficiente.casadocodigo.novolivro.Livro;

@Component
public class TotalPedidoValidator implements Validator{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors())
			return;
		NovoPedidoRequest request = ((NovaCompraRequest) target).getPedido();
		BigDecimal total = BigDecimal.ZERO;
		
		for (NovoPedidoItemRequest item : request.getItens()) {
			Livro livro = entityManager.find(Livro.class, item.getIdLivro());
			Assert.notNull(livro, "Livro não encontrado para o id: " + item.getIdLivro());
			total = new BigDecimal(item.getQuantidade()).multiply(livro.getPreco());
		}
		
		if(total.compareTo(request.getTotal()) != 0) {
			errors.rejectValue("pedido.total", null, "O valor total não condiz com os itens solicitados");
		}
		
	}

}
