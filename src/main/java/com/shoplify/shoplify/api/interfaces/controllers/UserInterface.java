package com.shoplify.shoplify.api.interfaces.controllers;

import com.shoplify.shoplify.models.Address;
import com.shoplify.shoplify.models.LocalUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterface {
    ResponseEntity<List<Address>> getUserAddresses(LocalUser user, Long userId);

    ResponseEntity<Address> putAddress(LocalUser user, Long userId, Address address);

    ResponseEntity<Address> updateAddress(LocalUser user, Long userId, Long addressId, Address address);
}
