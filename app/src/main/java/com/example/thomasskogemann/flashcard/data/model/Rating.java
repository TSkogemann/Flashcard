package com.example.thomasskogemann.flashcard.data.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Thomas Skogemann on 10-10-2016.
 */
public class Rating implements Serializable {
    String _id;
    int rating;
    int time;
    Boolean correct;
    Date solveDate;

    public Rating() {

    }

    public Boolean getCorrect() {
        return correct;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Date getSolveDate() {
        return solveDate;
    }

    public void setSolveDate(Date solveDate) {
        this.solveDate = solveDate;
    }
}
