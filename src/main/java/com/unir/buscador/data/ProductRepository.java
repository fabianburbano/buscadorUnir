package com.unir.buscador.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.buscador.model.pojo.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
