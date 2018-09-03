package com.example.jaroslav.taskfromforasoft.models.collection;

import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemAlbum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ITunesCollectionAlbum extends ITunesCollection {
    private ArrayList<ITunesItemAlbum> results;

    public ArrayList<ITunesItemAlbum> getAll() {
        // This code sort albums on name albums
        ArrayList<ITunesItemAlbum> sortedList = new ArrayList<>(results.subList(1, results.size()));
        Collections.sort(sortedList, new Comparator<ITunesItemAlbum>() {
            @Override
            public int compare(ITunesItemAlbum o1, ITunesItemAlbum o2) {
                return o1.getAlbumName().compareTo(o2.getAlbumName());
            }
        });
        return sortedList;
    }
}
