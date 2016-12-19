package com.example.thomasskogemann.flashcard.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.thomasskogemann.flashcard.R
import com.example.thomasskogemann.flashcard.data.model.User

/**
 * Created by Thomas Skogemann on 27-09-2016.
 */
class RecycleAdapter(private var myDataset: User?) : RecyclerView.Adapter<MyViewHolder>() {

    fun setMyDataset(myDataset: User) {
        this.myDataset = myDataset
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val myView = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem, parent, false)
        val vh = MyViewHolder(myView)
        return vh
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.myTextView.text = "Flashcard id: " + myDataset!!.flashcards[position].id

    }

    override fun getItemCount(): Int {
        return myDataset!!.flashcards.size
    }


}
