package com.shoplify.shoplify.service;

import com.shoplify.shoplify.api.model.Login;
import com.shoplify.shoplify.api.model.Registration;
import com.shoplify.shoplify.exception.EmailFailureException;
import com.shoplify.shoplify.exception.UserAlreadyExistsException;
import com.shoplify.shoplify.exception.UserNotVerified;
import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.VerificationToken;
import com.shoplify.shoplify.models.dao.UserDAO;
import com.shoplify.shoplify.models.dao.VerificationTokenDAO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserSerivce {

    private final UserDAO userDAO;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;
    private final EmailService emailService;
    private final VerificationTokenDAO verificationTokenDAO;

    public UserSerivce(UserDAO userDAO,VerificationTokenDAO verificationTokenDAO,  EncryptionService encryptionService, JWTService jwtService, EmailService emailService) {

        this.encryptionService = encryptionService;
        this.userDAO = userDAO;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.verificationTokenDAO = verificationTokenDAO;
    }

    public void registerUser(Registration registration) throws UserAlreadyExistsException, EmailFailureException {
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

        VerificationToken verificationToken = createVerificationToken(user);
        emailService.sendVerificationEmail(verificationToken);
        verificationTokenDAO.save(verificationToken);
        userDAO.save(user);
    }

    private VerificationToken createVerificationToken(LocalUser user){
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationJWT(user));
        verificationToken.setCreatedTimestamp((new Timestamp(System.currentTimeMillis())));
        verificationToken.setUser(user);
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }

    public String logIn(Login login) throws UserNotVerified, EmailFailureException {
        Optional<LocalUser> opUser = userDAO.findByUsername(login.getUsername());
            if(opUser.isPresent()) {
                LocalUser user = opUser.get();
                if(encryptionService.checkPassword(login.getPassword(), user.getPassword())) {
                    if(user.getEmailVerified()){
                        return jwtService.getToken(user);
                    }else {
                        List<VerificationToken> verificationTokens = user.getVerificationTokens();
                        boolean resend = verificationTokens.size() == 0 ||
                                verificationTokens.get(0).getCreatedTimestamp().
                                        before(new Timestamp(System.currentTimeMillis() - (1000 * 60 * 60)));
                        if(resend){
                            VerificationToken verificationToken = createVerificationToken(user);
                            verificationTokenDAO.save(verificationToken);
                            emailService.sendVerificationEmail(verificationToken);
                        }

                        throw new UserNotVerified(resend);
                    }

                }
            }

            return null;

    }

    @Transactional
    public boolean verifyUser(String token){
        Optional<VerificationToken> optToken = verificationTokenDAO.findByToken(token);
        if(optToken.isPresent()){
            VerificationToken verificationToken = optToken.get();
            LocalUser user = verificationToken.getUser();
            if(!user.getEmailVerified()){
                user.setEmailVerified(true);
                userDAO.save(user);
                verificationTokenDAO.deleteByUser(user);
                return true;
            }
        }
        return false;
    }
}
