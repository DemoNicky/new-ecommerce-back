package com.dobudobu.ecommerce.Controller;

import com.dobudobu.ecommerce.DTO.GetProductResponse;
import com.dobudobu.ecommerce.DTO.ProductResponse;
import com.dobudobu.ecommerce.DTO.ResponseHandling;
import com.dobudobu.ecommerce.Service.ProductService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseHandling<ProductResponse>> productCreate(
            @RequestParam("name") @NotNull String name,
            @RequestParam("desc") @NotNull String desc,
            @RequestParam("price") @NotNull BigDecimal price,
            @RequestParam("stock") @NotNull Integer stock,
            @RequestParam("categoriesId") @NotNull Long categoriesId,
            @RequestParam("image") @NotNull MultipartFile image) throws IOException {
        ProductResponse productResponse = productService.createProduct(name, desc, price, stock, categoriesId, image);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseHandling.<ProductResponse>builder().data(productResponse).build());
    }

    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseHandling<GetProductResponse>> getAllProduct(){
        return null;

    }

}
