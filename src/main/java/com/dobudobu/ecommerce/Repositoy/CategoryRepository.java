package com.dobudobu.ecommerce.Repositoy;

import com.dobudobu.ecommerce.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findById(Long id, Pageable pageable);

}
