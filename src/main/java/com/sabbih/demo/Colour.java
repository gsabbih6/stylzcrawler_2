package com.sabbih.demo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Data
@Table
public class Colour {
    @PrimaryKey
    UUID id;
    String colorcode;
    String color;
    public Colour(String color) {
        this.id = UUID.nameUUIDFromBytes(color.getBytes(StandardCharsets.UTF_8));
        this.color = color;
    }
    public Colour(String colorcode, String color) {
        this.id = UUID.nameUUIDFromBytes(color.getBytes(StandardCharsets.UTF_8));
        this.colorcode = colorcode;
        this.color = color;
    }
}
