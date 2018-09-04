package com.example.jaroslav.taskfromforasoft.models.collection;

import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemSong;

import java.util.ArrayList;

// This class returns a list of songs and only a list of songs
public class ITunesCollectionSong extends ITunesCollection {
    private ArrayList<ITunesItemSong> results;

    public ArrayList<ITunesItemSong> getAll(){
        return new ArrayList<>(results.subList(1, results.size()));
    }
}
