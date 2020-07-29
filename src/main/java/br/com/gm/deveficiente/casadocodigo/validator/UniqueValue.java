package br.com.gm.deveficiente.casadocodigo.validator;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UniqueValueValidator.class)
public @interface UniqueValue {

	//error message  
    public String message() default "{br.com.gm.casadocodigo.unique}";  
    //represents group of constraints     
    public Class<?>[] groups() default {};  
    //represents additional information about annotation  
    public Class<? extends Payload>[] payload() default {};
    
    String fieldName();
    
    Class<?> domainClass();
    
}
