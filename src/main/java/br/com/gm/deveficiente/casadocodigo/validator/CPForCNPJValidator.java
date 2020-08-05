package br.com.gm.deveficiente.casadocodigo.validator;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;

//3
public class CPForCNPJValidator implements ConstraintValidator<CPForCNPJ, Object>{

	@Autowired
	private Validator validator;

//	@Override
//	//1
//	public void initialize(CPForCNPJ constraintAnnotation) {
//		validator = Validation.buildDefaultValidatorFactory().getValidator();
//	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null)
			return false;
		Set<ConstraintViolation<CpfRequest>> cpfValidations = this.validator.validate(new CpfRequest(value.toString()));
		Set<ConstraintViolation<CnpjRequest>> cnpjValidations = this.validator.validate(new CnpjRequest(value.toString()));
		return cpfValidations.isEmpty() || cnpjValidations.isEmpty();
	}
	//1
	class CpfRequest{
		@CPF
		private String cpf;
		public CpfRequest(@CPF String cpf) {
			this.cpf = cpf;
		}
	}
	//1
	class CnpjRequest {
		@CNPJ
		private String cnpj;
		public CnpjRequest(@CNPJ String cnpj) {
			this.cnpj = cnpj;
		}
	}

}
