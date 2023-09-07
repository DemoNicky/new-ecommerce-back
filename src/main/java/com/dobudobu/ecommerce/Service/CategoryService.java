package com.dobudobu.ecommerce.Service;

import com.dobudobu.ecommerce.DTO.CategoryRequest;
import com.dobudobu.ecommerce.DTO.CategoryResponse;
import com.dobudobu.ecommerce.DTO.GetCategoryResponse;

public interface CategoryService {
    CategoryResponse createCategories(CategoryRequest categoryRequest);

    GetCategoryResponse getCategory(Long categoryId);
}
