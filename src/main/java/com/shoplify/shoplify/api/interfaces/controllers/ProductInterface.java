package com.shoplify.shoplify.api.interfaces.controllers;
import com.shoplify.shoplify.api.model.ProductDetails;
import com.shoplify.shoplify.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ProductInterface {

    ResponseEntity<Page<Product>> getProducts(int page, int size, String sort, String order);

    ResponseEntity<Optional<Product>> getProductById(Long product_id);

    ResponseEntity<Product> updateProduct(Long product_id, ProductDetails productDetails );

    ResponseEntity<Product> deleteProduct(Long product_id);
}
