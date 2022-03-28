package com.sabbih.demo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table
public class Deal {
    @PrimaryKey
    private UUID id;
    UUID productId;
    String discountCode;
    LocalDateTime expiryDate;
    String info;

}
