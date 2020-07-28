package br.com.gm.deveficiente.casadocodigo.exception;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@Autowired
	private MessageSource messageSource; 
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ValidationErrorOutputDTO handle(MethodArgumentNotValidException exception) {
		List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		return buildValidationOutputErrorDTO(globalErrors, fieldErrors);
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = BindException.class)
	public ValidationErrorOutputDTO handle(BindException exception) {
		List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		return buildValidationOutputErrorDTO(globalErrors, fieldErrors);
	}

	private ValidationErrorOutputDTO buildValidationOutputErrorDTO(List<ObjectError> globalErrors,
			List<FieldError> fieldErrors) {
		ValidationErrorOutputDTO validationErrors = new ValidationErrorOutputDTO();
		
		globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));
		fieldErrors.forEach(error -> {
			String errorMessage = getErrorMessage(error);
			validationErrors.addFielError(error.getField(), errorMessage);
		});
		return validationErrors;
	}
	
	private String getErrorMessage(ObjectError error) {
		System.out.println(error);
		return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	}
	
}
