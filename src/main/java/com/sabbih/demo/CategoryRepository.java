package com.sabbih.demo;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface CategoryRepository extends CassandraRepository<Category, UUID> {
//    boolean existsByName(String name);
}
