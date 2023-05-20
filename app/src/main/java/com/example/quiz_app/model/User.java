package com.example.quiz_app.model;

import java.util.Date;

public class User extends BaseModel {

    private String name;
    private Date dob;
    private String accountId;

    public User() {
    }

    public User(String name, Date dob, String accountId) {
        this.name = name;
        this.dob = dob;
        this.accountId = accountId;
    }

    public User(Integer id, String name, Date dob, String accountId) {
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
