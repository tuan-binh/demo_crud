package com.ra.controller;

import com.ra.dto.request.ProductRequest;
import com.ra.exception.CustomException;
import com.ra.model.Product;
import com.ra.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
	private final IProductService productService;
	
	/**
	 * @param pageable Pageable
	 * @param search String
	 * @apiNote phân trang và search
	 */
	@GetMapping
	public ResponseEntity<Page<Product>> findAll(
			  @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			  @RequestParam(defaultValue = "") String search
	) {
		return new ResponseEntity<>(productService.findAll(pageable, search), HttpStatus.OK);
	}
	
	/**
	 * @param productId Long
	 * @apiNote tìm kiểm sản phẩm theo id
	 * */
	@GetMapping("/{productId}")
	public ResponseEntity<Product> findById(@PathVariable Long productId) throws CustomException {
		
		return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
	}
	
	
	/**
	 * @param productRequest ProductRequest
	 * @apiNote thêm mới sản phẩm
	 * */
	@PostMapping
	public ResponseEntity<Product> addNewProduct(@Valid @RequestBody ProductRequest productRequest) {
		return new ResponseEntity<>(productService.save(productRequest), HttpStatus.CREATED);
	}
	
	/**
	 * @param productRequest ProductRequest
	 * @param productId Long
	 * @apiNote cập nhật thông tin sản phẩm
	 * */
	@PutMapping("/{productId}")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long productId) throws CustomException {
		return new ResponseEntity<>(productService.update(productRequest, productId), HttpStatus.OK);
	}
	
	/**
	 * @param productId Long
	 * @apiNote xóa thông tin sản phẩm
	 * */
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
		productService.deleteById(productId);
		return new ResponseEntity<>("delete product successfully", HttpStatus.OK);
	}
	
}

