package com.example.thomasskogemann.flashcard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thomasskogemann.flashcard.R;
import com.example.thomasskogemann.flashcard.data.model.Flashcard;

/**
 * Created by Thomas Skogemann on 28-09-2016.
 */
public class QuestionRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Flashcard myDataset;
    private ItemclickListener itemclickListener;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public QuestionRecycleAdapter(Flashcard myDataset, ItemclickListener itemclickListener) {
        this.myDataset = myDataset;
        this.itemclickListener = itemclickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View myView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.questionitem, parent, false);
            QuestionHolder vh = new QuestionHolder(myView);
            return vh;

        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.questionheader, parent, false);
            return new HeaderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).headerTextView.setText(myDataset.getQuestion());
            return;
        } else {
            QuestionHolder questinViwHolder = (QuestionHolder) holder;
            String question = myDataset.getAnswers().get(position-1).getAnswer();
            questinViwHolder.questionView.setText("Question " + question);

            // onClickListener
            questinViwHolder.questionView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    itemclickListener.onItemClicked(position -1, myDataset.getAnswers().get(position -1).getAnswer());
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return myDataset.getAnswers().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }
}