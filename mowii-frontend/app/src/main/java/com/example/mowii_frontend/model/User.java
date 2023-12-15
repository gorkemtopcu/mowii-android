package com.example.mowii_frontend.model;

public class User {
    private String password;
    private String id;
    private String name;
    private String email;
    private int collectionCount;
    private int totalLikes;

    public User(){ }

    public User(String name, String email, String password){
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(String password, String id, String name, String email, int collectionCount, int totalLikes) {
        this.password = password;
        this.id = id;
        this.name = name;
        this.email = email;
        this.collectionCount = collectionCount;
        this.totalLikes = totalLikes;
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

    public int getCollectionCount() {
        return collectionCount;
    }

    public int getTotalLikes() {
        return totalLikes;
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

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }
}
