package com.example.thomasskogemann.flashcard;

import com.example.thomasskogemann.flashcard.data.model.Deck;
import com.example.thomasskogemann.flashcard.data.model.Flashcard;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void dummyData() throws Exception {
        MainActivity ma = new MainActivity();

        Deck deck = new Deck(0,"test",new ArrayList<Flashcard>());
        deck = ma.generateDummyDeck(10);
        System.out.println("Deck Title: " + deck.getTitle());
        System.out.println("Deck id: " + deck.getId());
        //System.out.println(deck.getFlashcards().get(0).toString());
        System.out.println("flashcard index 0:");
        System.out.println("Flashcard questions 1 " + deck.getFlashcards().get(0).getAnswers().get(0));
        System.out.println("Flashcard questions 2 " + deck.getFlashcards().get(0).getAnswers().get(1));
        System.out.println("Flashcard questions 3 " + deck.getFlashcards().get(0).getAnswers().get(2));
        System.out.println("Flashcard questions 4 " + deck.getFlashcards().get(0).getAnswers().get(3));
        System.out.println("flashcard index 9:");
        System.out.println("Flashcard questions 1 " + deck.getFlashcards().get(9).getAnswers().get(0));
        System.out.println("Flashcard questions 2 " + deck.getFlashcards().get(9).getAnswers().get(1));
        System.out.println("Flashcard questions 3 " + deck.getFlashcards().get(9).getAnswers().get(2));
        System.out.println("Flashcard questions 4 " + deck.getFlashcards().get(9).getAnswers().get(3));
        //Log.i("ExampleUnitTest", deck.toString());
        assertEquals(4, 2 + 3);
        //Test should always fail untill improved.
    }
}