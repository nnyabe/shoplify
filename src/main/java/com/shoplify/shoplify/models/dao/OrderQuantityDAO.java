package com.shoplify.shoplify.models.dao;

import com.shoplify.shoplify.models.OrderQuantity;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderQuantityDAO extends ListCrudRepository<OrderQuantity, Long> {
}
