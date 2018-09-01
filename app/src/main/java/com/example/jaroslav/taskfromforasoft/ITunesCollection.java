package com.example.jaroslav.taskfromforasoft;

import android.graphics.Bitmap;

import java.util.ArrayList;

class ITunesCollection {
    private int resultCount;
    ArrayList<ITunesItem> results;

    public class ITunesItem {
        String wrapperType;
        String collectionType;
        int artistId;
        int collectionId;
        int amgArtistId;
        String artworkUrl100;
        Bitmap photoFromUrl100;

        public ITunesItem() {}
    }

    public int getResultCount() {
        return resultCount;
    }

    public ArrayList<ITunesItem> getItems() {
        return results;
    }

    public int getArtistID() {
        if (resultCount == 1) {
            return results.get(0).artistId;
        } else {
            return Integer.parseInt(null);
        }
    }

    public int getArtistID(int numberAlbum) {
        return results.get(numberAlbum).artistId;
    }

    public String getArtworkUrl100(int numberAlbum) {
        return results.get(numberAlbum).artworkUrl100;
    }

    public void setPhotoForAlbum(int numberAlbum, Bitmap bitmap){
        results.get(numberAlbum).photoFromUrl100 = bitmap;
    }
}