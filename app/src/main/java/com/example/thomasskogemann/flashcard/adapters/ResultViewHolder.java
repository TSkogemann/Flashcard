package com.example.thomasskogemann.flashcard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.thomasskogemann.flashcard.R;

/**
 * Created by Thomas Skogemann on 14-10-2016.
 */
public class ResultViewHolder extends RecyclerView.ViewHolder {
    public TextView resultCategoryView;
    public TextView resultRatingView;
    public TextView resultCorrectView;

    public ResultViewHolder(View view) {
        super(view);
        resultCategoryView = (TextView) view.findViewById(R.id.result_category_View);
        resultRatingView = (TextView) view.findViewById(R.id.result_rating_view);
        resultCorrectView = (TextView) view.findViewById(R.id.result_correct_view);

    }


}
