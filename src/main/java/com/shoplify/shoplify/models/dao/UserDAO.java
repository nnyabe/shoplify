package com.shoplify.shoplify.models.dao;

import com.shoplify.shoplify.models.LocalUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDAO extends CrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByEmail(String email);
    Optional<LocalUser> findByUsername(String username);
}
