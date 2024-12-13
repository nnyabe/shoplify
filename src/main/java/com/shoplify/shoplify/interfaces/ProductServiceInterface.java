package com.shoplify.shoplify.interfaces;

import com.shoplify.shoplify.api.model.ProductDetails;
import com.shoplify.shoplify.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductServiceInterface {
    Page<Product> getProducts(Pageable pageable);

    Optional<Product> getProduct(Long id);

    void updateProduct(ProductDetails product, Long id);

    void deleteProduct(Long id);
}
