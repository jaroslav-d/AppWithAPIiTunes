package com.example.jaroslav.taskfromforasoft.models.collection;

import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemArtist;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemSong;

import java.util.ArrayList;

public class ITunesCollectionSong extends ITunesCollection {
    private ArrayList<ITunesItemSong> results;

    public ArrayList<ITunesItemSong> getResults(){
        return new ArrayList<ITunesItemSong>(results.subList(1, results.size()));
    }
}
