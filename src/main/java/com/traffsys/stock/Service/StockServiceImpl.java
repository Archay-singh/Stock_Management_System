package com.traffsys.stock.Service;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.traffsys.stock.Repository.StockRepo;
import com.traffsys.stock.dto.ApiResponse;

import com.traffsys.stock.entity.Stock;
import com.traffsys.stock.proj.StockProj;
import com.traffsys.stock.proj.StockTotalQuantityProj;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	StockRepo repoStock;
	
	 @PersistenceContext
	    private EntityManager entityManager;


	@Override
	public ApiResponse getAllStock(int pageNo) {
		Pageable paging = PageRequest.of(pageNo, 100, Sort.by("name").ascending());
	
		Page<Stock> pagedData = repoStock.findAll(paging);
		if (pagedData.getContent().isEmpty())
			return new ApiResponse(0, "No record found", null);
		return new ApiResponse(1, "successfully", pagedData.getContent(), pagedData.getNumber(),
				pagedData.getTotalElements());
	}
	
	@Override
	@Transactional
	public ApiResponse createStock(Stock stock) {
		 if (stock.getCategory() != null) {
			 
	            stock.setCategory(entityManager.merge(stock.getCategory()));
	        }
            
	     
	        if (stock.getSupplier() != null) {
	            stock.setSupplier(entityManager.merge(stock.getSupplier()));
	        }
	        
	        Stock savedStock = repoStock.save(stock);
		
		return new ApiResponse(1,"succesfully created new stock",savedStock);
	}

	@Override
	public ApiResponse getStockById(Long id) {
		if(!repoStock.existsById(id))
			return new ApiResponse(0,"stock id is not found",null);
		
		return new ApiResponse(1,"succesfull",repoStock.findById(id).get());
	}

	
	@Override
	public ApiResponse deleteStock(Long id) {
	   if(!repoStock.existsById(id)) {
		   return new ApiResponse(0,"stock id is not found",null);
	   }
	   repoStock.deleteById(id);
		return new ApiResponse(1,"succesfull",null);
	}

	@Override
	public ApiResponse updateStock(Stock stock) {
		repoStock.save(stock);
		return new ApiResponse(1,"succesfully updated",repoStock.save(stock));
	}
	
	@Override
	public ApiResponse findTotalQuantityReferenceToCategory(String categoryName) {
		 List<StockTotalQuantityProj>stocks = repoStock.findTotalQuantityGroupedByCategory(categoryName);
	     return new ApiResponse(1, "successfully", stocks);
	}
	
	
	@Override
	public ApiResponse findStocksByCategoryId(Long categoryId) {
		List<Stock>stocks=repoStock.findStocksByCategoryId(categoryId);
		return new ApiResponse(1,"success",stocks);
	}
	 @Override
	    public ApiResponse findStockBytheirName(String name) {
	        Stock st = repoStock.findStockBytheirName(name);
	        return new ApiResponse(1, "Success", st);
	    }
	 @Override
		public ApiResponse getAllStockByCategory(String categoryName) {
			// TODO Auto-generated method stub
			List<Stock>stocks = repoStock.getAllStockByCategoryRepo(categoryName);
			//stocks.get(0).setSupplier(null);
			return new ApiResponse(1,"succesfully",stocks);
		}

	 @Override
	 public ApiResponse CustomFildsofStock(String categoryName) {
		 List<StockProj>stocks=repoStock.getStockByCategoryProj(categoryName);
		 return new ApiResponse(1,"success",stocks);
	 }

	 @Override
	 public ApiResponse findAllStockByPrice(String stock ) {
			List<Stock>st=repoStock.findAllStocksSortedByPrice(stock);
			return new ApiResponse(1,"successfull",st);
	}

	@Override
	public ApiResponse orderStock(Long id, int orderquantity) {
		Stock stock = repoStock.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Stock not found"));
		
		 if (orderquantity > stock.getQuantity()) {
	            throw new IllegalArgumentException("Insufficient stock available");
	        }
		 stock.setQuantity(stock.getQuantity() - orderquantity);
		 stock.setPaymentPending(true);
	     stock.setOutOfStock(stock.getQuantity() <= 0);
	         
	      repoStock.save(stock);
	        return new ApiResponse(1, "Order placed successfully", stock);
		
	}

	@Override
	public ApiResponse receivePayement(Long id) {
		
		  Stock stock = repoStock.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Stock not found"));
	        
	        if (!stock.isPaymentPending()) {
	            throw new IllegalStateException("No pending payment");
	        }

	        stock.setPaymentPending(false); 
	        repoStock.save(stock);
			return new ApiResponse(1,"payement receives successfully",stock);
	    }
	 
	 
	  public ApiResponse cancelOrder(Long stockId, int orderQuantity) {
	        Stock stock = repoStock.findById(stockId)
	                .orElseThrow(() -> new EntityNotFoundException("Stock not found"));

	        if (!stock.isPaymentPending()) {
	            throw new IllegalStateException("Cannot cancel order; payment is already received.");
	        }

	        stock.setQuantity(stock.getQuantity() + orderQuantity);
	        stock.setOutOfStock(stock.getQuantity() <= 0);
	        
	        repoStock.save(stock);
	        return new ApiResponse(1, "Order canceled successfully", stock);
	    }

	@Override
	public ApiResponse findPayementPending() {
	 Double amountpending=repoStock.payementPending();
		return new ApiResponse(1,"amount known succesfully",amountpending);
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	
	/*
	@Override
	public List<Stock> getAllStock() {
	 return  repoStock.findAll();
	}

	
	@Override
	public Stock getStockById(Long id) {
	
		return repoStock.findById(id).orElse(null);
	}
	
	
	@Override
	public Stock createStock(Stock stock) {
		return repoStock.save(stock);
		
	}

	@Override
	public String deleteStock(Long id) {
		 repoStock.deleteById(id);
		
		return "stock deleted succesfully";
		
	}

	

	@Override
	public Stock updateStock(Long id, Stock stock) {
		Stock existingStock=repoStock.findById(id).orElse(null);
		existingStock.setName(stock.getName());
		existingStock.setDescription(stock.getDescription());
		existingStock.setPrice(stock.getPrice());
		existingStock.setQuantity(stock.getQuantity());
		return existingStock;
		
	}
	*/

}
