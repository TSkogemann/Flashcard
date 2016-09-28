package com.example.thomasskogemann.flashcard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.thomasskogemann.flashcard.R;

import butterknife.ButterKnife;

/**
 * Created by Thomas Skogemann on 28-09-2016.
 */
public class HeaderViewHolder extends RecyclerView.ViewHolder {
    TextView headerTextView;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        headerTextView = ButterKnife.findById(itemView, R.id.questionHeaderView);
    }
}
