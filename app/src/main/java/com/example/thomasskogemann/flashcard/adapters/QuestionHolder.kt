package com.example.thomasskogemann.flashcard.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.example.thomasskogemann.flashcard.R

/**
 * Created by Thomas Skogemann on 28-09-2016.
 */
class QuestionHolder(view: View) : RecyclerView.ViewHolder(view) {

    var questionView: TextView
    var questionHeader: TextView

    init {

        questionView = view.findViewById(R.id.questionView) as TextView
        questionHeader = view.findViewById(R.id.questionHeaderView) as TextView
    }
}
