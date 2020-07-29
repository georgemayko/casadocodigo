package br.com.gm.deveficiente.casadocodigo.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

	@PersistenceContext
	private EntityManager manager;
	private Class<?> domainClass;
	private String fieldName;
	
	@Override
	public void initialize(UniqueValue constraintAnnotation) {
		fieldName = constraintAnnotation.fieldName();
		domainClass = constraintAnnotation.domainClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String format = String.format("FROM %s WHERE %s = :param", domainClass.getName(), fieldName);
		Query query = manager.createQuery(format.toString()).setParameter("param", value);
		List<?> resultList = query.getResultList();
		Assert.state(resultList.size() <= 1 , "Mais de um resultado encontrado para a consulta");
		return resultList.isEmpty();
	}


}
