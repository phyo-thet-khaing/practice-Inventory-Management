package com.ptk.demo.service;

import java.util.List;

import com.ptk.demo.dto.request.CategoryRequest;
import com.ptk.demo.dto.response.CategoryResponse;


public interface CategoryService {
	
	CategoryResponse create(CategoryRequest request);
	
	CategoryResponse getById(Long id);
	
	List<CategoryResponse> getAll();
	
	CategoryResponse update(Long id,CategoryRequest request);
	
	void delete(Long id);

}
