package com.sabbih.demo;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface DiscoverRepository extends CassandraRepository<Discover, UUID> {
}
