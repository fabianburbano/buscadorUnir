package com.unir.buscador.service;

import java.util.List;

import com.unir.buscador.model.pojo.Product;
import com.unir.buscador.model.request.CreateProductRequest;
import com.unir.buscador.model.request.DecreaseProductRequest;

public interface ProductsService {

	List<Product> getProducts();

	Product getProduct(String purchaseId);

	Boolean removeProduct(String customerId);

	Product createProduct(CreateProductRequest request);
	
	Product updateQuantityProduct(DecreaseProductRequest request);
}
