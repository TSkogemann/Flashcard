package com.example.thomasskogemann.flashcard.data.model;

import java.io.Serializable;

/**
 * Created by Thomas Skogemann on 10-10-2016.
 */
public class Answer implements Serializable {
    String answer;
    String _id;
    Boolean isCorrect;

    public Answer(String answer, String _id, Boolean isCorrect) {
        this.answer = answer;
        this._id = _id;
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
