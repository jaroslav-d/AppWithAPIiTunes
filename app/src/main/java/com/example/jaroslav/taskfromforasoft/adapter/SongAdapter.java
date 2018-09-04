package com.example.jaroslav.taskfromforasoft.adapter;

import android.support.annotation.NonNull;

import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionSong;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemSong;

import java.util.ArrayList;

public class SongAdapter extends ListAdapter {

    public SongAdapter(ITunesCollectionSong dataSong) {
        // Creates the necessary variables for the adapter on the page with the song list
        nameElement = new ArrayList<>();
        photoElement = new ArrayList<>();
        for (ITunesItemSong itemSong : dataSong.getAll()) {
            nameElement.add(itemSong.getTrackName());
            photoElement.add(itemSong.getPhoto());
        }
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        // cancel the click for the list of songs,
        // because there is no need to go to the next activity
        holder.setClick(false);
    }

    public int getItemCount() {
        return nameElement.size();
    }
}
