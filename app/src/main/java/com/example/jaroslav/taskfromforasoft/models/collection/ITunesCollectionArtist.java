package com.example.jaroslav.taskfromforasoft.models.collection;

import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemAlbum;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemArtist;

import java.util.ArrayList;

// This class implements the model of the list of artists returned by the method api "search"
public class ITunesCollectionArtist extends ITunesCollection {
    private ArrayList<ITunesItemArtist> results;

    public ArrayList<ITunesItemArtist> getAll() {
        return results;
    }
}
