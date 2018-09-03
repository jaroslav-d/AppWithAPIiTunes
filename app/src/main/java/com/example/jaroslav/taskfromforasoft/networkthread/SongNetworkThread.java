package com.example.jaroslav.taskfromforasoft.networkthread;

import com.example.jaroslav.taskfromforasoft.models.JSONParser;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollection;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionSong;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemSong;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SongNetworkThread extends NetworkThread {
    protected ITunesCollectionSong collectionSong;
    protected int albumId;

    public SongNetworkThread(String name) {
        super(name);
    }

    public ITunesCollectionSong getSongs() { return collectionSong; }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    @Override
    public void run() {
        super.run();
        try {
            URL url = new URL("https://itunes.apple.com/lookup?id="+ albumId +"&entity=song");
            readStream(url);
            collectionSong = new JSONParser().parse(responseRawData, ITunesCollectionSong.class);
            for (ITunesItemSong itemSong : collectionSong.getAll()) {
                itemSong.setPhoto(unloadPhoto(itemSong.getPhotoUrl()));
            }
            callback.unloadData();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            callback.outputMessageNoConnection();
        }
    }
}
