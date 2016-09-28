package com.example.thomasskogemann.flashcard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thomasskogemann.flashcard.R;
import com.example.thomasskogemann.flashcard.data.model.Deck;

import java.util.ArrayList;

/**
 * Created by Thomas Skogemann on 27-09-2016.
 */
public class RecycleAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Deck myDataset;
    private Itemclick itemclick;

    public RecycleAdapter(Deck myDataset, Itemclick itemclick) {
        this.myDataset = myDataset;
        this.itemclick = itemclick;
    }

    public void setMyDataset(Deck myDataset) {
        this.myDataset = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View myView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);
        MyViewHolder vh = new MyViewHolder(myView);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.myTextView.setText("Flashcard id: " + myDataset.getFlashcards().get(position).getId());

    }

    @Override
    public int getItemCount() {
        return myDataset.getFlashcards().size();
    }


}
