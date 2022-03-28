package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brand")
public class BrandController {
    @Autowired
    BrandService service;

    @GetMapping("/all")
    public ResponseEntity<List<Brand>> all() {
        List<Brand> brands = service.getAll();
        return ResponseEntity.ok(brands);
    }

    @PostMapping("/create")
    public ResponseEntity<Brand>create(@RequestBody Brand brand){

        if(brand!=null){
            return ResponseEntity.ok(service.save(brand));
        }
        return (ResponseEntity<Brand>) ResponseEntity.badRequest();
    }
}
