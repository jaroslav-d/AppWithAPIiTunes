package com.example.jaroslav.taskfromforasoft.models.collection;

import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemAlbum;

import java.util.ArrayList;

public class ITunesCollectionAlbum extends ITunesCollection {
    protected ArrayList<ITunesItemAlbum> results;

    public ArrayList<ITunesItemAlbum> getResults() {
        return results;
    }
}
