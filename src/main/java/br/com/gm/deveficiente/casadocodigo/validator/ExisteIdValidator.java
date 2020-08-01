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
	private boolean isRequired;

	@Override
	public void initialize(ExistId constraintAnnotation) {
		domainClass = constraintAnnotation.domainClass();
		this.isRequired = constraintAnnotation.isRequired();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(!this.isRequired && value == null)
			return true;
		if(value == null)
			return false;
		Object resultado = entityManager.find(domainClass, value);
		return resultado != null;
	}

}
