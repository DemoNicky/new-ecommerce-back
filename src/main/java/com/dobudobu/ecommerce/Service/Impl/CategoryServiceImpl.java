package com.dobudobu.ecommerce.Service.Impl;

import com.dobudobu.ecommerce.DTO.CategoryRequest;
import com.dobudobu.ecommerce.DTO.CategoryResponse;
import com.dobudobu.ecommerce.DTO.GetCategoryResponse;
import com.dobudobu.ecommerce.Entity.Category;
import com.dobudobu.ecommerce.Entity.Product;
import com.dobudobu.ecommerce.Repositoy.CategoryRepository;
import com.dobudobu.ecommerce.Repositoy.ProductRepository;
import com.dobudobu.ecommerce.Service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

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
    public GetCategoryResponse getCategory(Long categoryId, Pageable pageable) {
        Optional<Category> categoryResponse = categoryRepository.findById(categoryId);

        if (!categoryResponse.isPresent() || categoryResponse.isEmpty()){
            throw new NullPointerException("Category not found");
        }

        Page<Product> products = productRepository.findByCategories(categoryResponse.get(), pageable);

        return GetCategoryResponse.builder()
                .categoryName(categoryResponse.get().getCategoryName())
                .products(products.stream().toList())
                .build();

    }

}
