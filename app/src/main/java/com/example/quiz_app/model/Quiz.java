package com.example.quiz_app.model;

import java.util.List;

public class Quiz extends BaseModel {

    private String quizQuestion;
    private Integer exp, loId;
    private List<Answer> answers;

    public Quiz() {
    }

    public Quiz(String quizQuestion, Integer exp, List<Answer> answers) {
        this.quizQuestion = quizQuestion;
        this.exp = exp;
        this.answers = answers;
    }

    public Quiz(String quizQuestion, Integer exp, Integer loId, List<Answer> answers) {
        this.quizQuestion = quizQuestion;
        this.exp = exp;
        this.loId = loId;
        this.answers = answers;
    }

    public Quiz(Integer id, String quizQuestion, Integer exp, Integer loId, List<Answer> answers) {
        super(id);
        this.quizQuestion = quizQuestion;
        this.exp = exp;
        this.loId = loId;
        this.answers = answers;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(String quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getLoId() {
        return loId;
    }

    public void setLoId(Integer loId) {
        this.loId = loId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
