package com.example.thomasskogemann.flashcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.thomasskogemann.flashcard.data.model.Deck;
import com.example.thomasskogemann.flashcard.data.model.Flashcard;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //Buttons
    @BindView(R.id.start_btn)
    Button StartBtn;


    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_btn)
    public void start_clicked() {
        // put in intent
        Intent intent = new Intent(this, ShowCardActivity.class);
        intent.putExtra("dummyDeck", generateDummyDeck(10));
        //change activity
        startActivity(intent);
    }

    public Deck generateDummyDeck(int numberOfFlashcards) {
        ArrayList<Flashcard> flashCards = new ArrayList<>();
        for (int i = 0; i < numberOfFlashcards; i++) {
            Flashcard flashcard = createFlashCard(i, 4);
            flashCards.add(flashcard);
        }

        Deck deck = new Deck(1, "test", flashCards);



        return deck;
    }

    private Flashcard createFlashCard(int idNumber, int numberOfQuestions) {
        int id = idNumber;
        String question = "Her står spørgsmålet! Lalalal bla bla";
        ArrayList<String> questions = new ArrayList<>();
        questions.add("Correct Answer");
        for (int i = 1; i < numberOfQuestions; i++) {
            questions.add("Wrong Answer " + i);
        }

        Flashcard flashcard = new Flashcard(questions, id, question);
        return flashcard;
    }
}
