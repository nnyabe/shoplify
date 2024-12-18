package com.shoplify.shoplify.api.interfaces.controllers;

import com.shoplify.shoplify.api.model.CategoryTreeResponseDTO;
import com.shoplify.shoplify.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface CategoryControllerInterface {

    ResponseEntity<Category> createCategory(Category category, Long parentId);

    ResponseEntity<Category> getCategory(Long id);
    public ResponseEntity<Category> getCategoryRelations( Long id);

    ResponseEntity<Category> updateCategory(Long id, Category categoryDetails);

    ResponseEntity<Void> deleteCategory(Long id);

    CategoryTreeResponseDTO getAllCategories();
}
