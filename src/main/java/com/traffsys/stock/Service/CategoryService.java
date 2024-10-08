package com.traffsys.stock.Service;


import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.entity.Category;

public interface CategoryService {

	ApiResponse getAllCateogry(int pageNo);

	ApiResponse getCategoryById(Long id);
	
	ApiResponse createCategory(Category category);

	ApiResponse updateCategory(Category category);

	ApiResponse deleteCategory(Long id);
	
	
	ApiResponse getCategoryByName(String name);

	ApiResponse GetAllCategoryQuery();

	ApiResponse countAllCategories(String category);


 }
