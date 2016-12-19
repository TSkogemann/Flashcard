package com.example.thomasskogemann.flashcard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import com.example.thomasskogemann.flashcard.data.model.Answer
import com.example.thomasskogemann.flashcard.data.model.Flashcard
import com.example.thomasskogemann.flashcard.data.model.GetCardTask
import com.example.thomasskogemann.flashcard.data.model.Rating
import com.example.thomasskogemann.flashcard.data.model.User
import com.example.thomasskogemann.flashcard.data.model.Utils

import java.util.ArrayList
import java.util.Random

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity() {

    //Buttons
    @BindView(R.id.start_btn)
    internal var StartBtn: Button? = null


    //onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.start_btn)
    fun start_clicked() {
        // generating dummy info
        val dummyUser = User.generateDummyUser(Utils.randomString + "ID", "dummyUser", 5, 4)

        // intent
        val intent = Intent(this@MainActivity, ShowCardActivity::class.java)
        intent.putExtra("dummyUser", dummyUser)
        //change activity
        startActivity(intent)

        // TODO HER code to get objects from the backend!

    }}
/*
        ------------------------------------------------------------------------------------------------
        new GetCardTask(new GetCardTask.ApiListener() {
            @Override
            public void onSucces(Flashcard tempFlashcard) {
                // Safety check
                if (MainActivity.this == null) {
                    return;
                }
                Intent intent = new Intent(MainActivity.this, ShowCardActivity.class);
                intent.putExtra("dummyUser", dummyUser);
                //change activity
                startActivity(intent);
            }

            @Override
            public void onError() {
                Intent intent = new Intent(MainActivity.this, ShowCardActivity.class);
                intent.putExtra("dummyUser", dummyUser);
                //change activity
                startActivity(intent);
            }
        }).execute();
---------------------------------------------------------------------------------------------------------------
    }

 -------------------------- flyttet til objecter og Utils ------------------------------
    private Flashcard createDummyFlashCard(int numberOfQuestions) {
        String category = getRandomString() + "Category";
        String id = getRandomString() + "ID";
        String question = "Her står spørgsmålet! Lalalal bla bla " + getRandomString();
        ArrayList<Answer> answers = new ArrayList<>();
        Rating rating = new Rating();

        for (int i = 0; i < numberOfQuestions; i++) {
            Answer answer = new Answer(getRandomString(), getRandomString(), getRandomBoolean(i));
            answers.add(answer);


        }

        Flashcard flashcard = new Flashcard(category, answers, id, rating, question);
        return flashcard;
    }

    private User generateDummyUser(String id, String name, int numberOfFlashcards, int numberOfQuestions) {
        ArrayList<Flashcard> cards = new ArrayList<>();
        for (int i = 0; i < numberOfFlashcards; i++) {
            cards.add(createDummyFlashCard(numberOfQuestions));
        }
        User dummyUser = new User(id, name, cards);
        return dummyUser;
    }

    private String getRandomString() {
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

    private Boolean getRandomBoolean(int i) {
        if (i == 0) {
            return true;
        } else return false;

        */
/*  can be used to get a random boolean
        Random random = new Random();
        int rnd1 = random.nextInt(7);
        int rnd2 = random.nextInt(7);
        if (rnd1 >= rnd2) {
            return false;
        } else return true;

    }
-----------------------------------------------------------------------------------------------------------------------
*/

