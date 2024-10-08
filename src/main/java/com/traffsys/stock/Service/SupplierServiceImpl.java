package com.traffsys.stock.Service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.traffsys.stock.Repository.SupplierRepo;
import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.entity.Category;
import com.traffsys.stock.entity.Stock;
import com.traffsys.stock.entity.Supplier;
import com.traffsys.stock.proj.SupplierJoinStockProj;
import com.traffsys.stock.proj.SupplierStockandSalesProj;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepo repoSupplier;

 
	@Override
	public ApiResponse getAllSupplier(int pageNo) {
		/*  this code fetches a specific page of Supplier records from the database, 
		 * checks if any records were found, and returns an appropriate response, 
		 * including the data if it exists.
		 * */
		 
		Pageable paging = PageRequest.of(pageNo, 100, Sort.by("name").ascending());
		//pageNo specifies which page of data to retrieve.
		// The second argument, 1, specifies that each page should have 1 record.
		
		Page<Supplier> pagedData = repoSupplier.findAll(paging);
		if (pagedData.getContent().isEmpty())
			return new ApiResponse(0, "No record found", null);
		return new ApiResponse(1, "successfully", pagedData.getContent(), pagedData.getNumber(),
				pagedData.getTotalElements());
	}
	

	@Override
	public ApiResponse createSupplier(Supplier supplier) {
		return new ApiResponse(1, "success", repoSupplier.save(supplier));
	}

	@Override
	public ApiResponse getSupplierById(Long id) {
		if (!repoSupplier.existsById(id))
			return new ApiResponse(0, "Supplier id does not exist", null);

		return new ApiResponse(1, "succesfull get supplier id", repoSupplier.findById(id).get());
	}

	@Override
	public ApiResponse deleteSupplier(Long id) {
		if (!repoSupplier.existsById(id))
			return new ApiResponse(0, "Data not exist", null);
		repoSupplier.deleteById(id);
		return new ApiResponse(1, "succsefully deleted", null);

	}

	@Override
	public ApiResponse updateSupplier(Supplier supplier) {
		
		

		if (supplier.getId() == null) {
			return new ApiResponse(0, "data not exist", null);
		}
		if (!repoSupplier.existsById(supplier.getId()))
			return new ApiResponse(0, "data not exist", null);
		Supplier updateSupplier = repoSupplier.save(supplier);

		return new ApiResponse(1, "Succesfully updated", updateSupplier);
		
		/*
		 * Supplier existingSupplier = repoSupplier.findById(id).orElse(null);
		 * 
		 * existingSupplier.setName(supplier.getName());
		 * existingSupplier.setContactInfo(supplier.getContactInfo()); Supplier
		 * updatedSupplier = repoSupplier.save(existingSupplier);
		 * 
		 * return updatedSupplier;
		 * 
		 */	

	}


	@Override
	public ApiResponse findByNameSupplier(String supplierName) {
		Supplier sp=repoSupplier.findByNameSupplier(supplierName);
		return new ApiResponse(1,"success",sp);
	}

      
	@Override
	public ApiResponse findAllSupplierWithStockItems(String stock) {
		List<SupplierJoinStockProj> cat=repoSupplier.findAllSuppliersWithStockItems(stock);
		return new ApiResponse(1,"success",cat);
	
	}

	@Override
	public ApiResponse SupplierWithRecentSales() {
	
		List<SupplierStockandSalesProj>s=repoSupplier.findSuppliersWithRecentSales();
		return new ApiResponse(1,"success",s);
	}


	@Override
	public ApiResponse findAllSupplier() {
		
	Long s=repoSupplier.findAllSupplier();
	return new ApiResponse(1,"success",s);
	}

	
	

	/*
	 * if(!repoSupplier.existsById(id)) { return new
	 * ApiResponse(0,"Data not exist",null); } Supplier
	 * existingSupplier=repoSupplier.findById(id).orElse(null);
	 * 
	 * if(existingSupplier!=null) {
	 * existingSupplier.setContactInfo(existingSupplier.getContactInfo());
	 * existingSupplier.setName(existingSupplier.getContactInfo());
	 * repoSupplier.save(existingSupplier); } return new
	 * ApiResponse(1,"Succesfully updated", existingSupplier); }
	 */

}
