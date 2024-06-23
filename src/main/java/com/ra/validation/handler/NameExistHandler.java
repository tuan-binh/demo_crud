package com.ra.validation.handler;

import com.ra.repository.IProductRepository;
import com.ra.validation.annotation.NameExist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NameExistHandler implements ConstraintValidator<NameExist, String> {
	private final IProductRepository productRepository;
	
	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		return !productRepository.existsByName(s);
	}
}
