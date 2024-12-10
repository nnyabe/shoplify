package com.shoplify.shoplify.models.dao;

import com.shoplify.shoplify.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductDAO extends ListCrudRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);

}
