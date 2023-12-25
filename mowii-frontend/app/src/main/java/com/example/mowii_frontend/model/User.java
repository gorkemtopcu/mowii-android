package com.example.mowii_frontend.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private String password;
    private String id;
    private String name;
    private String email;
    private List<MovieCollection> collections;
    private List<MovieCollection> likedCollections;
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

    public User(String password, String id, String name, String email, List<MovieCollection> collections, List<MovieCollection> likedCollections) {
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

    public List<MovieCollection> getCollections() {return collections;}

    public void setCollections(List<MovieCollection> collections) {this.collections = collections;}

    public List<MovieCollection> getLikedCollections() {return likedCollections;}

    public void setLikedCollections(List<MovieCollection> likedCollections) {this.likedCollections = likedCollections;}
    public void addLikedCollection(MovieCollection likedCollection) {
        if (this.likedCollectionIdSet.add(likedCollection.getId())) {
            this.likedCollections.add(likedCollection);
        }
    }
    public void removeLikedCollection(MovieCollection likedCollection) {
        if (this.likedCollectionIdSet.remove(likedCollection.getId())){
            this.likedCollections.remove(likedCollection);
        }
    }

    public int getCollectionCount(){return collections.size();}
    public int getTotalLikes() {
        int totalLikes = 0;
        for (MovieCollection col:collections) {
            totalLikes += col.getLike();
        }
        return totalLikes;
    }

    public boolean checkIsCollectionLiked(MovieCollection movieCollection) {
        if(likedCollectionIdSet == null) {
            likedCollectionIdSet = new HashSet<>();
            for (MovieCollection mc: likedCollections) {likedCollectionIdSet.add(mc.getId());}
        }
        return likedCollectionIdSet.contains(movieCollection.getId());
    }
}
