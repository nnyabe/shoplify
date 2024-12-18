package com.shoplify.shoplify.api.controller.category;

import com.shoplify.shoplify.api.interfaces.controllers.CategoryControllerInterface;
import com.shoplify.shoplify.api.model.CategoryTreeResponseDTO;
import com.shoplify.shoplify.models.Category;
import com.shoplify.shoplify.service.CategoryService;
import com.shoplify.shoplify.trees.CategoryNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController implements CategoryControllerInterface {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category, @RequestParam(required = false) Long parentId) {
        Category createdCategory = categoryService.createCategory(category, parentId);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        Category category = categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}/relations")
    public ResponseEntity<Category> getCategoryRelations(@PathVariable Long id) {
        Category category = categoryService.getCategoryByIdWithProducts(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Category updatedCategory = categoryService.updateCategory(id, categoryDetails);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping
    public CategoryTreeResponseDTO getAllCategories() {
        CategoryNode root = categoryService.getAllCategories();

        return categoryService.traverseTree(root);
    }
}
