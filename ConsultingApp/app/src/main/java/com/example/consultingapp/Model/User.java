package com.example.consultingapp.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    private String name, email , id,password,region,profession,tel,address;
    private boolean subscriber;
    @SerializedName("accessToken")
    private String token;

    public String getRegion() {
        return region;
    }

    public String getProfession() {
        return profession;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public boolean isSubscriber() {
        return subscriber;
    }

    public void setSubscriber(boolean subscriber) {
        this.subscriber = subscriber;
    }

    public User(String name, String email, String id, String password, String region, String profession, String tel, String address, boolean subscriber) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.password = password;
        this.region = region;
        this.profession = profession;
        this.tel = tel;
        this.address = address;
        this.subscriber = subscriber;
    }

    public User(String name, String email, String password, String region, String profession, String tel, String address, boolean subscriber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.region = region;
        this.profession = profession;
        this.tel = tel;
        this.address = address;
        this.subscriber = subscriber;
    }

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String token) {
        this.token = token;
    }

    public User(String id, String name, String email, String password, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
        this.password = password;
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

    public String getToken() {
        return token;
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

    public void setToken(String token) {
        this.token = token;
    }
}
