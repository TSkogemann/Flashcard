package com.example.thomasskogemann.flashcard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.thomasskogemann.flashcard.R;

/**
 * Created by Thomas Skogemann on 28-09-2016.
 */
public class QuestionHolder extends RecyclerView.ViewHolder {

    public TextView questionView;
    public TextView questionHeader;
    public QuestionHolder(View view){
        super(view);

        questionView = (TextView) view.findViewById(R.id.questionView);
        questionHeader =(TextView) view.findViewById(R.id.questionHeaderView);
    }
}
