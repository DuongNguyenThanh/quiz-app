package com.example.quiz_app.model;

import java.io.Serializable;

public class BaseModel {

    private Integer id;

    public BaseModel() {
    }

    public BaseModel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
