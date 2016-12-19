package com.example.thomasskogemann.flashcard.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.example.thomasskogemann.flashcard.R

/**
 * Created by Thomas Skogemann on 14-10-2016.
 */
class ResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var resultCategoryView: TextView
    var resultRatingView: TextView
    var resultCorrectView: TextView

    init {
        resultCategoryView = view.findViewById(R.id.result_category_View) as TextView
        resultRatingView = view.findViewById(R.id.result_rating_view) as TextView
        resultCorrectView = view.findViewById(R.id.result_correct_view) as TextView

    }


}
