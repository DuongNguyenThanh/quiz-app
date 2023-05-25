package com.example.quiz_app.model;

public class UserQuiz extends BaseModel {

    private Integer quizId, userId;

    public UserQuiz() {
    }

    public UserQuiz(Integer quizId, Integer userId) {
        this.quizId = quizId;
        this.userId = userId;
    }

    public UserQuiz(Integer id, Integer quizId, Integer userId) {
        super(id);
        this.quizId = quizId;
        this.userId = userId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
