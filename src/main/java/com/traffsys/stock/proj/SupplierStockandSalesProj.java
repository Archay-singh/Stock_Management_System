package com.traffsys.stock.proj;

import java.time.LocalDate;

public interface SupplierStockandSalesProj {

	
	  Long getId();        
	  String getName1();     
	  String getContactInfo();
	  
	  
	  Long getId11();         
	  String getName();   
	  String getDescription(); 
	  Double getPrice();
	  Integer getQuantity();  
	  
	  
	    Long getId1();             
	    String getCustomerName();  
	    String getRemark();        
	    Integer getPrice1();        
	    Double getDiscount(); 
	    String getAddress();       
	    String getMobile();   
	    String getEmail();         
	    Integer getPurchasedQuantity();  
	    LocalDate getPurchasedDate();    
	    LocalDate getDeliveryDate();
	
}
