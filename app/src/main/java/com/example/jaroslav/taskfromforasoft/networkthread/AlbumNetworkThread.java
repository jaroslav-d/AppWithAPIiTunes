package com.example.jaroslav.taskfromforasoft.networkthread;

import com.example.jaroslav.taskfromforasoft.models.JSONParser;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionAlbum;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionArtist;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemAlbum;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

// This class is necessary for finding the artist and showing his album list
public class AlbumNetworkThread extends NetworkThread {
    protected ITunesCollectionAlbum collectionAlbum;
    protected String nameArtist;

    public AlbumNetworkThread(String name) {
        super(name);
    }

    // This method sets the name of the artist for the search,
    // which was obtained through intention from the previous activity
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
            // does the string conversion in case the artist has two names
            String query = nameArtist.replace(" ", "+");
            URL url = new URL("https://itunes.apple.com/search?term=" + query + "&entity=musicArtist");
            // creates a stream to receive json file
            readStream(url);
            // creates a data model for the list of artists
            ITunesCollectionArtist collectionArtist = new JSONParser().parse(responseRawData, ITunesCollectionArtist.class);
            // this condition checks whether an artist exists with that name,
            // if it does not exist, then a callback method is performed
            // to display a message stating that there is no such artist
            if (collectionArtist.getCount() == 0) {
                callback.outputMessageNoArtist();
                return;
            }
            // get an id from an artist search
            int artistId = collectionArtist.getAll().get(0).getArtistId();
            url = new URL("https://itunes.apple.com/lookup?id=" + artistId + "&entity=album");
            readStream(url);
            // creates a data model for the list of albums
            collectionAlbum = new JSONParser().parse(responseRawData, ITunesCollectionAlbum.class);
            // for the created model of the list of songs we load necessary pictures
            for (ITunesItemAlbum itemAlbum : collectionAlbum.getAll()) {
                itemAlbum.setPhoto(unloadPhoto(itemAlbum.getPhotoUrl()));
            }
            // goes back to the activity that created it and launched
            callback.unloadData();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //goes back to the activity that created it
            // and run it to display the error message
            callback.outputMessageNoConnection();
        }
    }
}
