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

    public Brand save(Brand brand) {
        brand.setId(UUID.nameUUIDFromBytes(brand.getName().getBytes(StandardCharsets.UTF_8)));
        return repository.save(brand);
    }

    public List<Brand> getAll() {
        return repository.findAll();
    }
}
