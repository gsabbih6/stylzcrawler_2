package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
// hangle http requests
@RestController
@RequestMapping("api/purchase")
public class PurchaseController {

    @Autowired PurchaseService service;
    @GetMapping("/generate")
    public ResponseEntity<Purchase> generate(@RequestParam String productid) {
       Purchase p= service.create(productid);

       return ResponseEntity.ok(p);

    }
}
