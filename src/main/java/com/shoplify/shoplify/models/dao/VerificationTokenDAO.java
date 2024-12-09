package com.shoplify.shoplify.models.dao;

import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface VerificationTokenDAO extends ListCrudRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);

    void deleteByUser(LocalUser user);
}
