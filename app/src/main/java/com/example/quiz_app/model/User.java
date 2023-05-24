package com.example.quiz_app.model;

import java.io.Serializable;

public class User extends BaseModel {

    private String name, dob, accountId;
    private Integer exp, imageId;

    public User() {
    }

    public User(String name, String dob, String accountId, Integer exp, Integer imageId) {
        this.name = name;
        this.dob = dob;
        this.accountId = accountId;
        this.exp = exp;
        this.imageId = imageId;
    }

    public User(Integer id, String name, String dob, String accountId, Integer exp, Integer imageId) {
        super(id);
        this.name = name;
        this.dob = dob;
        this.accountId = accountId;
        this.exp = exp;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}
