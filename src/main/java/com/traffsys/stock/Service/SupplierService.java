package com.traffsys.stock.Service;



import com.traffsys.stock.dto.ApiResponse;

import com.traffsys.stock.entity.Supplier;

public interface SupplierService {
	

	ApiResponse getAllSupplier(int pageNo);

	ApiResponse createSupplier(Supplier supplier);

	ApiResponse getSupplierById(Long id);

	ApiResponse deleteSupplier(Long id);

	ApiResponse updateSupplier(Supplier supplier);
	
	

	// Supplier repository
	ApiResponse findByNameSupplier(String supplierName);

	ApiResponse findAllSupplierWithStockItems(String stock);

	ApiResponse SupplierWithRecentSales();

	ApiResponse findAllSupplier();
       
	

}
