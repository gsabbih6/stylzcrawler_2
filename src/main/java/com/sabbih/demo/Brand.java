package com.sabbih.demo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table
public class Brand {
    @PrimaryKey
    private UUID id;

    String name;
    String logo;

    public Brand(String name) {
        this.name = name;
    }
}
