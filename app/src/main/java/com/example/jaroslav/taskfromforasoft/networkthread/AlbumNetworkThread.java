package com.example.jaroslav.taskfromforasoft.networkthread;

import com.example.jaroslav.taskfromforasoft.models.JSONParser;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionAlbum;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionArtist;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemAlbum;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AlbumNetworkThread extends NetworkThread {
    protected ITunesCollectionAlbum collectionAlbum;
    protected String nameArtist;

    public AlbumNetworkThread(String name) {
        super(name);
    }

    public void setArtistName(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public ITunesCollectionAlbum getAlbums() {
        return collectionAlbum;
    }

    @Override
    public void run() {
        super.run();
        try {
            String query = nameArtist.replace(" ", "+");
            URL url = new URL("https://itunes.apple.com/search?term=" + query + "&entity=musicArtist");
            readStream(url);
            ITunesCollectionArtist collectionArtist = new JSONParser().parse(responseRawData, ITunesCollectionArtist.class);
            if (collectionArtist.getCount() == 0) {
                callback.outputMessageNoArtist();
                return;
            }
            int artistId = collectionArtist.getAll().get(0).getArtistId();
            url = new URL("https://itunes.apple.com/lookup?id=" + artistId + "&entity=album");
            readStream(url);
            collectionAlbum = new JSONParser().parse(responseRawData, ITunesCollectionAlbum.class);
            for (ITunesItemAlbum itemAlbum : collectionAlbum.getAll()) {
                itemAlbum.setPhoto(unloadPhoto(itemAlbum.getPhotoUrl()));
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
