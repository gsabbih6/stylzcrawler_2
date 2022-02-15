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
    private UUID id;
    UUID parent_id;
    String name;
//    int count_items;
    /* use empty constructor*/
    public Category() {
    }
    public Category(String name) {
        this.id = UUID.nameUUIDFromBytes((name).getBytes());
        this.name = name;
    }

    public Category(String name, UUID parent_id) {
        this.id = UUID.nameUUIDFromBytes((name).getBytes());
        this.name = name;
        this.parent_id = parent_id;
    }
}
