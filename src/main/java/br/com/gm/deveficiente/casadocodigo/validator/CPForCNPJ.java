package br.com.gm.deveficiente.casadocodigo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPForCNPJValidator.class)
public @interface CPForCNPJ {

	String message() default "{br.com.gm.casadocodigo.documentoinvalido}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
