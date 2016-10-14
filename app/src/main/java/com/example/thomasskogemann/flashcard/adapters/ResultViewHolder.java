package com.example.thomasskogemann.flashcard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.thomasskogemann.flashcard.R;

/**
 * Created by Thomas Skogemann on 14-10-2016.
 */
public class ResultViewHolder extends RecyclerView.ViewHolder {
public TextView textView;
    public TextView subTextView;

    public ResultViewHolder(View view) {
        super(view);
        textView = (TextView) view.findViewById(R.id.resultView);
        subTextView = (TextView) view.findViewById(R.id.subText);

    }


}
