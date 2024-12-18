package com.shoplify.shoplify.models.dao;

import com.shoplify.shoplify.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryDAO extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c " +
            "LEFT JOIN FETCH c.products p " +
            "LEFT JOIN FETCH c.parent " +
            "LEFT JOIN FETCH c.leftPosition " +
            "LEFT JOIN FETCH c.rightPosition " +
            "WHERE c.id = :id")
    Optional<Category> findCategoryByIdWithProducts(@Param("id") Long id);
}
