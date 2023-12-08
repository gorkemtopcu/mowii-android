package com.mowii.mowii.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {
    @Id
    private String id;

    private String name;
    private String email;
    private String password;

    @DBRef
    private List<MovieCollection> collections;

    public User() {
    }

    public User(String name, String email, String password, List<MovieCollection> collections) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.collections = collections;
    }
}
