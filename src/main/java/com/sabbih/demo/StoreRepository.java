package com.sabbih.demo;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface StoreRepository extends CassandraRepository<Store, UUID> {

    boolean existsByUrl(String url);
}
