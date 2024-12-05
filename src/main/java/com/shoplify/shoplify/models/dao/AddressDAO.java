package com.shoplify.shoplify.models.dao;

import com.shoplify.shoplify.models.Address;
import org.springframework.data.repository.ListCrudRepository;

public interface AddressDAO extends ListCrudRepository<Address, Long> {
}
