package com.sabbih.demo;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.UUID;

@Service
public class UserService {
    private static String CLIENT_PROPERTY_KEY
            = "spring.security.oauth2.client.registration.";
    @Autowired
    UserRepository repository;
    @Autowired
    Environment env;

    public User update(User user) {
        return repository.save(user);
    }

    public User save(String idTokenString, String client) throws GeneralSecurityException, IOException {
        String clientId = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-id");
        if (clientId == null) {
            return null;
        }

        String clientSecret = env.getProperty(
                CLIENT_PROPERTY_KEY + client.toLowerCase() + ".client-secret");

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(clientId))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

// (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify(idTokenString);
        User user = new User();
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = (String) payload.get("email");
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            user.setId(UUID.nameUUIDFromBytes((email).getBytes()));

            user.setEmail(email);
            user.setFirstname(givenName);
            user.setLastname(familyName);
            user.setName(name);
            user.setPhotourl(pictureUrl);
            user.setLocaley(locale);
            user.setVerifiedemail(emailVerified);
            user.setWishlist(new LinkedHashSet<>());
            user.setWardrobeIds(new LinkedHashSet<>());
            // Use or store profile information
            // ...
            System.out.println(email);

        } else {
            System.out.println("Invalid ID token.");
        }

        if (repository.existsById(user.id)) return repository.findById(user.id).get();

        return repository.save(user);
    }

    public User findById(UUID fromString) {
        return repository.findById(fromString).get();
    }
}
