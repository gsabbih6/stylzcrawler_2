package com.sabbih.demo;


import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ColourRepository extends CassandraRepository<Colour, UUID> {
}
