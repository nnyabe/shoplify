package com.shoplify.shoplify.models.dao;

import com.shoplify.shoplify.models.Inventory;
import org.springframework.data.repository.ListCrudRepository;

public interface InventoryDAO extends ListCrudRepository<Inventory, Long> {
}
