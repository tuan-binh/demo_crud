package com.ra.validation.annotation;

import com.ra.validation.handler.NameExistHandler;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameExistHandler.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NameExist {
	String message() default "name already exist";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
