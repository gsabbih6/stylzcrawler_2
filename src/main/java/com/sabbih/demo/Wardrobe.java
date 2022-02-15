package com.sabbih.demo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Table
@Data
public class Wardrobe {
    @PrimaryKey
    UUID id;
    UUID userid;
    String name;
    String description;
    Set<String> productids;
}
