package com.traffsys.stock.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.traffsys.stock.Repository.CategoryRepo;
import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.entity.Category;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public ApiResponse getAllCateogry(int pageNo) {
		 
		Pageable paging = PageRequest.of(pageNo, 100, Sort.by("name").ascending());
		
		Page<Category> pagedData = categoryRepo.findAll(paging);
		
		if (pagedData.getContent().isEmpty())
			return new ApiResponse(0, "No record found", null);
		return new ApiResponse(1, "successfully", pagedData.getContent(), pagedData.getNumber(),
				pagedData.getTotalElements());
	}
	

	@Override
	public ApiResponse getCategoryById(Long id) {
		if(id==null) {
			return new ApiResponse(0,"id not be null",null);
		}

	
		if (!categoryRepo.existsById(id))
			return new ApiResponse(0, "Category id does not exist", null);

		return new ApiResponse(1, "succesfully get category id", categoryRepo.findById(id).get());
	}

	@Override
	public ApiResponse createCategory(Category category) {
		return new ApiResponse(1, "successfully",categoryRepo.save(category));
	}
	
                                                          
	@Override
	public ApiResponse updateCategory(Category category) {
		if(!categoryRepo.existsById(category.getId()))
			return new ApiResponse(0,"data not exist",null);
		categoryRepo.save(category);
		return new ApiResponse(1, "successfully",null);
	}

	@Override
	public ApiResponse deleteCategory(Long id) {
		if(!categoryRepo.existsById(id))
			return new ApiResponse(0,"data not exist",null);
		categoryRepo.deleteById(id);
		return new ApiResponse(1, "successfully",null);
	}

	
  //1
	@Override
	public ApiResponse GetAllCategoryQuery() {
	    List<Category>c=categoryRepo.findAllCategories();
		return new ApiResponse(1,"success",c);
	}
	
	//2
	@Override
	public ApiResponse getCategoryByName(String name) {
		Category ct = categoryRepo.findCategoryByName(name);
		return new ApiResponse(1,"Success",ct);
	}
	
	//3 
	@Override
	public ApiResponse countAllCategories(String category) 
	{
		Long ct=categoryRepo.countTotalCategories(category);
		return new ApiResponse(1,"success",ct);
	}
	
	
	
	/*
	@Override
	public List<Category> getAllCateogry() {

		return categoryRepo.findAll();
	}

	@Override
	public Category createCategory(Category category) {

		return categoryRepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		if (category.getId() == null || !categoryRepo.existsById(category.getId())) {
			// TODO Data not exist
			throw new RuntimeException("Data not found");
		}
		return categoryRepo.save(category);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepo.deleteById(id);
	}
	
	@Override
	public Category getCategoryById(Long id) {
		return categoryRepo.findById(id).orElse(null);
	}
	
	*/
	

/*
	public List<Category> getAllCategories() {
        return categoryRepo.findAllCategories();
    }
*/

}
