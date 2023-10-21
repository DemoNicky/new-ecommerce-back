package com.dobudobu.ecommerce.Repositoy;

import com.dobudobu.ecommerce.Entity.Category;
import com.dobudobu.ecommerce.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findByCategories(Category category, Pageable pageable);



}
