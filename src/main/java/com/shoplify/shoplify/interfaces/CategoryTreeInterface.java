package com.shoplify.shoplify.interfaces;

import com.shoplify.shoplify.models.Category;
import com.shoplify.shoplify.trees.CategoryNode;

import java.util.List;

public interface CategoryTreeInterface {


    CategoryNode buildTree(List<Category> categories);


    CategoryNode insertNode(CategoryNode node, Category category);


    int getTreeHeight(CategoryNode node);


    boolean hasTwoChildren(Category parent);


    boolean isPositionOccupied(Category parent, boolean isLeftPosition);
}
