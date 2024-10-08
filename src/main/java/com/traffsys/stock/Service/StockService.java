package com.traffsys.stock.Service;



import com.traffsys.stock.dto.ApiResponse;

import com.traffsys.stock.entity.Stock;

public interface StockService {

	ApiResponse getAllStock(int pageNo);

	ApiResponse createStock(Stock stock);

	ApiResponse getStockById(Long id);

	ApiResponse  deleteStock(Long id);

	ApiResponse updateStock(Stock stock);
	


    
	ApiResponse findAllStockByPrice(String stock);

	ApiResponse findStocksByCategoryId(Long categoryid);
	
	ApiResponse findStockBytheirName(String name);
    
	ApiResponse getAllStockByCategory(String categoryName);
	
	ApiResponse CustomFildsofStock(String categoryName);

	ApiResponse findTotalQuantityReferenceToCategory(String categoryName);

	ApiResponse orderStock(Long id, int quantity);

	ApiResponse receivePayement(Long id);

	ApiResponse cancelOrder(Long id, int cancelquantity);

	ApiResponse findPayementPending();
	
	
}
