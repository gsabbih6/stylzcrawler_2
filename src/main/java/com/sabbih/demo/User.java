package com.sabbih.demo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.security.Provider;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Table
public class User {
    @PrimaryKey
    UUID id;
    String firstname;
    String lastname;
    String email;
    String name;
    String localey;
    String photourl;
    String authtoken;
    boolean verifiedemail;
    Set<String> wishlist;
    Set<String> wardrobeIds;
}
