package com.mowii.mowii.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
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

    private List<String> collectionsId = new ArrayList<>();

    private List<String> likedCollectionsId = new ArrayList<>();


    public User() { }

    public User(String name, String email, String password, List<String> collections, List<String> likedCollections) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.collectionsId = collections;
        this.likedCollectionsId = likedCollections;
    }

    public void appendLikeCollection(String movieCollection) {
        likedCollectionsId.add(movieCollection);
    }
    public void appendMyCollection(String movieCollection) {
        collectionsId.add(movieCollection);
    }
    public void removeLikeCollection(String movieCollection) {
        likedCollectionsId.remove(movieCollection);
    }
}
