package com.dobudobu.ecommerce.Service;

import com.dobudobu.ecommerce.DTO.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

public interface ProductService {
    ProductResponse createProduct(String name, String desc, BigDecimal price, Integer stock, Long categoriesId, MultipartFile image) throws IOException;
}
