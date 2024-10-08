package com.traffsys.stock.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="sales")
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String customerName;
	private String remark;
	private Integer price;
	private Double discount;
	private String address;
	private String mobile;
	private String email;
	private Integer purchasedQuantity;
	private LocalDate purchasedDate;
	private LocalDate deliveryDate;

	

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "stock_id")
	private Stock stock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPurchasedQuantity() {
		return purchasedQuantity;
	}
    

	public void setPurchasedQuantity(Integer purchasedQuantity) {
		this.purchasedQuantity = purchasedQuantity;
	}


	public LocalDate getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(LocalDate purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	


	public Sales(Long id, String customerName, String remark, Integer price, Double discount, String address,
			String mobile, String email, Integer purchasedQuantity, LocalDate purchasedDate, LocalDate deliveryDate, Stock stock) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.remark = remark;
		this.price = price;
		this.discount = discount;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
		this.purchasedQuantity = purchasedQuantity;
		this.purchasedDate = purchasedDate;
		this.deliveryDate = deliveryDate;
		this.stock = stock;
	}

	public Sales() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Sales [id=" + id + ", customerName=" + customerName + ", remark=" + remark + ", price=" + price
				+ ", discount=" + discount + ", address=" + address + ", mobile=" + mobile + ", email=" + email
				+ ", purchasedQuantity=" + purchasedQuantity + ", purchasedDate=" + purchasedDate + ", deliveryDate="
				+ deliveryDate + ", stock=" + stock + "]";
	}

	

    
}
