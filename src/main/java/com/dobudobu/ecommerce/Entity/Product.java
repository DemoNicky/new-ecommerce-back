package com.dobudobu.ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "tb_product")
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
    private Long price;

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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate deletedAt;

    private Boolean active = Boolean.TRUE;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
    private Category categories;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_and_image_data",
    joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_data_id")}
    )
    private List<ImageData> imageData;
}
