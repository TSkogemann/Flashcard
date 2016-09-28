package com.example.thomasskogemann.flashcard.data.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Thomas Skogemann on 27-09-2016.
 */
public class Flashcard implements Serializable {
    //Bør kort ikke have en titel, når de skal vises i oversigt?
    ArrayList<String> answers = new ArrayList<>();
    int id;
    String question;

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public int getId() {
        return id;
    }

    public String getQuestion(){return question;}

    public Flashcard(ArrayList<String> answers, int id, String question) {
        this.answers = answers;
        this.id = id;
        this.question = question;
    }
}
