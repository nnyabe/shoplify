package com.shoplify.shoplify.models.dao;

import com.shoplify.shoplify.models.LocalUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserDAO extends ListCrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByEmail(String email);
    Optional<LocalUser> findByUsername(String username);

}
