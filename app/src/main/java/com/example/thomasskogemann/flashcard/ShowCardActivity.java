package com.example.thomasskogemann.flashcard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.thomasskogemann.flashcard.adapters.ItemclickListener;
import com.example.thomasskogemann.flashcard.adapters.QuestionRecycleAdapter;
import com.example.thomasskogemann.flashcard.adapters.RecycleAdapter;
import com.example.thomasskogemann.flashcard.data.model.Answer;
import com.example.thomasskogemann.flashcard.data.model.Flashcard;
import com.example.thomasskogemann.flashcard.data.model.Rating;
import com.example.thomasskogemann.flashcard.data.model.User;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Thomas Skogemann on 27-09-2016.
 */
public class ShowCardActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mQuestionAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mQuestionManager;
    private Flashcard flashcardData = new Flashcard("tempCategory", new ArrayList<Answer>(), "tempID", new Rating(), "tempQuestion");
    private User currentUser;
    private int currentCard = 0;
    private int answerNumber = 0;
    private int redoMode = 0; // 0 = off 1 = on

    //Layout
    @BindView(R.id.show_card_layout)
    RelativeLayout intro_layout;

    //TextViews
    @BindView(R.id.show_user_info)
    TextView show_user_info;

    @BindView(R.id.show_category_info)
    TextView show_category_info;

    @BindView(R.id.show_progress_info)
    TextView show_progress_info;

    @BindView(R.id.show_date_info)
    TextView show_date_info;

    //RecyclerViews
    @BindView(R.id.show_content_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.show_question_recycler_view)
    RecyclerView mQuestionView;

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

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mQuestionManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mQuestionView.setLayoutManager(mQuestionManager);

        //Setting user
        if (getIntent().hasExtra("dummyUser")) {
            currentUser = (User) getIntent().getSerializableExtra("dummyUser");
            show_user_info.setText("" + currentUser.getName());
        }
        // setting view
        settingProgressInfo();
        setDateInfo();
        //show_category_info.setText(currentUser.getFlashcards().get(currentCard).getCategory());


        //Setting adapters
        mAdapter = new RecycleAdapter(currentUser);
        mQuestionAdapter = new QuestionRecycleAdapter(flashcardData, new ItemclickListener() {
            @Override
            public void onItemClicked(int itemNumber, String data) {
                Log.i(ShowCardActivity.class.getSimpleName(), data);
                answer_btn.setText(data);
                answerNumber = itemNumber;
            }
        });
        updateView();

        // setting adapters to views
        mRecyclerView.setAdapter(mAdapter);
        mQuestionView.setAdapter(mQuestionAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        currentCard=0;
    }

    private void setDateInfo() {
        show_progress_info.setVisibility(View.INVISIBLE);
        show_date_info.setVisibility(View.VISIBLE);
        show_date_info.setText("" + new Date());
    }


    @OnClick(R.id.start_flash_card)
    public void startFlashCardClicked() {
        mRecyclerView.setVisibility(View.GONE);
        mQuestionView.setVisibility(View.VISIBLE);
        answer_btn.setVisibility(View.VISIBLE);
        show_progress_info.setVisibility(View.VISIBLE);
        show_category_info.setVisibility(View.VISIBLE);
        show_date_info.setVisibility(View.INVISIBLE);
        start_flash_card_btn.setVisibility(View.GONE);
    }

    @OnClick(R.id.answer)
    public void answer_btn_clicked() {
        // Guard should be changed, lowering currentCard by 1 is not a clean way to do it
        //// TODO: 14-10-2016  
        if (currentUser.getFlashcards().size() <= currentCard) {
            Log.d(currentCard + " - " + currentUser.getFlashcards().size(),"");
            currentCard = currentCard -1;
            return;
        }

        Flashcard card = currentUser.getFlashcards().get(currentCard);
        if (card.getAnswers().get(answerNumber).getCorrect()) {
            // Correct answer
            //TODO set rating
            card.setRating(calculateRating(true, "tempCorrectId", 5, new Date(), 10));
            nextCard();
        } else {
            // Incorrect answer
            card.setRating(calculateRating(false, "tempIncorrectId", 1, new Date(), 15));
            nextCard();
        }
    }

    private Rating calculateRating(boolean isCorrect, String id, int rating, Date date, int time) {
        Rating newRating = new Rating();
        // setting is correct
        newRating.setCorrect(isCorrect);
        // setting id - should be autogenerated?
        newRating.set_id(id);
        // setting rating - TODO needs a method to calculate how
        newRating.setRating(rating);
        // setting date
        newRating.setSolveDate(date);
        // setting time it took to solve the question - TODO need a method
        newRating.setTime(time);
        return newRating;
    }

    public void nextCard() {
        currentCard++;
        if (currentCard >= currentUser.getFlashcards().size()) {
            deckDone();
        }
        updateView();
    }

    private void updateView() {
        answer_btn.setText("Answer");
        updateNextCard();
        settingProgressInfo();
        mQuestionAdapter.notifyDataSetChanged();
    }

    private void updateNextCard() {
        if (redoMode == 0) {
            if (currentUser.getFlashcards().size() > currentCard) {
                flashcardData.update(currentUser.getFlashcards().get(currentCard));
            }
        }
        if (redoMode == 1) {
            if (currentUser.getFlashcards().size() > currentCard) {
                if (currentUser.getFlashcards().get(currentCard).getRating().getCorrect() == false) {
                    flashcardData.update(currentUser.getFlashcards().get(currentCard));
                } else
                    nextCard();
            } else redoMode = 0;
        }
    }

    private void deckDone() {
        //todo
        redoIncorrectCards();
        if (redoMode == 0) {
            showResults();
        }

    }

    private void redoIncorrectCards() {
        if (redoMode == 1) {
            redoMode = 0;
            return;
        }

        if (redoMode == 0) {
            redoMode = 1;
            currentCard = 0;
        }
    }

    private void showResults() {
        //TODO
        // intent
        Intent intent = new Intent(ShowCardActivity.this, ResultActivity.class);
        intent.putExtra("currentUser", currentUser);
        //change activity
        startActivity(intent);
        /*
        currentUser.nextDeck();
        currentCard = 0;
        currentDeck.update(currentUser.getDeckList().get(currentUser.getCurrentDeck()));
        show_deck_info.setText(currentUser.getDeckList().get(currentUser.getCurrentDeck()).getTitle());
        mQuestionAdapter.notifyDataSetChanged();
        */
    }


    // methods used for creating Span text. Colored text
    private void settingProgressInfo() {
        if (redoMode == 0) {
            String coloredText = setIncorrectAnswers();
            String fullText = setCardsLeft() + "/" + coloredText;
            int color = Color.RED;
            setColor(show_progress_info, fullText, coloredText, color);
            setCategoryInfo();

        }
        if (redoMode == 1) {
            int incorrectLeft = Integer.parseInt(setIncorrectAnswers());
            show_progress_info.setText("TODO");
            setCategoryInfo();

        }
    }

    private void setCategoryInfo() {
        // setting card category
        if (currentUser.getFlashcards().size() > currentCard) {
            show_category_info.setText(currentUser.getFlashcards().get(currentCard).getCategory());
        }
    }

    private void setColor(TextView view, String fulltext, String subtext, int color) {
        view.setText(fulltext, TextView.BufferType.SPANNABLE);
        Spannable str = (Spannable) view.getText();
        int i = fulltext.indexOf("/") + 1;
        str.setSpan(new ForegroundColorSpan(color), i, i + subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

    }

    private String setCardsLeft() {
        int cards = 0;
        if (redoMode == 1) {
            for (Flashcard flashcard : currentUser.getFlashcards()) {
                if (flashcard.getRating().getCorrect() != null) {
                    if (flashcard.getRating().getCorrect() == false) {
                        cards++;
                    }
                }
            }
        }

        if (redoMode == 0) {
            cards = currentUser.getFlashcards().size() - currentCard;
        }
        return "" + cards;
    }

    private String setIncorrectAnswers() {

        int res = 0;

        for (Flashcard flashcard : currentUser.getFlashcards()) {
            if (flashcard.getRating().getCorrect() != null) {
                if (flashcard.getRating().getCorrect() == false) {
                    res++;
                }
            }
        }
        return "" + res;
    }

}
