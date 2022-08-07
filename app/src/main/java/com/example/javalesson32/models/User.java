package com.example.javalesson32.models;

import java.io.Serializable;

public class User implements Serializable {
    private String fullName;
    private String email;
    private  String phoneeNumber;
    private String userName;
    private String passwrod;

    public User(String fullName, String email, String phoneeNumber, String userName, String passwrod) {
        this.fullName = fullName;
        this.email = email;
        this.phoneeNumber = phoneeNumber;
        this.userName = userName;
        this.passwrod = passwrod;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneeNumber() {
        return phoneeNumber;
    }

    public void setPhoneeNumber(String phoneeNumber) {
        this.phoneeNumber = phoneeNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }
}
