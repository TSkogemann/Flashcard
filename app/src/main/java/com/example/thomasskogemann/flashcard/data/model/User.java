package com.example.thomasskogemann.flashcard.data.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Thomas Skogemann on 30-09-2016.
 */
public class User implements Serializable {

    private int id;
    private String name;
    private ArrayList<Deck> deckList = new ArrayList<>();
    private ArrayList<DeckProgress> deckProgressList = new ArrayList<>();
    private int currentDecck;

    public User(int id, String name, Deck deck) {
        this.id = id;
        this.name = name;
        deckList.add(deck);
        deckProgressList.add(new DeckProgress(deck));
    }

    public String getName() {
        return name;
    }

    public ArrayList<DeckProgress> getDeckProgressList() {
        return deckProgressList;
    }

    public int getCurrentDecck() {
        return currentDecck;
    }

    public void setCurrentDecck(int currentDecck) {
        this.currentDecck = currentDecck;
    }

    public void SetCardCorrect(int deckIndex, int cardIndex) {
    deckProgressList.get(deckIndex).setProgressCorrect(cardIndex);
    }

    public void SetCardIncorrect(int deckIndex, int cardIndex) {
        deckProgressList.get(deckIndex).setProgressIncorrect(cardIndex);
    }
}
