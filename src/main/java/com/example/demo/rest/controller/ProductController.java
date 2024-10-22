package com.example.demo.rest.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rest.model.Product;
import com.example.demo.rest.service.ProductService;

@RestController
@RequestMapping("/product/api")
public class ProductController { 
	@Autowired
	private ProductService productService;
	
//	@GetMapping("/get")
//	public String status() {
//		
//		return "Up and Running";
//	} 
//	
//	@GetMapping("/Hars")
//	public String sendHarsh() {  
//		return "Harsha Vardhan";
//
//	}
//	
//	@GetMapping("/23")
//	public String hello() {
//		return "Thank You";
//	}
//	
//	@GetMapping("/number/{num1}/{num2}")
//	public String  multiply(@PathVariable String num1, @PathVariable String num2) {
//		return num1+num2;
//		
//	}
//	
//	@GetMapping("/number/{array}")
//	public int sum(@PathVariable String array) {
//		String[] arr = array.split(" , ");
//		int sum = 0;
//		for(String s: arr) sum += Integer.parseInt(s);
//		return sum;
//				
//	}
	
	List<Product> Productlist = new ArrayList();		
	{
		Productlist.add(new Product(101,"Vyjayanthi","Walk",12,12141));
		Productlist.add(new Product(102,"Nike","Running",13,12341));
		Productlist.add(new Product(103,"Adidas","Synthetic",11,12391));
}	
//
//		
		@GetMapping(value="/shoes", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Product> displayProducts(){
//			return productService.getAllProducts(); 
			return Productlist;
		}
		
		@GetMapping(value="/producct", produces = MediaType.APPLICATION_XML_VALUE)
		public ResponseEntity<List<Product>> entityView() {
			//return ResponseEntity.ok(productService.getAllProducts());
			return ResponseEntity.ok(Productlist);
		}
		
		//filter
		@GetMapping(value="/product/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Product>getById(@PathVariable long id){
			
			Optional<Product> optional = Productlist.stream().filter(product -> product.getId() == id).findFirst();
			
			return ResponseEntity.status(HttpStatus.OK).body(optional.get());
			//return ResponseEntity.ok(productService.getProductById(id));
			
			
		}
		
		//filter with brand
		
		@GetMapping(value="/productt/{brand}", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Product> getBybrand(@PathVariable String brand){

			
			Optional<Product> optional = Productlist.stream().filter(product -> product.getBrand().equals(brand)).findFirst();
			
			return ResponseEntity.status(HttpStatus.OK).body(optional.get()); 
			
			//return ResponseEntity.ok(productService.getByBrand(brand)); 
			
		} 
		
//		@GetMapping(value="/productts/{brand}", produces=MediaType.APPLICATION_JSON_VALUE)
//		public ResponseEntity<List<Product>> getProductsBybrand(@PathVariable String brand){
//
//			
//			Optional<Product> optional = Productlist.stream().filter(product -> product.getBrand().equals(brand)).findFirst();
//			
//			return ResponseEntity.status(HttpStatus.OK).body(optional.get()); 
//			
//			//return ResponseEntity.ok(productService.getProductsByBrand(brand)); 
//			
//		} 
		//Posting data to array
		@PostMapping(value="/addproduct",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Product> addProduct(@RequestBody Product product){
		boolean flag=false;
			if(product!=null)
				flag = Productlist.add(product);
			return flag? ResponseEntity.status(201).body(product):ResponseEntity.status(404).body(null);
		   // return ResponseEntity.ok(productService.addProduct(product));
		 
		
		}
		
		
		@PutMapping(value="/modify", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		//	return ResponseEntity.ok(productService.update(product));
			long count = Productlist.stream().filter(p -> p.getId() == product.getId()).count();
			if(count==1)
				Productlist.add(product); 
			for(Product p: Productlist) { 
				if(p.getId() == product.getId()) p.update(product); 
			}
			return ResponseEntity.accepted().body(null);
		}
		
		@DeleteMapping(value="/delete/{id}")
		public void removeProduct(@PathVariable long id) {
			Optional<Product> optional = Productlist.stream().filter(p -> p.getId() == id).findFirst();
			Productlist.remove(optional.get()); 
			
			//productService.delete(id);
			
			
		}
		
		
	
	
	
	
	

}
