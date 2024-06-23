package com.ra.service;

import com.ra.dto.request.ProductRequest;
import com.ra.exception.CustomException;
import com.ra.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
	
	Page<Product> findAll(Pageable pageable,String search);
	
	Product findById(Long id) throws CustomException;
	
	Product save(ProductRequest productRequest);
	
	Product update(ProductRequest productRequest, Long idUpdate) throws CustomException;
	
	void deleteById(Long idDelete);
	
}
