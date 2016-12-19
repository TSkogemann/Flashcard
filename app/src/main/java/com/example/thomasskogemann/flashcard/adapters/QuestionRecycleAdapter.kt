package com.example.thomasskogemann.flashcard.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.thomasskogemann.flashcard.R
import com.example.thomasskogemann.flashcard.data.model.Flashcard

/**
 * Created by Thomas Skogemann on 28-09-2016.
 */
class QuestionRecycleAdapter(private val myDataset: Flashcard, private val itemclickListener: ItemclickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == TYPE_ITEM) {
            val myView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.questionitem, parent, false)
            val vh = QuestionHolder(myView)
            return vh

        } else {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.questionheader, parent, false)
            return HeaderViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HeaderViewHolder) {
            holder.headerTextView.text = myDataset.question
            return
        } else {
            val questinViwHolder = holder as QuestionHolder
            val question = myDataset.answers[position - 1].answer
            questinViwHolder.questionView.text = "Question " + question

            // onClickListener
            questinViwHolder.questionView.setOnClickListener { itemclickListener.onItemClicked(position - 1, myDataset.answers[position - 1].answer) }

        }
    }

    override fun getItemCount(): Int {
        return myDataset.answers.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        } else {
            return TYPE_ITEM
        }
    }

    companion object {
        private val TYPE_HEADER = 0
        private val TYPE_ITEM = 1
    }
}