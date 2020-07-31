package br.com.gm.deveficiente.casadocodigo.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExisteIdValidator implements ConstraintValidator<ExistId, Object>{

	@PersistenceContext
	private EntityManager entityManager;
	
	//private String field;
	private Class<?> domainClass;

	@Override
	public void initialize(ExistId constraintAnnotation) {
		domainClass = constraintAnnotation.domainClass();
		//field = constraintAnnotation.field();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null)
			return false;
		Object resultado = entityManager.find(domainClass, value);
		return resultado != null;
	}

}
