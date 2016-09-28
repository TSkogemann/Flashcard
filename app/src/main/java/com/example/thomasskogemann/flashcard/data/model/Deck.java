package com.example.thomasskogemann.flashcard.data.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Thomas Skogemann on 27-09-2016.
 */
public class Deck implements Serializable {
    int id;
    String title;
    ArrayList<Flashcard> flashcards;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }

    public Deck(int id, String title, ArrayList<Flashcard> flashcards) {
        this.id = id;
        this.title = title;
        this.flashcards = flashcards;
    }
}
