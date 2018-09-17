package com.example.jaroslav.taskfromforasoft;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jaroslav.taskfromforasoft.networkthread.AlbumNetworkThread;

public class ListActivity extends AppCompatActivity implements AlbumNetworkThread.Callback {
    RecyclerView recyclerView;
    TextView textError;
    ProgressBar progressBar;

    @Override
    public void unloadData() {
    }

    // displays no connection message
    @Override
    public void outputMessageNoConnection() {
        textError.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                textError.setVisibility(View.VISIBLE);
                textError.setText(R.string.connect_error);
            }
        });
    }

    // displays a message that there is no such artist
    @Override
    public void outputMessageNoArtist() {
        textError.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                textError.setVisibility(View.VISIBLE);
                textError.setText(R.string.artist_error);
            }
        });
    }

    @Override
    public void outputMessageNoAlbumForArtist() {
        textError.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                textError.setVisibility(View.VISIBLE);
                textError.setText(R.string.album_error);
            }
        });
    }
}
