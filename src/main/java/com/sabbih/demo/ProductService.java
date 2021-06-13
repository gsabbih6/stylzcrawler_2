package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product create(Product product) {
//        Product sf = productRepository.findByPaymentUrl(product.paymentUrl);
//        if (productRepository.existsByPaymentUrl(product.getPaymentUrl())) return product;
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> get(UUID uuid) {
        return productRepository.findById(uuid);
    }

    public void delete(UUID uuid) {
        productRepository.deleteById(uuid);
    }
}
