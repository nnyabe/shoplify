package com.shoplify.shoplify.models.dao;

import com.shoplify.shoplify.models.Address;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface AddressDAO extends ListCrudRepository<Address, Long> {

    List<Address> findByUserId(Long userId);
}
