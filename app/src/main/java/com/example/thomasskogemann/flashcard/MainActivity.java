package com.example.thomasskogemann.flashcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.thomasskogemann.flashcard.data.model.Deck;
import com.example.thomasskogemann.flashcard.data.model.Flashcard;
import com.example.thomasskogemann.flashcard.data.model.User;

import java.util.ArrayList;
import java.util.Random;

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
        // generating dummy info
        Deck dummyDeck = generateDummyDeck(10);
        User dummyUser =  generateDummyUser(1,"dummyUser",dummyDeck);
        dummyUser.addDeck(generateDummyDeck(8));
        dummyUser.addDeck(generateDummyDeck(7));
        dummyUser.addDeck(generateDummyDeck(4));
        dummyUser.addDeck(generateDummyDeck(1));
        // put in intent
        Intent intent = new Intent(this, ShowCardActivity.class);
        intent.putExtra("dummyUser", dummyUser);
        //change activity
        startActivity(intent);
    }

    // Methods to create dummyDeck, flashcards and users
    public Deck generateDummyDeck(int numberOfFlashcards) {
        ArrayList<Flashcard> flashCards = new ArrayList<>();
        for (int i = 0; i < numberOfFlashcards; i++) {
            Flashcard flashcard = createDummyFlashCard(i, 4);
            flashCards.add(flashcard);
        }

        Deck deck = new Deck(1, "test", flashCards);



        return deck;
    }

    private Flashcard createDummyFlashCard(int idNumber, int numberOfQuestions) {
        int id = idNumber;
        String question = "Her står spørgsmålet! Lalalal bla bla " + getRandomString();
        ArrayList<String> questions = new ArrayList<>();
        questions.add("Correct Answer");
        for (int i = 1; i < numberOfQuestions; i++) {
            questions.add(getRandomString() + " " + i);
        }

        Flashcard flashcard = new Flashcard(questions,0, id, question);
        return flashcard;
    }

    private User generateDummyUser(int id,String name,Deck deck ){
       User dummyUser = new User(id, name,deck);
        return dummyUser;
    }

    private String getRandomString(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Faxe kondi");
        strings.add("Coca Cola");
        strings.add("Pepsi");
        strings.add("Fanta");
        strings.add("7UP");
        strings.add("Pepsi max");
        strings.add("Cola Light");
        Random random = new Random();
        return strings.get(random.nextInt(strings.size()));
    }
}
