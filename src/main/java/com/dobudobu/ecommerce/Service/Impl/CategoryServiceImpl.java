package com.dobudobu.ecommerce.Service.Impl;

import com.dobudobu.ecommerce.DTO.CategoryRequest;
import com.dobudobu.ecommerce.DTO.CategoryResponse;
import com.dobudobu.ecommerce.DTO.GetCategoryResponse;
import com.dobudobu.ecommerce.Entity.Category;
import com.dobudobu.ecommerce.Repositoy.CategoryRepository;
import com.dobudobu.ecommerce.Service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Override
    public CategoryResponse createCategories(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .build();
        categoryRepository.save(category);

        return CategoryResponse.builder()
                .categoryName(category.getCategoryName())
                .build();
    }

    @Override
    public GetCategoryResponse getCategory(Long categoryId) {
        Category categoryResponse = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NullPointerException("Category tidak di temukan"));

        return GetCategoryResponse.builder()
                .categoryName(categoryResponse.getCategoryName())
                .products(categoryResponse.getProducts())
                .build();
    }

}
