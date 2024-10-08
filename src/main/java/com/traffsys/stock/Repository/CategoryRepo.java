package com.traffsys.stock.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.traffsys.stock.entity.Category;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

	
	@Query("FROM Category")
	List<Category>findAllCategories();

	
	@Query(value = "SELECT * FROM category WHERE name=?1", nativeQuery = true)
	Category findCategoryByName(String name);
	
	
	
	@Query(value = "SELECT COUNT(*) FROM category", nativeQuery = true)
	Long countTotalCategories(String category);


	
    
}
