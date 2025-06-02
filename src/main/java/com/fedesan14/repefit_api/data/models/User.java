package com.fedesan14.repefit_api.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User{
        @Column(unique = true)
        String username;
        String password;
        @Id
        UUID id = UUID.randomUUID();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
