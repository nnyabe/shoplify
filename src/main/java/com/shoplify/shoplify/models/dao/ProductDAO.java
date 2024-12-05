package com.shoplify.shoplify.models.dao;

import com.shoplify.shoplify.models.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductDAO extends ListCrudRepository<Product, Long> {
}
