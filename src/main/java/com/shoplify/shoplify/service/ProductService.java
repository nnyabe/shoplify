package com.shoplify.shoplify.service;

import com.shoplify.shoplify.models.Product;
import com.shoplify.shoplify.models.dao.ProductDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getProducts() {
        return productDAO.findAll();
    }
}
