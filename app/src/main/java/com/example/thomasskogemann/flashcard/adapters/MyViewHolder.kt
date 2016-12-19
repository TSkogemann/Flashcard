package com.example.thomasskogemann.flashcard.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.thomasskogemann.flashcard.R

/**
 * Created by Thomas Skogemann on 27-09-2016.
 */
class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var myTextView: TextView

    init {
        myTextView = view.findViewById(R.id.myTextView) as TextView
    }
}
