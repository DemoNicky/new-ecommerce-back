package com.dobudobu.ecommerce.DTO;

import com.dobudobu.ecommerce.Entity.Category;
import com.dobudobu.ecommerce.Entity.ImageData;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ProductResponse<T> {

    private String name;

    private String desc;

    private BigDecimal price;

    private Integer stock;

    private LocalDate createdAt;

    private T categories;

    private T image;

}
