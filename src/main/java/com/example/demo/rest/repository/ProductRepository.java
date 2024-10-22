package com.example.demo.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.rest.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
	public Product findByBrand(String Brand);


	public List<Product> findProductsByBrand(String Brand);


}