package com.shoplify.shoplify.models.dao;

import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.Orders;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OderDAO extends ListCrudRepository<Orders, Long> {

    List<Orders> findByUser(LocalUser user);
}
