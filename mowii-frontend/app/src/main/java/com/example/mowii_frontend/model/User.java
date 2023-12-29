package com.example.mowii_frontend.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private String password;
    private String id;
    private String name;
    private String email;
    private List<String> collections;
    private List<String> likedCollections;
    private HashSet<String> likedCollectionIdSet;

    public User(){ }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password){
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(String password, String id, String name, String email, List<String> collections, List<String> likedCollections) {
        this.password = password;
        this.id = id;
        this.name = name;
        this.email = email;
        this.collections = collections;
        this.likedCollections = likedCollections;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getCollections() {return collections;}

    public void setCollections(List<String> collections) {this.collections = collections;}

    public List<String> getLikedCollections() {return likedCollections;}

    public void setLikedCollections(List<String> likedCollections) {this.likedCollections = likedCollections;}
    public void addLikedCollection(String likedCollection) {
        if (this.likedCollectionIdSet.add(likedCollection)) {
            this.likedCollections.add(likedCollection);
        }
    }
    public void removeLikedCollection(String likedCollection) {
        if (this.likedCollectionIdSet.remove(likedCollection)){
            this.likedCollections.remove(likedCollection);
        }
    }

    public int getCollectionCount(){return collections.size();}

    public int getTotalLikes(List<MovieCollection> movieCollections) {
        int totalLikes = 0;
        for (MovieCollection mc : movieCollections) {
            totalLikes += mc.getLike();
        }
        return totalLikes;
    }

    public boolean checkIsCollectionLiked(String id) {
        if(likedCollectionIdSet == null) {
            likedCollectionIdSet = new HashSet<>(likedCollections);
        }
        return likedCollectionIdSet.contains(id);
    }
}
