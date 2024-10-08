package com.traffsys.stock.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private double price;
	private Integer quantity;
    private boolean paymentPending; 
	private boolean outOfStock;  
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="category_id")
	private Category category;
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
    


	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		 this.outOfStock = (quantity <= 0);
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}
	
	

	public Supplier getSupplier() {
		return supplier;
	}



	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}





	public Stock(Long id, String name, String description, double price, Integer quantity, boolean paymentPending,
			boolean outOfStock, Category category, Supplier supplier) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.paymentPending = paymentPending;
		this.outOfStock = outOfStock;
		this.category = category;
		this.supplier = supplier;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", category=" + category + ", supplier=" + supplier +"]";
	}



	public boolean isPaymentPending() {
		return paymentPending;
	}



	public void setPaymentPending(boolean paymentPending) {
		this.paymentPending = paymentPending;
	}



	public boolean isOutOfStock() {
		return outOfStock;
	}



	public void setOutOfStock(boolean outOfStock) {
		this.outOfStock = outOfStock;
	}



	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
