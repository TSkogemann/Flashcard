package com.example.thomasskogemann.flashcard.data.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Thomas Skogemann on 30-09-2016.
 */
public class DeckProgress implements Serializable {
    private int id;
    private String title;
    // Integer values: 0 = never done, 1= correct, 2= incorrect
    private ArrayList<Integer> progress = new ArrayList<>();

    public DeckProgress(Deck deck) {
        this.id = deck.getId();
        this.title = deck.getTitle();

        for (Flashcard card :deck.getFlashcards()) {
            progress.add(0);
        }
    }

    public void setProgressCorrect(int index) {
        progress.set(index,1);
    }

    public void setProgressIncorrect(int index){
        progress.set(index,2);
    }
}
