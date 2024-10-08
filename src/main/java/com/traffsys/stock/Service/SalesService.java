package com.traffsys.stock.Service;


import java.time.LocalDate;
import java.util.List;

import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.dto.SalesRequest;
import com.traffsys.stock.entity.Sales;


public interface SalesService {
	
	
	ApiResponse getAllSales(int pageNo);
	
	ApiResponse getSalesById(Long id);

	ApiResponse createSales(Sales sale);

	ApiResponse updateSales(Sales sale);

	ApiResponse deleteSales(Long id);
	
	ApiResponse  countTotalSalesByStock(Long stockId);
	
	ApiResponse findTotalSalesreferenceToCustomer(String name);

	ApiResponse SalesAddress(String name);

	ApiResponse SalesBetweenDate(LocalDate purchasedDate, LocalDate deliveryDate);

	//ApiResponse getParticularData(LocalDate start_date, LocalDate end_date, String categoryName, String supplierName);
	
	ApiResponse lastMonthSales(LocalDate firstDayOfLastMonth, LocalDate lastDayOfLastMonth);

	ApiResponse getTotalSalesMonthly();

	ApiResponse getParticularData(SalesRequest request);

	




	

}
