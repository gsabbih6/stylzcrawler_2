package com.sabbih.demo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Data
@Table
public class Brand {
    @PrimaryKey
    private UUID id;

    String name;
    String logo;
    HashSet<String> product_id;

    public Brand(String name) {
        this.name = name;
        this.id = UUID.nameUUIDFromBytes(name.getBytes(StandardCharsets.UTF_8));
    }

    public void addProduct(String id) {
        if (product_id == null) product_id = new HashSet<>();

        product_id.add(id);
    }
}
