package com.sabbih.demo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

@Data
@Table
public class Category {
    @PrimaryKey
    UUID id;
    String name;

    public Category(String name) {
        this.id = UUID.nameUUIDFromBytes((name+"CategoryDiyanu").getBytes());
        this.name = name;
    }
}
