package com.example.jaroslav.taskfromforasoft.adapter;

import android.support.annotation.NonNull;

import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionSong;

public class SongAdapter extends ListAdapter {

    public SongAdapter(ITunesCollectionSong dataSong) {
        createElements(dataSong);
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        holder.setClick(false);
    }

    public int getItemCount() {
        return nameElement.size();
    }
}
