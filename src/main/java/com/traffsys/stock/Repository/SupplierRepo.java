package com.traffsys.stock.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.traffsys.stock.entity.Stock;
import com.traffsys.stock.entity.Supplier;
import com.traffsys.stock.proj.SupplierJoinStockProj;
import com.traffsys.stock.proj.SupplierStockandSalesProj;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long> {

	@Query(value = "SELECT * FROM Supplier WHERE contact_info = ?1", nativeQuery = true)
	Supplier findByNameSupplier(String supplierName);

	//TODO add projection
	// query to find all suppliers and their stock items
	@Query(value = "SELECT sup.id AS supplier_id, sup.name AS supplier_name, sup.contact_info AS supplier_contact, "
			+ "st.id AS stock_id, st.name AS stock_name, st.description AS stock_description, st.price AS stock_price, st.quantity AS stock_quantity "
			+ "FROM supplier sup " + "INNER JOIN stock st ON sup.id = st.supplier_id", nativeQuery = true)
	List<SupplierJoinStockProj> findAllSuppliersWithStockItems(String stock);
	

    // Native SQL query to find suppliers who have supplied stock in the last 60 days
    @Query(value = "SELECT DISTINCT s.* FROM supplier s " +
                   "JOIN stock st ON s.id = st.supplier_id " +
                   "JOIN sales sa ON st.id = sa.stock_id " +
                   "WHERE sa.purchased_date >= CURDATE() - INTERVAL 60 DAY", nativeQuery = true)
     List<SupplierStockandSalesProj> findSuppliersWithRecentSales();
    
    @Query(value="Select COUNT(*) from supplier",nativeQuery=true)
    Long findAllSupplier();

	
    
    
    
    
	
}
