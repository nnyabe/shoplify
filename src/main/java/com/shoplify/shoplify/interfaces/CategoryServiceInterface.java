package com.shoplify.shoplify.interfaces;

import com.shoplify.shoplify.api.model.CategoryTreeResponseDTO;
import com.shoplify.shoplify.models.Category;
import com.shoplify.shoplify.trees.CategoryNode;

import java.util.List;

public interface CategoryServiceInterface {

    Category createCategory(Category category, Long parentId);

    Category getCategory(Long id);

    Category updateCategory(Long id, Category categoryDetails);

    void deleteCategory(Long id);
    public Category getCategoryByIdWithProducts(Long id);

    CategoryNode getAllCategories();

    CategoryTreeResponseDTO traverseTree(CategoryNode node);
}
