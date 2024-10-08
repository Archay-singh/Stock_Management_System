package com.traffsys.stock.Controller;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.traffsys.stock.Service.StockService;
import com.traffsys.stock.dto.ApiResponse;
	import com.traffsys.stock.entity.Stock;

@CrossOrigin
@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	StockService service;
	
	@GetMapping()
	public ApiResponse getAllStock(@RequestParam("pageNo") int pageNo) {
		return service.getAllStock(pageNo);
		
	}
	
	@GetMapping("/{id}")
	public ApiResponse getStockById(@PathVariable Long id) {
		return service.getStockById(id);
	}
	
	
	@PostMapping("/createStock")
	public ApiResponse createStock(@RequestBody Stock stock) {
		
		return service.createStock(stock);
	}
	
	
	@PutMapping("/updateStock")
	public ApiResponse updateStock(@RequestBody Stock stock) {
		return service.updateStock(stock);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ApiResponse deleteStock(@PathVariable Long id) {
		return service.deleteStock(id);
	}
	
	@PostMapping("/orderStock/{id}")
	public ApiResponse orderStock(@PathVariable Long id, @RequestParam int quantity) {
      return service.orderStock(id,quantity);
	}
	
	@PostMapping("/receivePayement/{id}")
	public ApiResponse receivePayement(@PathVariable Long id) {
		return service.receivePayement(id);
	}
	@PostMapping("/canceOrder/{id}")
	public ApiResponse cancelOrder(@PathVariable Long id,@RequestParam int cancelquantity) {
		return service.cancelOrder(id,cancelquantity);
	}
	
	//These are the controller for all the stockRepo
	 
	 //1
	 @GetMapping("/findStocksByCategory")
	 public ApiResponse findStocksByCategory(@RequestParam("categroyId") Long categoryid) {
		 return service.findStocksByCategoryId(categoryid);
	 }
	 
	 
	 //2
	 @GetMapping("/getByStock")
		public ApiResponse getStockByName(@RequestParam("stockName") String stockName) {
	    return service.findStockBytheirName(stockName);
		    }
	 
	 //3
	 @GetMapping("getByCategory")
		public ApiResponse getAllStockByCategory(@RequestParam("categoryName") String categoryName) {
			return service.getAllStockByCategory(categoryName);
		}
	 
	 
	 //4
	 @GetMapping("customfieldStock")
	 public ApiResponse getCustomFieldOfStock(@RequestParam("categoryNameForStock") String categoryNameForStock) {
		 return service.CustomFildsofStock(categoryNameForStock);
	 }
	 
	 //5
	 @GetMapping("/getstockbyasc")
	 public ApiResponse  findAllStocksSortedByPrice(@RequestParam("ascprice") String stock) {
		 return service.findAllStockByPrice(stock);
	 }
	 
	 //6
	 @GetMapping("/getcategoryquantity")
	 public ApiResponse findTotalQuantityReferenceToCategory(@RequestParam("CategoryName") String categoryName) {
		 return service.findTotalQuantityReferenceToCategory(categoryName);
	 } 
	 
	 
	 @GetMapping("/payementPending")
	 public ApiResponse findPayementPending() {
		 return service.findPayementPending();
	 }
	 
/*
	@GetMapping()
	public ResponseEntity<List<Stock>>getAllStock(){
		List<Stock>allstock=service.getAllStock();
		return new ResponseEntity<>(allstock,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Stock>getStockById(@PathVariable Long id){
		Stock stock=service.getStockById(id);
		
		if(stock!=null) {
		return new ResponseEntity<>(stock,HttpStatus.OK);
		}
		else {
	    return new ResponseEntity<>(stock,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/createStock")
    public ResponseEntity<Stock>createStock(@RequestBody Stock stock){
		Stock newStock=service.createStock(stock);
		return new ResponseEntity<>(newStock,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/updateStock/{id}")
	public ResponseEntity<Stock>updateStock(@PathVariable Long id,@RequestBody Stock stock)
	{
		Stock existingStock=service.getStockById(id);
		if(existingStock!=null) {
			stock.setId(id);
			Stock updatedStock=service.updateStock(id,stock);
			return new ResponseEntity<>(updatedStock,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteStock(@PathVariable Long id){
		Stock existingStock=service.getStockById(id);
		if(existingStock!=null) {
			service.deleteStock(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	*/
	
}
