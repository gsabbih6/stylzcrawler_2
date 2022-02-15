package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
//@CrossOrigin
@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getProduct(@PathVariable String id) {
        Optional<Category> opt = service.get(UUID.fromString(id));
        if (opt.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(opt.get(), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<Category> addProduct(@Valid @RequestBody Category product) throws IOException {
        Category p = service.create(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllProduct() {
        List<Category> allCategory = service.getAll();
        if (allCategory == null || allCategory.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(allCategory);
        //findById(userId).map(this.customerRepository::findByUser).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        if (service.get(UUID.fromString(id)).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.delete(UUID.fromString(id));
        return new ResponseEntity<>("Item Deleted", HttpStatus.OK);
    }
}
