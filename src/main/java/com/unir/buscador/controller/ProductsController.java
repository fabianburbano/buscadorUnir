package com.unir.buscador.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.unir.buscador.model.pojo.Product;
import com.unir.buscador.model.request.CreateProductRequest;
import com.unir.buscador.model.request.DecreaseProductRequest;
import com.unir.buscador.service.ProductsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductsController {
	
	private final ProductsService service;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(@RequestHeader Map<String, String> headers) {
		log.info("headers: {}", headers);
		List<Product> products = service.getProducts();
		if (products != null) {
			return ResponseEntity.ok(products);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable String productId) {

		log.info("Request received for product {}", productId);
		Product product = service.getProduct(productId);

		if (product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Void> deletePurchaser(@PathVariable String productId) {

		Boolean removed = service.removeProduct(productId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
		Product createdProduct = service.createProduct(request);
		if (createdProduct != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PatchMapping("/products")
	public ResponseEntity<Product> decreaseQuantityProduct(@RequestBody DecreaseProductRequest request) {
		Product decreaseProduct = service.updateQuantityProduct(request);
		if (decreaseProduct != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(decreaseProduct);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
