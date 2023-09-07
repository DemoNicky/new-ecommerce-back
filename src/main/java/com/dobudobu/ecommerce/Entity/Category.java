package com.dobudobu.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tb_category")
@Data
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", length = 20, nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private List<Product> products;
}
