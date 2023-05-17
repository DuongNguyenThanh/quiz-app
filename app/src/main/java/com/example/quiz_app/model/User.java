package com.example.quiz_app.model;

import java.sql.Timestamp;

public class User extends BaseModel {

    private String name;
    private Timestamp dob;
    private String accountId;

    public User() {
    }

    public User(String name, Timestamp dob, String accountId) {
        this.name = name;
        this.dob = dob;
        this.accountId = accountId;
    }

    public User(Integer id, String name, Timestamp dob, String accountId) {
        super(id);
        this.name = name;
        this.dob = dob;
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDob() {
        return dob;
    }

    public void setDob(Timestamp dob) {
        this.dob = dob;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
