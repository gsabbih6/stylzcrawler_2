package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
public class BrandService {

    @Autowired
    BrandRepository repository;

    public Brand save(Brand brand, UUID id) {
        brand.addProduct(id.toString());
        return repository.save(brand);
    }

    public List<Brand> getAll() {
        return repository.findAll();
    }
}
