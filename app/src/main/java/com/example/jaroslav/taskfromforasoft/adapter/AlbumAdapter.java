package com.example.jaroslav.taskfromforasoft.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.jaroslav.taskfromforasoft.SongListActivity;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionAlbum;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemAlbum;

import java.util.ArrayList;

// This adapter for element view in album list activity
public class AlbumAdapter extends ListAdapter {
    protected ArrayList<Integer> albumIDs;

    public ArrayList<String> getAlbumNames() {
        return nameElement;
    }

    public ArrayList<Bitmap> getAlbumPhotos() {
        return photoElement;
    }

    public ArrayList<Integer> getAlbumIDs() {
        return albumIDs;
    }

    public void setAlbumNames(ArrayList<String> albumName) {
        this.nameElement = albumName;
    }

    public void setAlbumPhotos(ArrayList<Bitmap> albumPhoto) {
        this.photoElement = albumPhoto;
    }

    public void setAlbumIDs(ArrayList<Integer> albumIDs) {
        this.albumIDs = albumIDs;
    }


    public AlbumAdapter(ITunesCollectionAlbum dataAlbums) {
        // Creates the necessary variables for the adapter on the page with the album list
        nameElement = new ArrayList<>();
        photoElement = new ArrayList<>();
        albumIDs = new ArrayList<>();
        for (ITunesItemAlbum itemSong : dataAlbums.getAll()) {
            nameElement.add(itemSong.getAlbumName());
            photoElement.add(itemSong.getPhoto());
            albumIDs.add(itemSong.getAlbumId());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        // Create intent for loading a songs list on album
        Intent intent = new Intent(holder.textList.getContext(),SongListActivity.class);
        intent.putExtra("albumID",albumIDs.get(position));
        holder.setIntent(intent);
    }

    public int getItemCount() {
        return nameElement.size();
    }
}
