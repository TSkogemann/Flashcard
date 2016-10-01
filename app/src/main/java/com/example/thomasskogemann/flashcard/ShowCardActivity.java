package com.example.thomasskogemann.flashcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.thomasskogemann.flashcard.adapters.Itemclick;
import com.example.thomasskogemann.flashcard.adapters.QuestionRecycleAdapter;
import com.example.thomasskogemann.flashcard.adapters.RecycleAdapter;
import com.example.thomasskogemann.flashcard.data.model.Deck;
import com.example.thomasskogemann.flashcard.data.model.Flashcard;
import com.example.thomasskogemann.flashcard.data.model.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Thomas Skogemann on 27-09-2016.
 */
public class ShowCardActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView mQuestionView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mQuestionAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mQuestionManager;
    private Deck currentDeck = new Deck(-1, "temp", new ArrayList<Flashcard>());
    private Flashcard myFlashCardData = new Flashcard(new ArrayList<String>(),0, 0, "tempQuestion");
    private User currentUser;
    private int currentCard;

    //Layout
    @BindView(R.id.show_card_layout)
    RelativeLayout intro_layout;

    //TextViews
    @BindView(R.id.show_user_info)
    TextView show_content_view;

    //RecyclerViews
    @BindView(R.id.show_content_recycler_view)
    RecyclerView recycler_view;

    @BindView(R.id.show_question_recycler_view)
    RecyclerView question_view;

    //buttons
    @BindView(R.id.start_flash_card)
    Button start_flash_card_btn;

    @BindView(R.id.answer)
    Button answer_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcard);
        ButterKnife.bind(this);

        //recyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.show_content_recycler_view);
        mQuestionView = (RecyclerView) findViewById(R.id.show_question_recycler_view);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mQuestionManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mQuestionView.setLayoutManager(mQuestionManager);

        //Setting user
        currentUser = (User) getIntent().getSerializableExtra("dummyUser");
        show_content_view.setText("" + currentUser.getName());


        //Setting currentDeck
        if (getIntent() != null) {
            //currentDeck = (Deck) getIntent().getSerializableExtra("dummyDeck");

            currentUser.setCurrentDeck(0);
            currentDeck = currentUser.getDeckList().get(currentUser.getCurrentDeck());
            // specify an adapter (see also next example)
            mAdapter = new RecycleAdapter(currentDeck, new Itemclick() {
                @Override
                public void onItemClicked(String data) {
                    Log.i(ShowCardActivity.class.getSimpleName(), data);
                }
            });
            currentCard =0;
            myFlashCardData = currentDeck.getFlashcards().get(currentCard);
            mQuestionAdapter = new QuestionRecycleAdapter(myFlashCardData, new Itemclick() {
                @Override
                public void onItemClicked(String data) {
                    Log.i(ShowCardActivity.class.getSimpleName(), data);
                    answer_btn.setText(data);
                }
            });

            mRecyclerView.setAdapter(mAdapter);
            mQuestionView.setAdapter(mQuestionAdapter);
        }
    }

    @OnClick(R.id.start_flash_card)
    public void startFlashCardClicked() {
        mRecyclerView.setVisibility(View.GONE);
        mQuestionView.setVisibility(View.VISIBLE);
        answer_btn.setVisibility(View.VISIBLE);
        start_flash_card_btn.setVisibility(View.GONE);
    }

    @OnClick(R.id.answer)
    public void answer_btn_clicked() {
        if (myFlashCardData.getAnswers().get(0).equals(answer_btn.getText())) {
            // Correct answer
            currentUser.SetCardCorrect(currentUser.getCurrentDeck(), currentCard);
            nextCard();

        } else {
            // Incorrect answer
            currentUser.SetCardIncorrect(currentUser.getCurrentDeck(),currentCard);
            nextCard();

        }
    }

    public void nextCard(){
        currentCard++;
        if (currentCard >= currentDeck.getFlashcards().size() ){
            deckDone();
        }
        answer_btn.setText("Answer");
        myFlashCardData = currentDeck.getFlashcards().get(currentCard);
        //TODO change view NOT WORKING
        mQuestionAdapter.notifyDataSetChanged();
        mAdapter.notifyDataSetChanged();
        mQuestionView.invalidate();
        mRecyclerView.invalidate();
        // should be one of the above 4
    }

    private void deckDone() {
        // not made
        currentUser.nextDeck();
        currentCard=0;
        currentUser.setCurrentDeck(currentUser.getCurrentDeck());
      //  mangler noget notify
    }
}
