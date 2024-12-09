package com.shoplify.shoplify.api.user;


import com.shoplify.shoplify.models.Address;
import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.dao.AddressDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
private final AddressDAO addressDAO;

public UserController(AddressDAO addressDAO) {
    this.addressDAO = addressDAO;
}

    @GetMapping("/{userId}/")
    public ResponseEntity<List<Address>> getUserAddresses(@AuthenticationPrincipal LocalUser user,  @PathVariable Long userId) {
    if(!userHasPermission(user, userId)) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
        return ResponseEntity.ok(addressDAO.findByUserId(userId));
    }

    @PutMapping("/{userId}/address")
    public ResponseEntity putAddress(@AuthenticationPrincipal LocalUser user, @PathVariable Long userId, @RequestBody Address address) {
    if(!userHasPermission(user, userId)) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    address.setId(null);
    LocalUser refUser = new LocalUser();
    refUser.setId(userId);
    address.setUser(refUser);
    return ResponseEntity.ok(addressDAO.save(address));
    }

    @PatchMapping("/{userId}/address/{addressId}")
    public ResponseEntity<Address> updateAddress(@AuthenticationPrincipal LocalUser user , @PathVariable Long userId, @PathVariable Long addressId, @RequestBody Address address) {
        if(!userHasPermission(user, userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if(address.getId() ==  addressId) {
            Optional<Address> existingAddress = addressDAO.findById(addressId);
            if(existingAddress.isPresent()) {
                LocalUser originalUser = existingAddress.get().getUser();
                if(originalUser.getId() == user.getId()) {
                    address.setUser(originalUser);
                    return ResponseEntity.ok(addressDAO.save(address));
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }
    private boolean userHasPermission(LocalUser localUser, Long userId) {
    return localUser.getId() == userId;
    }


}
