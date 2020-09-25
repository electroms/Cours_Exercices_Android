package com.example.filmquizz.business;

import java.io.Serializable;

public class Question implements Serializable {

    private int id;
    private String text;
    private boolean answer;

    public Question(int id, String text, boolean answer) {
        this.id = id;
        this.text = text;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", test='" + text + '\'' +
                ", answer=" + answer +
                '}';
    }
}
