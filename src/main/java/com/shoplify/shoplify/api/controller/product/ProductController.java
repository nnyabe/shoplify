package com.shoplify.shoplify.api.controller.product;

import com.shoplify.shoplify.models.Product;
import com.shoplify.shoplify.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;

    }


    @GetMapping("/")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
