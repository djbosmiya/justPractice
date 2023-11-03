package com.example.hrminiapp.models;
import java.io.Serializable;

public class User implements Serializable {
    public String userId;
    public String emailId;

    public User(String userId, String emailId){
        this.userId = userId;
        this.emailId = emailId;
    }
}
