package com.sabbih.demo;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ProductRepository extends CassandraRepository<Product, UUID> {

//    boolean existsByPayment_url(String payment_url);
}
