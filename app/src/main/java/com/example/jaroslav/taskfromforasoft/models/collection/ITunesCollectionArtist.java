package com.example.jaroslav.taskfromforasoft.models.collection;

import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemArtist;

import java.util.ArrayList;

public class ITunesCollectionArtist extends ITunesCollection {
    private ArrayList<ITunesItemArtist> results;

    public ArrayList<ITunesItemArtist> getResults() {
        return results;
    }
}
