package br.com.gm.deveficiente.casadocodigo.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorOutputDTO {

	private List<String> globalErrors ;
	private List<FieldErrorOutputDTO> fieldErrors;

	public ValidationErrorOutputDTO() {
		this.globalErrors = new ArrayList<>();
		this.fieldErrors = new ArrayList<>();
	}

	public void addError(String errorMessage) {
		this.globalErrors.add(errorMessage);
	}

	public void addFielError(String field, String errorMessage) {
		this.fieldErrors.add(new FieldErrorOutputDTO(field, errorMessage));
	}
	
	public List<String> getGlobalErrors() {
		return globalErrors;
	}
	
	public List<FieldErrorOutputDTO> getFieldErrors() {
		return fieldErrors;
	}
	
	
}
