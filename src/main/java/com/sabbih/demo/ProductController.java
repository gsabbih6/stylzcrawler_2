package com.sabbih.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// hangle http requests
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        Optional<Product> opt = service.get(UUID.fromString(id));
        if (opt.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(opt.get(), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        Product p = service.create(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> allProducts = service.getAll();
        if (allProducts == null || allProducts.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(allProducts);
        //findById(userId).map(this.customerRepository::findByUser).get();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        if (service.get(UUID.fromString(id)).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.delete(UUID.fromString(id));
        return new ResponseEntity<>("Item Deleted", HttpStatus.OK);
    }
}
