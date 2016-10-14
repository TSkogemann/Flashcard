package com.example.thomasskogemann.flashcard.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

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

    public static User generateDummyUser(String id, String name, int numberOfFlashcards, int numberOfQuestions) {
        ArrayList<Flashcard> cards = new ArrayList<>();
        for (int i = 0; i < numberOfFlashcards; i++) {
            cards.add(Flashcard.createDummyFlashCard(numberOfQuestions));
        }
        User dummyUser = new User(id, name, cards);
        return dummyUser;
    }


}
