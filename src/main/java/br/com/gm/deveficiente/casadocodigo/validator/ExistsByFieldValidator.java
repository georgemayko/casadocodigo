package br.com.gm.deveficiente.casadocodigo.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class ExistsByFieldValidator implements ConstraintValidator<ExistsByField, Object> {

	@PersistenceContext
	private EntityManager manager;
	
	private Class<?> domainClass;
	private String field;
	@Override
	public void initialize(ExistsByField constraintAnnotation) {
		this.domainClass = constraintAnnotation.domainClass();
		this.field = constraintAnnotation.field();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null)
			return true;
		String query = String.format("FROM %s WHERE %s = :param", domainClass.getSimpleName(), field);
		List<?> resultList = manager.createQuery(query, domainClass).setParameter("param", value).getResultList();
		return !resultList.isEmpty();
	}

}
