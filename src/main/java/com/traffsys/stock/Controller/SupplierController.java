package com.traffsys.stock.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traffsys.stock.Service.SupplierService;
import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.entity.Supplier;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	SupplierService service;
	
	@GetMapping("getSupplier")
	public ApiResponse findByNameSupplier(@RequestParam("supplierName") String supplierName) {
		return service.findByNameSupplier(supplierName);
		
	}   
	@GetMapping("/getallSupplier")
	public ApiResponse getAllSupplier(@RequestParam("pageNo") int pageNo) {
		
	    return service.getAllSupplier(pageNo);
	}
	
	@GetMapping("/{id}")
	public ApiResponse getSupplierById(@PathVariable Long id) {
		return service.getSupplierById(id);
	}
	@CrossOrigin
	@PostMapping("/createSupplier")
	public ApiResponse createSupplier(@RequestBody Supplier supplier) {
	System.out.println("hello");
		return service.createSupplier(supplier);
	}
	
		@PutMapping("/updateSupplier")
	public ApiResponse updateSupplier(@RequestBody Supplier supplier) {
		return service.updateSupplier(supplier);
	}
	

	@DeleteMapping("/delete/{id}")
	public ApiResponse deleteSupplier(@PathVariable Long id) {
		return service.deleteSupplier(id);

	}
	
	
	
	//These are the controller for all the query in SupplierRepo
	
	
	@GetMapping("/supplierwithStockItems")
	public ApiResponse findAllSuppliersWithStockItems(@RequestParam("stock") String Stock) {
		return service.findAllSupplierWithStockItems(Stock);
	}
	
	@GetMapping("/SuppliersWithRecentSales")
	public ApiResponse SuppliersWithRecentSales() {
		return service.SupplierWithRecentSales();
	}
	
	
	@GetMapping("/getAllSupplier")
	public ApiResponse findAllSupplier()
	{	
		return  service.findAllSupplier();
	}
	
	

/*	@GetMapping("/getallSupplier")
	public ResponseEntity<List<Supplier>> getAllSupplier() {
		List<Supplier> allsupplier = service.getAllSupplier();
		return new ResponseEntity<>(allsupplier, HttpStatus.OK);
	}
	*/
	

//	@GetMapping("/{id}")
//	public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
//		Supplier supplier = service.getSupplierById(id);
//		if (supplier != null) {
//			return new ResponseEntity<>(supplier, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//		}
//
//	}
	

//	@PostMapping("/createSupplier")
//	public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
//		Supplier newSupplier = service.createSupplier(supplier);
//		return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
//		
//
//	}
	
	

//	@PutMapping("/updateSupplier/{id}")
//	public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
//		ResponseEntity<Supplier> existingSupplier = getSupplierById(id);
//		if (existingSupplier != null) {
//			supplier.setId(id);
//			Supplier updateSupplier = service.updateSupplier(id, supplier);
//			return new ResponseEntity<>(updateSupplier, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//
//	}
	
	
	/*
	
	@GetMapping("/getallSupplier")
	public ApiResponse getAllSupplier(@RequestParam("pageNo") int pageNo, @RequestHeader("Autorization") String token) {
		if(token == null) {
			return new ApiResponse(0, "Unauthorized", null);
		}
		decodeToken(token);
	    return service.getAllSupplier(pageNo);
	}
	
	private void decodeToken(String saltStr){
		System.err.println(saltStr);
    	saltStr = Base64.getDecoder().decode(saltStr.getBytes()).toString();
    	String expiryTime = saltStr.split("|")[1];
    	System.err.println(expiryTime);
    	
    }
	*/
	

	//handler                      -->interceptor
	//prepost                      -->filter
	                                //-->jwt tokens
	
    
} 
