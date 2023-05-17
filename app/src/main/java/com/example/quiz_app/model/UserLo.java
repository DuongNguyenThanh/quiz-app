package com.example.quiz_app.model;

public class UserLo extends BaseModel {

    private Integer currentExp, loId, userId;

    public UserLo() {
    }

    public UserLo(Integer currentExp, Integer loId, Integer userId) {
        this.currentExp = currentExp;
        this.loId = loId;
        this.userId = userId;
    }

    public UserLo(Integer id, Integer currentExp, Integer loId, Integer userId) {
        super(id);
        this.currentExp = currentExp;
        this.loId = loId;
        this.userId = userId;
    }

    public Integer getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(Integer currentExp) {
        this.currentExp = currentExp;
    }

    public Integer getLoId() {
        return loId;
    }

    public void setLoId(Integer loId) {
        this.loId = loId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
