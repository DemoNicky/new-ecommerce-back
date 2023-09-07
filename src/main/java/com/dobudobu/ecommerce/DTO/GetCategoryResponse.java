package com.dobudobu.ecommerce.DTO;

import com.dobudobu.ecommerce.Entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetCategoryResponse {

    private String categoryName;

    private List<Product> products;
}
