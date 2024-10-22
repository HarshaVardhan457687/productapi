package com.example.demo.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.rest.model.Product;
import com.example.demo.rest.repository.ProductRepository;

@Service
public class ProductService {
	
	
	@Autowired
	private ProductRepository repository;
	
	public Product addProduct(Product newProduct) {
		
		return repository.save(newProduct);
	}
	public Product getProductById(long id) {
		return repository.findById(id).get(); 
		
	}
	
	public Product update(Product updatedProduct) {
		return repository.save(updatedProduct);
	} 
	
	public void delete(long id) { 
		repository.deleteById(id);
	}  
	
	public List<Product> getAllProducts(){ 
		List<Product> list = new ArrayList<>();  
		repository.findAll().forEach(product -> list.add(product)); 
		return list;
	}
	
	public Product getByBrand(String brand) { 
		return this.getAllProducts()
				.stream()
				.filter(product -> product.getBrand().equals(brand))
				.findAny()
				.get();
	} 
	
	public Product getProductBrand(String brand){
		return repository.findByBrand(brand);
	}
	
	public List<Product> getProductsByBrand(String Brand){
		return repository.findProductsByBrand(Brand);
	}
	
	
}
