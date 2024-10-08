package com.traffsys.stock.Controller;


import java.time.LocalDate;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traffsys.stock.Service.SalesService;
import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.dto.SalesRequest;
import com.traffsys.stock.entity.Sales;

@CrossOrigin
@RestController
@RequestMapping("/sales")
public class SalesController {

	@Autowired
    SalesService service;

	
	
	@GetMapping("")
	public ApiResponse getAllSales(@RequestParam("pageNo") int pageNo) {
		return service.getAllSales(pageNo);
	}
	
	
	@GetMapping("/{id}")
	public ApiResponse getSalesById(@PathVariable Long id) {
		return service.getSalesById(id);
	}
	
	@PostMapping("/createSales")
	public ApiResponse createSales(@RequestBody Sales sale) {
		System.out.println("HJ");
		return service.createSales(sale);
	}
	
	@PutMapping("/updateSales")
	public ApiResponse udpateSales(@RequestBody Sales sale) {
		return service.updateSales(sale);
	}
	
	@DeleteMapping("deleteSales/{id}")
	public ApiResponse deleteSales(@PathVariable Long id) {
		return service.deleteSales(id);
	}
	
	
	//These are the controller for all the query in SalesRepo
	
	
	//1
	@GetMapping("countTotalSalesByStock")
	public ApiResponse countTotalSalesByStock(@RequestParam("id") Long id) {
		return service.countTotalSalesByStock(id);
	}
	
	//2
	@GetMapping("SalesGroupedByCustomer")
	public ApiResponse findTotalSalesreferenceToCustomer(@RequestParam("name") String name) {
		return service.findTotalSalesreferenceToCustomer(name);
	}
	
    //3
	@GetMapping("salesByName")
	public ApiResponse getSalesByName(@RequestParam("name") String name) {
		return service.SalesAddress(name);
	}
	
	//4
	@GetMapping("salesbetweendate")
	public ApiResponse findSalesBetweenDate(
		@RequestParam("purchased_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate purchasedDate,
		@RequestParam("delivery_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deliveryDate) {

		return service.SalesBetweenDate(purchasedDate, deliveryDate);
		
	}
    
	
	 @GetMapping("/total-sales-monthly")
	    public ApiResponse getTotalSalesMonthly() {
		  
	    
	      return service.getTotalSalesMonthly();
	    }
	 
	 @GetMapping("/lastMonthSales")
	    public ApiResponse getLastMonthSales() {

	        LocalDate today = LocalDate.now();
	       
	        LocalDate firstDayOfLastMonth = today.minusMonths(1).withDayOfMonth(1);
	        LocalDate lastDayOfLastMonth = today.minusMonths(1).withDayOfMonth(today.minusMonths(1).lengthOfMonth());

	    
	        return service.lastMonthSales(firstDayOfLastMonth, lastDayOfLastMonth);
	        
	    }
	 
	 
	 
	 @PostMapping("/get-data")
	 public ApiResponse getParticularData(@RequestBody SalesRequest request) {
	     return service.getParticularData(request);
	 }

	 
	 
	 
 
	/*
	@GetMapping("")
	public ResponseEntity<List<Sales>>getAllSales(){
		List<Sales>sales=service.getAllSales();
		return new ResponseEntity<>(sales,HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Sales>getSalesById(@PathVariable Long id){
		Sales sale=service.getSalesById(id);
		if(sale!=null)
		{
			return new ResponseEntity<>(sale,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}
		
	@PostMapping("/createSales")
	public ResponseEntity<Sales>createSales(@RequestBody Sales sale)
	{
		Sales newSales=service.createSales(sale);
		return new ResponseEntity<>(newSales,HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/updateSales/{id}")
	public ResponseEntity<Sales> updateSales(@PathVariable Long id, @RequestBody Sales sale) {
	    Sales existingSales = service.getSalesById(id);
	    if (existingSales != null) {
	    	sale.setId(id);
	        Sales updatedSales = service.updateSales(id,sale);
	        return new ResponseEntity<>(updatedSales, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	
	
	@DeleteMapping("deleteSales/{id}")
	public ResponseEntity<String>deleteSales(@PathVariable Long id)
	{
		Sales existingProduct=service.getSalesById(id);
		if(existingProduct!=null) {
			service.deleteSales(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	*/
	 
	 // @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
    // @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
   //  @RequestParam("categoryName") String categoryName,
   //  @RequestParam("supplierName") String supplierName
	
}
