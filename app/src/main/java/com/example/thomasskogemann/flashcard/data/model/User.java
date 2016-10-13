package com.example.thomasskogemann.flashcard.data.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Thomas Skogemann on 30-09-2016.
 */
public class User implements Serializable {

    private String id;
    private String name;
    private ArrayList<Flashcard> flashcards = new ArrayList<>();

    public User(String id, String name, ArrayList<Flashcard> flashcards) {
        this.id = id;
        this.name = name;
        this.flashcards = flashcards;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }
}
