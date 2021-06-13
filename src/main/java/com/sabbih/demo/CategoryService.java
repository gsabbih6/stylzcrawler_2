package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category create(Category category) {
//        Category sf = ;
//        if (categoryRepository.existsByName(category.getName())) return category;
        return categoryRepository.save(category);
    }

    public Store update(Store store) {
        return null;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> get(UUID uuid) {
        return categoryRepository.findById(uuid);
    }

    public void delete(UUID uuid) {
        categoryRepository.deleteById(uuid);
    }

}
