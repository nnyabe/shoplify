package com.shoplify.shoplify.service;

import com.shoplify.shoplify.api.model.CategoryTreeResponseDTO;
import com.shoplify.shoplify.interfaces.CategoryServiceInterface;
import com.shoplify.shoplify.models.Category;
import com.shoplify.shoplify.models.dao.CategoryDAO;
import com.shoplify.shoplify.trees.CategoryNode;
import com.shoplify.shoplify.trees.CategoryTree;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceInterface {

    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

   @Override
    @Transactional
    public Category createCategory(Category category, Long parentId) {
        if (parentId != null) {
            Optional<Category> parentOptional = categoryDAO.findById(parentId);
            if (parentOptional.isPresent()) {
                Category parent = parentOptional.get();
                category.setParent(parent);

                if (parent.getLeftPosition() == null) {
                    parent.setLeftPosition(category);
                } else if (parent.getRightPosition() == null) {
                    parent.setRightPosition(category);
                } else {
                    throw new IllegalStateException("Parent category already has two children");
                }
            }
        }
        return categoryDAO.save(category);
    }

    @Override
    @Transactional
    public Category getCategoryByIdWithProducts(Long id) {
        // Use the custom query from the CategoryDAO
        Optional<Category> category = categoryDAO.findCategoryByIdWithProducts(id);

        // If category is not present, throw an exception
        return category.orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Override
    public Category getCategory(Long id) {
        return categoryDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Override
    @Transactional
    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        category.setName(categoryDetails.getName());

        return categoryDAO.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        if (category.getLeftPosition() != null) {
            deleteCategory(category.getLeftPosition().getId());
        }
        if (category.getRightPosition() != null) {
            deleteCategory(category.getRightPosition().getId());
        }

        // Remove references from parent category (if it exists)
        if (category.getParent() != null) {
            if (category.getParent().getLeftPosition() == category) {
                category.getParent().setLeftPosition(null);
            } else if (category.getParent().getRightPosition() == category) {
                category.getParent().setRightPosition(null);
            }
        }

        categoryDAO.delete(category);
    }

    @Override
    public CategoryNode getAllCategories() {
        List<Category> categories = categoryDAO.findAll();
        CategoryTree categoryTree = new CategoryTree();
        return categoryTree.buildTree(categories);
    }

    @Override
    public CategoryTreeResponseDTO traverseTree(CategoryNode node) {

        if (node == null) {
            return null;
        }


        List<CategoryTreeResponseDTO> children = new ArrayList<>();

        if (node.getLeft() != null) {
            children.add(traverseTree(node.getLeft()));
        }
        if (node.getRight() != null) {
            children.add(traverseTree(node.getRight()));
        }

        return new CategoryTreeResponseDTO(
                node.getCategory().getId(),
                node.getCategory().getName(),
                children
        );
    }
}
