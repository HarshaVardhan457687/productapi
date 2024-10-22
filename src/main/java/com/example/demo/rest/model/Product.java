package com.example.demo.rest.model;





import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JacksonXmlRootElement
@Entity 
@NoArgsConstructor
public class Product {
	@Id
	private long id;
	private String brand, descc;
	private int qty, price;
	
//	public Product(long productId, String brand, String description, int qty, int price) {
//		this.productId = productId;
//		this.brand = brand;
//		this.description = description;
//		this.qty = qty;
//		this.price = price;
//	} 
//	
//	public String getBrand() { 
//		return this.brand;
//	} 
//	
//	public long getProductId() { 
//		return this.productId;
//	} 
	public void update(Product p) { 
		this.id = p.id; 
		this.brand = p.brand; 
		this.descc = p.descc; 
		this.qty = p.qty; 
		this.price = p.price;
	}

}
