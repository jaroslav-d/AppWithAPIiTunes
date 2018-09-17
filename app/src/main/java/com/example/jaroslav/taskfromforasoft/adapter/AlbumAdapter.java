package com.example.jaroslav.taskfromforasoft.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.jaroslav.taskfromforasoft.SongListActivity;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionAlbum;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemAlbum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// This adapter for element view in album list activity
public class AlbumAdapter extends ListAdapter {
    protected ArrayList<Integer> albumIDs;
    private ArrayList<String> genreAndPrice;

    public ArrayList<String> getAlbumNames() {
        return nameElement;
    }

    public ArrayList<String> getAlbumPhotos() {
        return photoUrl;
    }

    public ArrayList<Integer> getAlbumIDs() {
        return albumIDs;
    }

    public void setAlbumNames(ArrayList<String> albumName) {
        this.nameElement = albumName;
    }

    public void setAlbumPhotos(ArrayList<String> albumPhoto) {
        this.photoUrl = albumPhoto;
    }

    public void setAlbumIDs(ArrayList<Integer> albumIDs) {
        this.albumIDs = albumIDs;
    }


    public AlbumAdapter(ITunesCollectionAlbum dataAlbums) {
        // Creates the necessary variables for the adapter on the page with the album list
        nameElement = new ArrayList<>();
        photoUrl = new ArrayList<>();
        albumIDs = new ArrayList<>();
        genreAndPrice = new ArrayList<>();
        for (ITunesItemAlbum itemSong : dataAlbums.getAll()) {
            nameElement.add(itemSong.getAlbumName());
            photoUrl.add(itemSong.getPhotoUrl());
            albumIDs.add(itemSong.getAlbumId());
            genreAndPrice.add("Genre: " + itemSong.getGenre() + "\n" + "Price: " + itemSong.getPrice());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        String mainTextSong = nameElement.get(position) + "\n" + genreAndPrice.get(position);
        // Create intent for loading a songs list on album
        Intent intent = new Intent(holder.textList.getContext(),SongListActivity.class);
        intent.putExtra("albumID", albumIDs.get(position));
        intent.putExtra("mainTextSong",mainTextSong);
        intent.putExtra("mainPhotoSong", photoUrl.get(position));
        holder.setIntent(intent);
    }

    public int getItemCount() {
        return nameElement.size();
    }
}
