package com.dobudobu.ecommerce.Controller;

import com.dobudobu.ecommerce.DTO.CategoryRequest;
import com.dobudobu.ecommerce.DTO.CategoryResponse;
import com.dobudobu.ecommerce.DTO.GetCategoryResponse;
import com.dobudobu.ecommerce.DTO.ResponseHandling;
import com.dobudobu.ecommerce.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseHandling<CategoryResponse> createCategories(@RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = categoryService.createCategories(categoryRequest);
        return ResponseHandling.<CategoryResponse>builder().data(categoryResponse).build();
    }

    @GetMapping(
            path = "/{categoryId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseHandling<GetCategoryResponse> getCategory(@PathVariable("categoryId") Long categoryId){
        GetCategoryResponse categoryResponses = categoryService.getCategory(categoryId);
        return ResponseHandling.<GetCategoryResponse>builder().data(categoryResponses).build();
    }

}


