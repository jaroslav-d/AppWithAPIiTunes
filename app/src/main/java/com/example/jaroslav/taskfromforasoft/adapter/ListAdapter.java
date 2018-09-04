package com.example.jaroslav.taskfromforasoft.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jaroslav.taskfromforasoft.R;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionAlbum;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionSong;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemAlbum;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemSong;

import java.util.ArrayList;

// This class implements the adapter for activity with the type of recycler.
// This adapter is needed to create the content of the list in accordance with
// the specified layout of one line. The row layout is in resources named element_view
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    protected ArrayList<String> nameElement;
    protected ArrayList<Bitmap> photoElement;

    public ListAdapter(){}

    // Here is a reference to the views for each view element
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textList;
        public ImageView imageList;
        private Intent intent;
        private boolean click = true;

        public MyViewHolder(View v) {
            super(v);
            textList = v.findViewById(R.id.textInList);
            imageList = v.findViewById(R.id.imageInList);
            v.setOnClickListener(clickListener);
        }

        // Creates an intention for moving on to the next activity
        public void setIntent(Intent intent) {
            this.intent = intent;
        }

        // Sets the condition whether the intent will be transferred to another
        // Activity when you click on the list item
        public void setClick(boolean click) {
            this.click = click;
        }

        // Implements an interface for clicking on a list item
        private View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!click) { return;}
                v.getContext().startActivity(intent);
            }
        };
    }

    // Create new views (invoked by the layout manager, which is in the activity)
    @Override
    @NonNull
    public ListAdapter.MyViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_view, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // - get element from dataset at this position
        // - replace the contents of the view with that element
        holder.textList.setText(nameElement.get(position));
        if (photoElement.get(position) != null) {
            holder.imageList.setImageBitmap(photoElement.get(position));
            holder.imageList.setVisibility(View.VISIBLE);
        }
    }

    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {return 0;}
}
