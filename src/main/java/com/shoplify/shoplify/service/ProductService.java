package com.shoplify.shoplify.service;

import com.shoplify.shoplify.api.model.ProductDetails;
import com.shoplify.shoplify.interfaces.ProductServiceInterface;
import com.shoplify.shoplify.models.Product;
import com.shoplify.shoplify.models.dao.ProductDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productDAO.findAll(pageable);
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productDAO.findById(id);
    }

    @Override
    public void updateProduct(ProductDetails product, Long id) {
        Optional<Product> productOptional = productDAO.findById(id);
        if (productOptional.isPresent()) {
            Product updateProduct = productOptional.get();
            if(product.getName() != null) {
                updateProduct.setName(product.getName());
            }
            if(product.getDescription() != null) {
                updateProduct.setDescription(product.getDescription());
            }
            if (product.getCategory() != null) {
                updateProduct.setCategory(product.getCategory());
            }
            if (product.getDetails() != null) {
                updateProduct.setDetails(product.getDetails());
            }
            if(product.getPrice() >= 0){
                updateProduct.setPrice(product.getPrice());
            }
            productDAO.save(updateProduct);
        }

    }

    @Override
    public void deleteProduct(Long id) {
        productDAO.deleteById(id);
    }
}
