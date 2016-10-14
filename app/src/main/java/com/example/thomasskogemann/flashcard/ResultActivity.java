package com.example.thomasskogemann.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.example.thomasskogemann.flashcard.adapters.ItemclickListener;
import com.example.thomasskogemann.flashcard.adapters.QuestionRecycleAdapter;
import com.example.thomasskogemann.flashcard.adapters.RecycleAdapter;
import com.example.thomasskogemann.flashcard.adapters.ResultAdapter;
import com.example.thomasskogemann.flashcard.data.model.User;
import com.example.thomasskogemann.flashcard.data.model.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Thomas Skogemann on 13-10-2016.
 */
public class ResultActivity extends AppCompatActivity {
    User user;
    ResultAdapter mResultAddapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // recycler views
    @BindView(R.id.result_list)
    RecyclerView resultView;

    // Buttons
    @BindView(R.id.get_more_cards_btn)
    Button getMoreCardsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        // setting user
        if (getIntent().hasExtra("currentUser")) {
            user = (User) getIntent().getSerializableExtra("currentUser");

        }

        // setting layout manager
        mLayoutManager = new LinearLayoutManager(this);
        resultView.setLayoutManager(mLayoutManager);


        // Adapter
        mResultAddapter = new ResultAdapter(user);
        // setting adapters to views
        resultView.setAdapter(mResultAddapter);


    }

    @OnClick(R.id.get_more_cards_btn)
    public void getMoreCards() {
        // generating dummy info
        MainActivity bla = new MainActivity();
        final User dummyUser = User.generateDummyUser(Utils.getRandomString() + "ID", "dummyUser", 10, 4);

        // intent
        Intent intent = new Intent(ResultActivity.this, ShowCardActivity.class);
        intent.putExtra("dummyUser", dummyUser);
        //change activity
        startActivity(intent);
    }
}
