package com.sabbih.demo;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface WardrobeRepository extends CassandraRepository<Wardrobe, UUID> {
    @AllowFiltering List<Wardrobe> findByUserid(UUID userid);
}
