package com.example.jaroslav.taskfromforasoft;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionAlbum;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionArtist;
import com.example.jaroslav.taskfromforasoft.models.JSONParser;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemAlbum;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkThread extends Thread {
    private String errorMassage = null;
    private String responseRawData;
    private String firstName;
    private String lastName;
    private Callback callback;

    public NetworkThread(String name) {
        super(name);
    }

    interface Callback {
        void unloadData();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setArtistName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ITunesCollectionAlbum getAlbums() {
        return dataAlbums;
    }

    @Override
    public void run() {
        super.run();
        try {
            String query = firstName + "+" + lastName;
            URL url = new URL("https://itunes.apple.com/search?term=" + query + "&entity=musicArtist");
            readStream(url);
            ITunesCollectionArtist collectionArtist = new JSONParser().parse(responseRawData, ITunesCollectionArtist.class);
            int artistId = collectionArtist.getResults().get(0).artistId;
            url = new URL("https://itunes.apple.com/lookup?id=" + artistId + "&entity=album");
            readStream(url);
            ITunesCollectionAlbum collectionAlbum = new JSONParser().parse(responseRawData, ITunesCollectionAlbum.class);
            for (ITunesItemAlbum itemAlbum : collectionAlbum.getResults()) {
                //dataAlbums.setPhotoForAlbum(i, unloadPhoto(dataAlbums.getURLPhoto(i)));
                // itemAlbum
            }
            callback.unloadData();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void readStream(URL url) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            int maxReadSize = 1000000;
            Reader reader = new InputStreamReader(stream, "UTF-8");
            char[] rawBuffer = new char[maxReadSize];
            int readSize;
            StringBuffer buffer = new StringBuffer();
            while (((readSize = reader.read(rawBuffer)) != -1) && maxReadSize > 0) {
                if (readSize > maxReadSize) {
                    readSize = maxReadSize;
                }
                buffer.append(rawBuffer, 0, readSize);
                maxReadSize -= readSize;
            }
            responseRawData = buffer.toString();
            stream.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            errorMassage = "нет подключения к интернету";
        }
    }

    private Bitmap unloadPhoto(String urlString) {
        try {
            URL url = new URL(urlString);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            errorMassage = "нет подключения к интернету";
            return null;
        }
    }

    private Bitmap unloadPhoto(URL url) {
        try {
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            errorMassage = "нет подключения к интернету";
            return null;
        }
    }
}
