package com.example.thomasskogemann.flashcard.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.thomasskogemann.flashcard.R
import com.example.thomasskogemann.flashcard.data.model.User

/**
 * Created by Thomas Skogemann on 14-10-2016.
 */
class ResultAdapter(internal var user: User) : RecyclerView.Adapter<ResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {

        val myView = LayoutInflater.from(parent.context)
                .inflate(R.layout.result_item, parent, false)
        val vh = ResultViewHolder(myView)
        return vh
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val answer: String
        if (user.flashcards[position].rating.correct === true) {
            answer = "correct"
        } else
            answer = "incorrect"
        holder.resultCategoryView.text = user.flashcards[position].category
        holder.resultRatingView.text = "Your rating: " + user.flashcards[position].rating.rating
        holder.resultCorrectView.text = "You answer was: " + answer


    }

    override fun getItemCount(): Int {
        return user.flashcards.size
    }
}
/*

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.myTextView.setText("Flashcard id: " + myDataset.getFlashcards().get(position).getId());

    }



*/