package com.traffsys.stock.Controller;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traffsys.stock.Service.CategoryService;
import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.entity.Category; 


@CrossOrigin
@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService serviceCat;
	
	@CrossOrigin
	@GetMapping("get_all")
	public ApiResponse getAllCategories(@RequestParam("pageNo") int pageNo) {
	 
		
		return serviceCat.getAllCateogry(pageNo);
	}
	

	@GetMapping("/{id}")
	public ApiResponse getAllCategoryById(@PathVariable Long id) {
		System.out.println("hello testing");
		return serviceCat.getCategoryById(id);
	}
     
	@PostMapping("/createCategory")
	public ApiResponse createCategory(@RequestBody Category createCategory) {
		return serviceCat.createCategory(createCategory);
	}
	
	 @PutMapping("/updateCat")
	 public ApiResponse updateCategory(@RequestBody Category category)
	 {
		 return serviceCat.updateCategory(category);
	 }
	

	
	@DeleteMapping("/deleteCat/{id}") 
	public ApiResponse deleteCategory(@PathVariable long id) {
		return serviceCat.deleteCategory(id);
	}
	
	
	//These are the controller for all the query in categoryRepo
	
	
	
	//1
	@GetMapping("getAllCategoryQuery")
	public ApiResponse GetAllCategoryQuery() {
		return serviceCat.GetAllCategoryQuery();
	}
	
	//2
	@GetMapping("get_by_name_category")
	public ApiResponse getCategoryByName(@RequestParam("name") String name) {
		return serviceCat.getCategoryByName(name);
	}
	//3
	@GetMapping("getcount")
	public ApiResponse  countTotalCategories(@RequestParam("category") String category) {
		return serviceCat.countAllCategories(category);
	}
	
	
	
	
	/*
	 * 
	  @GetMapping("get_all") 
	  public ResponseEntity<List<Category>> getAllCateogry()
	  { List<Category> categroy = serviceCat.getAllCateogry(); return new
	 ResponseEntity<>(categroy, HttpStatus.OK); }
	 * 
	 * 
	 * 
	 * 
	 * @GetMapping("/{id}") public ResponseEntity<Category>
	 * getCategoryById(@PathVariable Long id) { Category category =
	 * serviceCat.getCategoryById(id); if (category != null) { return new
	 * ResponseEntity<>(category, HttpStatus.OK); } else { return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND);
	 * 
	 * } }
	 * 
	 * @PostMapping("/createCategory") 
	 * public ResponseEntity<Category>
	 * createCategory(@RequestBody Category category) { Category newCategory =
	 * serviceCat.createCategory(category); return new ResponseEntity<>(newCategory,
	 * HttpStatus.CREATED);
	 * 
	 * }
	 * 
	 * @PutMapping("/updateCat")
	 *  public ResponseEntity<Category>
	 * updateCategory(@RequestBody Category category) { Category updatedCategory =
	 * serviceCat.updateCategory(category); return new
	 * ResponseEntity<>(updatedCategory, HttpStatus.OK);
	 * 
	 * }
	 * 
	 * @DeleteMapping("/delteCat/{id}") 
	 * public ResponseEntity<Void>
	 * deleteCategory(@PathVariable Long id) { Category existingCategory =
	 * serviceCat.getCategoryById(id); if (existingCategory != null) {
	 * serviceCat.deleteCategory(id); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } else { return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 */

}
