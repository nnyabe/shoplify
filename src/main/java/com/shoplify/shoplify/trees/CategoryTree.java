package com.shoplify.shoplify.trees;
import com.shoplify.shoplify.interfaces.CategoryTreeInterface;
import com.shoplify.shoplify.models.Category;

import java.util.List;

public class CategoryTree implements CategoryTreeInterface {
    private CategoryNode root;

    @Override
    public CategoryNode buildTree(List<Category> categories) {
        for (Category category : categories) {
            root = insertNode(root, category);
        }
        return root;
    }

    @Override
    public CategoryNode insertNode(CategoryNode node, Category category) {
        if (node == null) {
            return new CategoryNode(category);
        }

        int leftHeight = getTreeHeight(node.getLeft());
        int rightHeight = getTreeHeight(node.getRight());

        // Insert into the smaller subtree to maintain balance
        if (leftHeight <= rightHeight) {
            node.setLeft(insertNode(node.getLeft(), category));
        } else {
            node.setRight(insertNode(node.getRight(), category));
        }

        return node;
    }


    @Override
    public int getTreeHeight(CategoryNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getTreeHeight(node.getLeft()), getTreeHeight(node.getRight())) + 1;
    }

    @Override
    public boolean hasTwoChildren(Category parent) {
        return parent.getLeftPosition() != null && parent.getRightPosition() != null;
    }

    @Override
    public boolean isPositionOccupied(Category parent, boolean isLeftPosition) {
        if (isLeftPosition) {
            return parent.getLeftPosition() != null;
        } else {
            return parent.getRightPosition() != null;
        }
    }
}
