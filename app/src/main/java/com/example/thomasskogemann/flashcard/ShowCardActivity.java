package com.example.thomasskogemann.flashcard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.thomasskogemann.flashcard.adapters.Itemclick;
import com.example.thomasskogemann.flashcard.adapters.QuestionRecycleAdapter;
import com.example.thomasskogemann.flashcard.adapters.RecycleAdapter;
import com.example.thomasskogemann.flashcard.data.model.Deck;
import com.example.thomasskogemann.flashcard.data.model.DeckProgress;
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
    private Flashcard myFlashCardData = new Flashcard(new ArrayList<String>(), 0, 0, "tempQuestion");
    private User currentUser;
    private int currentCard;

    //Layout
    @BindView(R.id.show_card_layout)
    RelativeLayout intro_layout;

    //TextViews
    @BindView(R.id.show_user_info)
    TextView show_user_info;

    @BindView(R.id.show_deck_info)
    TextView show_deck_info;

    @BindView(R.id.show_card_info)
    TextView show_card_info;

    @BindView(R.id.show_progress_info)
    TextView show_progress_info;

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
        show_user_info.setText("" + currentUser.getName());


        //Setting currentDeck
        if (getIntent() != null) {
            currentUser.setCurrentDeck(0);
            currentDeck = currentUser.getDeckList().get(currentUser.getCurrentDeck());
            // setting current deck info
            show_deck_info.setText(currentDeck.getTitle());
            // specify an adapter (see also next example)
            mAdapter = new RecycleAdapter(currentDeck, new Itemclick() {
                @Override
                public void onItemClicked(String data) {
                    Log.i(ShowCardActivity.class.getSimpleName(), data);
                }
            });
            currentCard = 0;
            myFlashCardData = currentDeck.getFlashcards().get(currentCard);
            // setting current card info
            //todo should flashcards have a title for a better overview ?
            show_card_info.setText("" + myFlashCardData.getId());
            mQuestionAdapter = new QuestionRecycleAdapter(myFlashCardData, new Itemclick() {
                @Override
                public void onItemClicked(String data) {
                    Log.i(ShowCardActivity.class.getSimpleName(), data);
                    answer_btn.setText(data);
                }
            });
            // setting  progress info
            settingProgressInfo();

            //Spannable wordtoSpan = new SpannableString("1/10");
            //wordtoSpan.setSpan(new BackgroundColorSpan(Color.RED),0,1,1);
            //wordtoSpan.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            //show_progress_info.setText(wordtoSpan);

            // setting adapters to views
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
            currentUser.SetCardIncorrect(currentUser.getCurrentDeck(), currentCard);
            nextCard();

        }
    }

    public void nextCard() {
        currentCard++;
        if (currentCard  >= currentDeck.getFlashcards().size()) {
            deckDone();

        }
        answer_btn.setText("Answer");
        updateCard();


    }

    private void updateCard() {
        myFlashCardData.update(currentDeck.getFlashcards().get(currentCard));
        show_card_info.setText("" + currentDeck.getFlashcards().get(currentCard).getId());
        settingProgressInfo();
        mQuestionAdapter.notifyDataSetChanged();
    }

    private void deckDone() {
        if (currentUser.getCurrentDeck() + 1 >= currentUser.getDeckList().size()) {
            // TODO When all decks are done it just restarts at index 0
            currentDeck.update(currentUser.getDeckList().get(0));
            mQuestionAdapter.notifyDataSetChanged();
        }
        nextDeck();

    }

    private void nextDeck() {
        currentUser.nextDeck();
        currentCard = 0;
        currentDeck.update(currentUser.getDeckList().get(currentUser.getCurrentDeck()));
        show_deck_info.setText(currentUser.getDeckList().get(currentUser.getCurrentDeck()).getTitle());
        mQuestionAdapter.notifyDataSetChanged();
    }

    private void setColor(TextView view, String fulltext, String subtext, int color) {
        view.setText(fulltext, TextView.BufferType.SPANNABLE);
        Spannable str = (Spannable) view.getText();
        int i = fulltext.indexOf(subtext);
        str.setSpan(new ForegroundColorSpan(color), i, i + subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

    }
    private void settingProgressInfo() {
        String coloredText = setCorrectAnswers();
        String fullText = coloredText+"/"+setTotalAnswers();
        int color = Color.GREEN;
        setColor(show_progress_info, fullText, coloredText, color);
    }

    private String setTotalAnswers() {
        ArrayList<Integer> progress = currentUser.getDeckProgressList().get(currentUser.getCurrentDeck()).getProgress();
        return ""+progress.size();
    }

    private String setCorrectAnswers() {
        DeckProgress progressDeck = currentUser.getDeckProgressList().get(currentUser.getCurrentDeck());
        int res=0;
        for (Integer answer: progressDeck.getProgress()) {
            if (answer == 1){
                res++;
            }
        }
        return ""+res;
    }
}
