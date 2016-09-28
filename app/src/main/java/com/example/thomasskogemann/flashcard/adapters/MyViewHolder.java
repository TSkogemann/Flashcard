package com.example.thomasskogemann.flashcard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.thomasskogemann.flashcard.R;

/**
 * Created by Thomas Skogemann on 27-09-2016.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView myTextView;
    public MyViewHolder(View view){
        super(view);
        myTextView = (TextView) view.findViewById(R.id.myTextView);
    }
}
