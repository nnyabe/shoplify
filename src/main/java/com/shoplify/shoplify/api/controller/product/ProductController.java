package com.shoplify.shoplify.api.controller.product;

import com.shoplify.shoplify.api.interfaces.controllers.ProductInterface;
import com.shoplify.shoplify.api.model.ProductDetails;
import com.shoplify.shoplify.models.Product;
import com.shoplify.shoplify.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController implements ProductInterface {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "10") int size
            , @RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "asc") String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(order), sort);
        Page<Product> products = productService.getProducts(pageable);

        return ResponseEntity.ok(products);
    }

    @Override
    @GetMapping("/{product_id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long product_id){
        return ResponseEntity.ok(productService.getProduct(product_id));
    }

    @Override
    @PutMapping("/{product_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable Long product_id, @RequestBody ProductDetails productDetails) {
        productService.updateProduct(productDetails, product_id);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{product_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long product_id) {
        productService.deleteProduct(product_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

