package com.sabbih.demo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

@Data
@Table

public class Discover {
    @PrimaryKey
    UUID id;
    String name;
    String thumbnail;
    String short_desc;
    Set<String> product_ids;
}
