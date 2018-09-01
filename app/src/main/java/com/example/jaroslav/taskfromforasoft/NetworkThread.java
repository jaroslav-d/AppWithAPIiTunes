package com.example.jaroslav.taskfromforasoft;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkThread extends Thread {
    private ITunesCollection dataAlbums;
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

    public ITunesCollection getAlbums() {
        return dataAlbums;
    }

    @Override
    public void run() {
        super.run();
        try {
            URL url = new URL("https://itunes.apple.com" +
                    "/search?term=" +
                    firstName +
                    "+" +
                    lastName +
                    "&entity=musicArtist&limit=1");
            readStream(url);
            ITunesCollection searchData = new JSONParser().parse(responseRawData);
            int artistId = searchData.getArtistID();
            url = new URL("http://itunes.apple.com" +
                    "/lookup?id=" +
                     artistId +
                    "&entity=album");
            readStream(url);
            dataAlbums = new JSONParser().parse(responseRawData);
            for (int i = 1; i < dataAlbums.getResultCount()+1; i++) {
                dataAlbums.setPhotoForAlbum(i, unloadPhoto(dataAlbums.getArtworkUrl100(i)));
            }
            callback.unloadData();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void readStream(URL url) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            int maxReadSize = 1024;
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

    public Bitmap unloadPhoto(String urlString) {
        try {
            URL url = new URL(urlString);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            errorMassage = "нет подключения к интернету";
            return null;
        }
    }

    public Bitmap unloadPhoto(URL url) {
        try {
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            errorMassage = "нет подключения к интернету";
            return null;
        }
    }
}
