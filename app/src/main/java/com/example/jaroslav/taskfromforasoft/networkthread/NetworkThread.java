package com.example.jaroslav.taskfromforasoft.networkthread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

// This is the main thread and it implements the methods of
// connecting and reading data with itunes.
// Implements callback methods for displaying data on the screen.
public class NetworkThread extends Thread {
    protected String responseRawData;
    protected Callback callback;

    public NetworkThread(String name) {
        super(name);
    }

    // create callback
    public interface Callback {
        void unloadData();
        void outputMessageNoConnection();
        void outputMessageNoArtist();
        void outputMessageNoAlbumForArtist();
    }

    // set callback
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        super.run();
    }

    // This method is required to connect and retrieve data json
    protected void readStream(URL url) throws IOException {
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
    }

    // This method is required to download a photo from the URL address
    protected Bitmap unloadPhoto(String urlString) throws IOException {
        URL url = new URL(urlString);
        return BitmapFactory.decodeStream(url.openConnection().getInputStream());
    }

    // This method is required to download a photo from the URL address
    protected Bitmap unloadPhoto(URL url) throws IOException{
        return BitmapFactory.decodeStream(url.openConnection().getInputStream());
    }
}
