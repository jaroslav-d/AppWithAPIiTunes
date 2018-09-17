package com.example.jaroslav.taskfromforasoft.networkthread;

import com.example.jaroslav.taskfromforasoft.models.JSONParser;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollection;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionSong;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemSong;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

// This class is needed to get a list of songs by their id
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
            // open the flow to get raw data from itunes
            readStream(url);
            // convert the data to the desired format for further processing
            collectionSong = new JSONParser().parse(responseRawData, ITunesCollectionSong.class);
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
