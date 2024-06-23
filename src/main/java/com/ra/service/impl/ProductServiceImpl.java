package com.ra.service.impl;

import com.ra.dto.request.ProductRequest;
import com.ra.exception.CustomException;
import com.ra.model.Product;
import com.ra.repository.IProductRepository;
import com.ra.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
	private final IProductRepository productRepository;
	
	@Override
	public Page<Product> findAll(Pageable pageable, String search) {
		Page<Product> products;
		if (search.isEmpty()) {
			products = productRepository.findAll(pageable);
		} else {
			products = productRepository.findAllByNameContains(search, pageable);
		}
		return products;
	}
	
	@Override
	public Product findById(Long id) throws CustomException {
		return productRepository.findById(id).orElseThrow(() -> new CustomException("product not found", HttpStatus.NOT_FOUND));
	}
	
	@Override
	public Product save(ProductRequest productRequest) {
		Product product = Product.builder()
				  .name(productRequest.getName())
				  .price(productRequest.getPrice())
				  .stock(productRequest.getStock())
				  .status(productRequest.getStatus() == null || productRequest.getStatus())
				  .build();
		return productRepository.save(product);
	}
	
	@Override
	public Product update(ProductRequest productRequest, Long idUpdate) throws CustomException {
		Product product = productRepository.findById(idUpdate).orElseThrow(() -> new CustomException("product not found", HttpStatus.NOT_FOUND));
		product.setName(productRequest.getName() != null ? productRequest.getName() : product.getName());
		product.setPrice(productRequest.getPrice() != null ? productRequest.getPrice() : product.getPrice());
		product.setStock(productRequest.getStock() != null ? productRequest.getStock() : product.getStock());
		product.setStatus(productRequest.getStatus() != null ? productRequest.getStatus() : product.getStatus());
		return productRepository.save(product);
	}
	
	@Override
	public void deleteById(Long idDelete) {
		productRepository.deleteById(idDelete);
	}
}
