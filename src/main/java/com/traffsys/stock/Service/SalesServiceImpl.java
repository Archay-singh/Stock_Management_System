package com.traffsys.stock.Service;



import java.time.LocalDate; 


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.traffsys.stock.Repository.SalesRepo;
import com.traffsys.stock.Repository.StockRepo;

import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.dto.SalesRequest;
import com.traffsys.stock.entity.Sales;
import com.traffsys.stock.entity.Stock;

import com.traffsys.stock.proj.LastMonthSalesProj;
import com.traffsys.stock.proj.MontlySalesProj;
import com.traffsys.stock.proj.SpecialProj;
import com.traffsys.stock.proj.TotalSalesProj;

import jakarta.transaction.Transactional;


@Service
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	private SalesRepo repoSales;
	
	@Autowired
	private StockRepo stockRepo;
	
	

	@Override
	public ApiResponse getAllSales(int pageNo) {
		Pageable paging = PageRequest.of(pageNo, 100, Sort.by("customerName").ascending());
	  
		 
		Page<Sales> pagedData = repoSales.findAll(paging);
		if (pagedData.getContent().isEmpty())
			return new ApiResponse(0, "No record found", null);
		return new ApiResponse(1, "successfully", pagedData.getContent(), pagedData.getNumber(),
				pagedData.getTotalElements());
	}

	@Override
	public ApiResponse getSalesById(Long id) { 
		if(!repoSales.existsById(id)) {
			return new ApiResponse(0,"id is not found",null);
		} 
		return new ApiResponse(1,"succesfull",repoSales.findById(id).get());
	}
    
	
	@Transactional  //This annotation indicates that the method should be executed within a transaction. Any database changes made in the method will either be committed together or rolled back if an exception occurs, ensuring atomicity.
	@Override
	public ApiResponse createSales(Sales sale) {
		sale = repoSales.save(sale);
		Stock stock = stockRepo.findById(sale.getStock().getId()).get();
		int totalStock = stock.getQuantity() - sale.getPurchasedQuantity();
		stock.setQuantity(totalStock); //updating quantity using transactional annotation
		//stockRepo.save(stock);
		//Stock
		
		//stockRepo.updateSaleQuantity(totalStock, sale.getStock().getId());
		return new ApiResponse(1, "success", sale);
	}

	@Override
	public ApiResponse updateSales(Sales sale) {
		if(sale.getId()==null) {
			return new ApiResponse(0,"id does not exist",null);
		}
		if(!repoSales.existsById(sale.getId()))
			return new ApiResponse(0,"data not exist",null);
		
		return new ApiResponse(1, "successfully",repoSales.save(sale));
	}
	
	

	@Override
	public ApiResponse deleteSales(Long id) {
	   if(!repoSales.existsById(id)) {
		   return new ApiResponse(0,"id is not exist",null);
	   }
	   repoSales.deleteById(id);
		return new ApiResponse(1,"successfully deleted",null);
	}
	

	//1
	@Override
	public ApiResponse countTotalSalesByStock(Long stockId) {
		
		Long st=repoSales.countTotalSalesByStock(stockId);
		return new ApiResponse(1,"success",st);
	}
	
    //2
	@Override
	public ApiResponse findTotalSalesreferenceToCustomer(String name) {
		List<TotalSalesProj>s=repoSales.findTotalSalesGroupedByCustomer(name);
		return new ApiResponse(1,"success",s);
	}
	//3
	@Override
	public ApiResponse SalesAddress(String name) {
		List<Sales>s=repoSales.findAllSalesByaddress(name);
		return new ApiResponse(1,"success",s);
	}
	//4
	@Override
	public ApiResponse SalesBetweenDate(LocalDate purchased_date, LocalDate delivery_date) {
		List<Sales> s=repoSales.findSalesBetweenDate(purchased_date, delivery_date);
		 for(int i=0;i<s.size();i++)
		 {
			 s.get(i).setStock(null);
		 }
		return new ApiResponse(1,"success",s);
	}


	@Override
	public ApiResponse lastMonthSales(LocalDate firstDayOfLastMonth, LocalDate lastDayOfLastMonth) {
		   List<LastMonthSalesProj> salesData =repoSales.findLastMonthSales(firstDayOfLastMonth, lastDayOfLastMonth);

	
	        if (salesData.isEmpty()) {
	            return new ApiResponse(0,"No sales data found for the last month.",null);
	        }

	      return new ApiResponse(1,"success",salesData);
	    }

	@Override
	public ApiResponse getTotalSalesMonthly() {
		List<MontlySalesProj>salesbymonth=repoSales.findTotalSalesMonthly();
    	return new ApiResponse(1,"success",salesbymonth);
	}


	@Override
	public ApiResponse getParticularData(SalesRequest request) {
		    LocalDate startDate = request.getStartDate();
		    LocalDate endDate = request.getEndDate();
		    String categoryName =request.getCategoryName();
		    String supplierName = request.getSupplierName();
	    
	    List<SpecialProj> data = repoSales.gettingdata(startDate, endDate, categoryName, supplierName);
	    
	  
	    if (data.isEmpty()) {
	        return new ApiResponse(0, "No sales data found ", null);
	    }
	    
	    return new ApiResponse(1, "success", data);
	    
	}
		    
		    // List<Map<String,Object>> mList=null;
		//    List<SpecialProj> data = repoSales.gettingdata(startDate, endDate, categoryName, supplierName);
		 //   return new ApiResponse(1,"success",data);
		    
		 //   if (categoryName != null && supplierName != null) {
		 //       mList=repoSales.gettingdata(startDate, endDate, categoryName, supplierName);
		 //   } else if (categoryName != null && supplierName==null) {
		 //       mList = repoSales.getAllSalesByCategory(startDate, endDate, categoryName);
		 //   } else if (supplierName != null&& categoryName==null) {
		  //      mList = repoSales.getAllSalesBySupplier(startDate,endDate,supplierName);
		 //   }	
   
}
	
	
	
	
	/*
	 * 
	@Override
	public List<Sales> getAllSales() {
		
		return repoSales.findAll();
	}

	@Override
	public Sales getSalesById(Long id) {
		return repoSales.findById(id).orElse(null);
	}

	@Override
	public Sales createSales(Sales sale) {
		return repoSales.save(sale);
	}

	@Override
	public Sales updateSales(Long id,Sales sale) {
	 Sales existingSales=repoSales.findById(id).orElse(null);
	 existingSales.setAddress(existingSales.getAddress());
	 existingSales.setCustomerName(existingSales.getCustomerName());
	 existingSales.setDiscount(existingSales.getDiscount());
	 existingSales.setEmail(existingSales.getEmail());
	 existingSales.setMobile(existingSales.getMobile());
	 existingSales.setPrice(existingSales.getPrice());
	 existingSales.setPurchasedQuantity(existingSales.getPurchasedQuantity());
	 existingSales.setQuantity(existingSales.getQuantity());
	 existingSales.setRemark(existingSales.getRemark());
	 return existingSales;
		
	}

	@Override
	public String deleteSales(Long id) {
	
	   repoSales.deleteById(id);
	   return "Sales record deleted successfully";
	}
	
	*/
	

