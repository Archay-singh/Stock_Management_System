package com.traffsys.stock.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.entity.Category;
import com.traffsys.stock.entity.Stock;
import com.traffsys.stock.proj.StockProj;
import com.traffsys.stock.proj.StockTotalQuantityProj;
import com.traffsys.stock.proj.TotalSalesProj;

import jakarta.transaction.Transactional;

@Repository
public interface StockRepo extends JpaRepository<Stock,Long> {
	
	//1
	//give all stock with their particular category id
    @Query(value = "SELECT * FROM stock WHERE category_id = ?1", nativeQuery = true)
    List<Stock> findStocksByCategoryId(@Param("categoryId") Long categoryId);
    
    
    //2
    @Query(value="SELECT * FROM Stock WHERE name = ?1", nativeQuery = true)
    Stock findStockBytheirName(String name);

    //3
    @Query(value = "SELECT stk.* from stock AS stk INNER JOIN category AS ct ON ct.id=stk.category_id WHERE ct.name=?1", nativeQuery = true)
    List<Stock> getAllStockByCategoryRepo(String categoryName);

	
	//4
	//Extracting custom fields
	@Query(value = "SELECT stk.name,stk.description from stock AS stk INNER JOIN category AS ct ON ct.id=stk.category_id WHERE ct.name=?1", nativeQuery = true)
	List<StockProj> getStockByCategoryProj(String category);
	
	
    //5
	 @Query(value = "SELECT * FROM stock ORDER BY price ASC", nativeQuery = true)
	        List<Stock> findAllStocksSortedByPrice(String stock);
	
	//6
	// Total Stock Quantity Grouped by Category
	@Query(value = "SELECT c.name AS category_name, SUM(s.quantity) AS total_quantity " +
	            "FROM stock s " +
	            "JOIN category c ON s.category_id = c.id " +
	            "GROUP BY c.name", nativeQuery = true)
	    List<StockTotalQuantityProj>findTotalQuantityGroupedByCategory(String CategoryName);
	
    @Modifying
    @Transactional 
    @Query(value = "UPDATE stock AS stk SET stk.quantity=?1 WHERE stk.id=?2",nativeQuery=true)
	void updateSaleQuantity(int totalStock, Long id);

	@Query(value="select SUM(s.price*s.quantity) from stock s where s.payment_pending=true;",nativeQuery=true)
	Double payementPending();






   
    
    
    
    
    


}

