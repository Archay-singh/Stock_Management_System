package com.traffsys.stock.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.traffsys.stock.entity.Sales;
import com.traffsys.stock.proj.LastMonthSalesProj;
import com.traffsys.stock.proj.MontlySalesProj;
import com.traffsys.stock.proj.SpecialProj;
import com.traffsys.stock.proj.TotalSalesProj;

@Repository
public interface SalesRepo extends JpaRepository<Sales,Long> {

	 //1
	 // Native SQL query to count total sales by particular stock_id
	 @Query(value = "SELECT COUNT(*) FROM sales WHERE stock_id = ?1", nativeQuery = true)
	 Long countTotalSalesByStock(@Param("stockId") Long stockId);
	 
	 //2
	 //find total sales amount by customer
	 @Query(value="select s.customer_name AS customer_name,SUM(s.price*s.purchased_quantity) AS total_sales from sales s GROUP BY s.customer_name",nativeQuery=true)
	 List<TotalSalesProj>findTotalSalesGroupedByCustomer(String name);

	 //3
	 @Query(value="select * from Sales where address=?1",nativeQuery=true)
	 List<Sales>findAllSalesByaddress(String name);
	 
     //4
	 //Sales between particular date
	 @Query(value = "SELECT * FROM sales WHERE purchased_date BETWEEN :purchasedDate AND :deliveryDate", nativeQuery = true)
	  List<Sales>findSalesBetweenDate(@Param("purchasedDate") LocalDate purchasedDate, @Param("deliveryDate") LocalDate deliveryDate);
	  
	  @Query(value = "SELECT DATE_FORMAT(s.purchased_date, '%Y-%m') AS month_year, SUM(s.price * s.purchased_quantity) AS total_sales " +
              "FROM sales s " +
              "GROUP BY DATE_FORMAT(s.purchased_date, '%Y-%m') " +
              "ORDER BY month_year", nativeQuery = true)
	  List<MontlySalesProj> findTotalSalesMonthly();
	  
	 
	  @Query(value = "SELECT st.name AS productName, SUM(s.purchased_quantity) AS totalQuantity " +
              "FROM sales s JOIN stock st ON s.stock_id = st.id " +
              "WHERE s.purchased_date >= ?1 AND s.delivery_date <= ?2 " +
              "GROUP BY st.name", nativeQuery = true)
      List<LastMonthSalesProj>findLastMonthSales(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
      
	
	

	  @Query(value = "SELECT sp.name AS supplierName,\r\n"
	  		+ "st.name AS stockName,\r\n"
	  		+ "st.price AS pricePerStock,\r\n"
	  		+ "s.purchased_quantity AS SalesQuantity,\r\n"
	  		+ "st.payment_pending AS paymentStatus\r\n"
	  		+ "FROM sales s\r\n"
	  		+ "INNER JOIN stock st ON s.stock_id = st.id\r\n"
	  		+ "INNER JOIN Category c ON st.category_id = c.id\r\n"
	  		+ "INNER JOIN Supplier sp ON st.supplier_id = sp.id\r\n"
	  		+ "WHERE s.purchased_date BETWEEN :startDate AND :endDate\r\n"
	  		+ "AND (:categoryName IS NULL OR c.name = :categoryName)\r\n"
	  		+ "AND (:supplierName IS NULL OR sp.name = :supplierName)\r\n",nativeQuery = true)
      List<SpecialProj> gettingdata(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate, @Param("categoryName") String categoryName,@Param("supplierName") String supplierName);
}
