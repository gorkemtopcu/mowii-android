package com.mowii.mowii.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class User {
    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private int collectionCount;

    @DBRef
    private List<MovieCollection> collections = new ArrayList<>();

    @DBRef
    private List<MovieCollection> likedCollections = new ArrayList<>();


    public User() {
    }

    public User(String name, String email, String password, List<MovieCollection> collections, List<MovieCollection> likedCollections) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.collections = collections;
        this.likedCollections = likedCollections;
    }

    public void appendLikeCollection(MovieCollection movieCollection) {
        likedCollections.add(movieCollection);
    }
    public void appendMyCollection(MovieCollection movieCollection) {
        collections.add(movieCollection);
    }

    public void removeLikeCollection(MovieCollection movieCollection) {
        likedCollections.remove(movieCollection);
    }
}
