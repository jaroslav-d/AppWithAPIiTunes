package com.example.jaroslav.taskfromforasoft.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.jaroslav.taskfromforasoft.SongListActivity;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionAlbum;

import java.util.ArrayList;

public class AlbumAdapter extends ListAdapter {

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
        createElements(dataAlbums);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        // Create intent for search a songs in album
        Intent intent = new Intent(holder.textList.getContext(),SongListActivity.class);
        intent.putExtra("albumID",albumIDs.get(position));
        holder.setIntent(intent);
    }

    public int getItemCount() {
        return nameElement.size();
    }
}
