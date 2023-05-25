package com.example.quiz_app.model;

public class UserLo extends BaseModel {

    private Integer currentExp, loId, userId;
    private String status;

    public UserLo() {
    }

    public UserLo(Integer currentExp, String status, Integer loId, Integer userId) {
        this.currentExp = currentExp;
        this.loId = loId;
        this.userId = userId;
        this.status = status;
    }

    public UserLo(Integer id, Integer currentExp, String status, Integer loId, Integer userId) {
        super(id);
        this.currentExp = currentExp;
        this.loId = loId;
        this.userId = userId;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
