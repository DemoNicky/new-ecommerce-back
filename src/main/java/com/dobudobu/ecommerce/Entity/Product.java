package com.dobudobu.ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "tb_product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "product_description", length = 300, nullable = false)
    private String desc;

    @Column(name = "product_price", nullable = false)
    private BigDecimal price;

    @Column(name = "stock", length = 300)
    private Integer stock;

    @Column(name = "sold_item")
    private Integer soldItem;

    @Column(name = "created_at")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdAt;

    @Column(name = "modified_at")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate modifiedAt;

    @Column(name = "deleted_at")
    @JsonFormat(pattern = "dd-MM-yyyy ")
    private LocalDate deletedAt;

    @Builder.Default
    private Boolean deleted = Boolean.FALSE;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private Category categories;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageData imageData;
}
