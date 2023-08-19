package com.validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;



public class EntityValidator<T> {
	
	public List<String> validate(T entity){
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
		
		List<String> errors = new ArrayList<>();
		if(!constraintViolations.isEmpty()) {
			for(ConstraintViolation<T> constraintViolation: constraintViolations) {
				errors.add(constraintViolation.getMessage());
			}
		}
		return errors;
	}
}
