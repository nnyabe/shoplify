package com.shoplify.shoplify.api.model;

import java.util.List;

public class CategoryTreeResponseDTO {

    private Long id;
    private String name;
    private List<CategoryTreeResponseDTO> children;

    public CategoryTreeResponseDTO(Long id, String name, List<CategoryTreeResponseDTO> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryTreeResponseDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryTreeResponseDTO> children) {
        this.children = children;
    }
}
