package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/auth")
@RestController
//@CrossOrigin
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    ProductService productService;

    @GetMapping("/user")
    public ResponseEntity<User> createUser(@RequestParam String idToken, @RequestParam String client)
            throws GeneralSecurityException, IOException {
        User user = service.save(idToken, client);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User users = service.update(user);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/user/wishlist")
    public ResponseEntity<List<Product>> wishlistUser(@RequestBody User user) {
//        User users = service.update(user);
        List<Product> products = new ArrayList<>();
        if (user.getWishlist() != null) {

            for (String uuid : user.getWishlist()
            ) {
                products.add(productService.get(UUID.fromString(uuid)).get());
            }

        }
        return ResponseEntity.ok(products);
    }
}
