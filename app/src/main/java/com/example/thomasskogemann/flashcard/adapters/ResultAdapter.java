package com.example.thomasskogemann.flashcard.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thomasskogemann.flashcard.R;
import com.example.thomasskogemann.flashcard.data.model.User;

/**
 * Created by Thomas Skogemann on 14-10-2016.
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {
    User user;

    public ResultAdapter(User user) {
        this.user = user;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View myView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item, parent, false);
        ResultViewHolder vh = new ResultViewHolder(myView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.textView.setText("Your answer was: " + user.getFlashcards().get(position).getRating().getCorrect());
        holder.subTextView.setText("TESTESTT " + user.getFlashcards().get(position).getRating().getCorrect());

    }

    @Override
    public int getItemCount() {
        return user.getFlashcards().size();
    }
}
   /*

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.myTextView.setText("Flashcard id: " + myDataset.getFlashcards().get(position).getId());

    }



*/