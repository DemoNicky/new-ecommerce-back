package com.dobudobu.ecommerce.Controller;

import com.dobudobu.ecommerce.DTO.CategoryRequest;
import com.dobudobu.ecommerce.DTO.CategoryResponse;
import com.dobudobu.ecommerce.DTO.GetCategoryResponse;
import com.dobudobu.ecommerce.DTO.ResponseHandling;
import com.dobudobu.ecommerce.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            path = "/{categoryId}/{paging}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseHandling<GetCategoryResponse>> getCategory(@PathVariable("categoryId") Long categoryId,
                                                                             @PathVariable("paging")int page){
        try{
            Pageable pageable = PageRequest.of(page, 10);
            GetCategoryResponse categoryResponses = categoryService.getCategory(categoryId, pageable);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseHandling.<GetCategoryResponse>builder().data(categoryResponses).build());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseHandling.<GetCategoryResponse>builder()
                            .errors("Category dengan id " + categoryId + " tidak dapat di temukan").build());
        }

    }

}


