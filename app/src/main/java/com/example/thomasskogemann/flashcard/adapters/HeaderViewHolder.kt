package com.example.thomasskogemann.flashcard.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.example.thomasskogemann.flashcard.R

import butterknife.ButterKnife

/**
 * Created by Thomas Skogemann on 28-09-2016.
 */
class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal var headerTextView: TextView

    init {
        headerTextView = ButterKnife.findById<TextView>(itemView, R.id.questionHeaderView)
    }
}
