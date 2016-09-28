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
    private Deck myDataSet = new Deck(-1, "temp", new ArrayList<Flashcard>());
    private Flashcard myFlashCardData = new Flashcard(new ArrayList<String>(),0, "tempQuestion");

    //Layout
    @BindView(R.id.show_card_layout)
    RelativeLayout intro_layout;

    //TextViews
    @BindView(R.id.show_content)
    TextView show_content_view;

    //RecyclerViews
    @BindView(R.id.show_content_recycler_view)
    RecyclerView recycler_view;

    @BindView(R.id.show_question_recycler_view)
    RecyclerView question_view;

    //buttons
    @BindView(R.id.start_flash_card)
    Button start_flash_card_btn;

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


        //Setting myDataSet
        if (getIntent() != null) {
            myDataSet = (Deck) getIntent().getSerializableExtra("dummyDeck");
            // specify an adapter (see also next example)
            mAdapter = new RecycleAdapter(myDataSet, new Itemclick() {
                @Override
                public void onItemClicked(String data) {
                    Log.i(ShowCardActivity.class.getSimpleName(), data);
                }
            });
            myFlashCardData = myDataSet.getFlashcards().get(0);
            mQuestionAdapter = new QuestionRecycleAdapter(myFlashCardData, new Itemclick() {
                @Override
                public void onItemClicked(String data) {
                    Log.i(ShowCardActivity.class.getSimpleName(), data);
                }
            });

            mRecyclerView.setAdapter(mAdapter);
            mQuestionView.setAdapter(mQuestionAdapter);
        }
    }

    @OnClick(R.id.start_flash_card)
    public void startFlashCardClicked(){
        mRecyclerView.setVisibility(View.GONE);
        mQuestionView.setVisibility(View.VISIBLE);
    }
}
