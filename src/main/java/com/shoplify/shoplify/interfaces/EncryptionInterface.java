package com.shoplify.shoplify.interfaces;

public interface EncryptionInterface {
    String encryptPassword(String password);

    boolean checkPassword(String password, String hashedPassword);
}
