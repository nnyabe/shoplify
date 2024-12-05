package com.shoplify.shoplify.service;

import com.shoplify.shoplify.api.model.Login;
import com.shoplify.shoplify.api.model.Registration;
import com.shoplify.shoplify.exception.UserAlreadyExistsException;
import com.shoplify.shoplify.models.LocalUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import com.shoplify.shoplify.models.dao.UserDAO;

import java.util.Optional;

@Service
public class UserSerivce {

    private final UserDAO userDAO;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;

    public UserSerivce(UserDAO userDAO, EncryptionService encryptionService, JWTService jwtService) {

        this.encryptionService = encryptionService;
        this.userDAO = userDAO;
        this.jwtService = jwtService;
    }

    public void registerUser(Registration registration) throws UserAlreadyExistsException {
        if(userDAO.findByEmail(registration.getEmail()).isPresent()
                || userDAO.findByUsername(registration.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists!");
        }
        LocalUser user = new LocalUser();
        user.setUsername(registration.getUsername());
        user.setEmail(registration.getEmail());
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());

        user.setPassword(encryptionService.encryptPassword(registration.getPassword()));

        userDAO.save(user);
    }

    public String logIn(Login login) {
        Optional<LocalUser> opUser = userDAO.findByUsername(login.getUsername());
            if(opUser.isPresent()) {
                LocalUser user = opUser.get();
                if(encryptionService.checkPassword(login.getPassword(), user.getPassword())) {
                    return jwtService.getToken(user);
                }
            }

            return null;

    }
}
