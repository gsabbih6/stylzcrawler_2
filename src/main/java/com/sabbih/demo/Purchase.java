package com.sabbih.demo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table
public class Purchase {
    @PrimaryKey
    UUID id;
    boolean redeemed=false;
    UUID redeemedby;
    UUID productid;
}
