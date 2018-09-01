package com.example.jaroslav.taskfromforasoft.models.collection;

import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemAlbum;

import java.util.ArrayList;

public class ITunesCollectionAlbum extends ITunesCollection {
    private ArrayList<ITunesItemAlbum> results;

    public ArrayList<ITunesItemAlbum> getResults() {
        return new ArrayList<ITunesItemAlbum>(results.subList(1, results.size()));
    }
}
