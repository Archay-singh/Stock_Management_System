package com.traffsys.stock.dto;

import java.time.LocalDate;



import com.fasterxml.jackson.annotation.JsonFormat;

public class SalesRequest {

   
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
  
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    
	    private String categoryName;
	    private String supplierName;
	    
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public LocalDate getEndDate() {
			return endDate;
		}
		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public String getSupplierName() {
			return supplierName;
		}
		public void setSupplierName(String supplierName) {
			this.supplierName = supplierName;
		}
		public SalesRequest(LocalDate startDate, LocalDate endDate, String categoryName, String supplierName) {
			super();
			this.startDate = startDate;
			this.endDate = endDate;
			this.categoryName = categoryName;
			this.supplierName = supplierName;
		}
		@Override
		public String toString() {
			return "SalesRequest [startDate=" + startDate + ", endDate=" + endDate + ", categoryName=" + categoryName
					+ ", supplierName=" + supplierName + "]";
		}
		public SalesRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    

}
