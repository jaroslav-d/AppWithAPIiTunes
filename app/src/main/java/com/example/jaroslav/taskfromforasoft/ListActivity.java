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

    @Override
    public void outputMessageNoConnection() {
        textError.post(new Runnable() {
            @Override
            public void run() {
                textError.setVisibility(View.VISIBLE);
                textError.setText(R.string.connect_error);
            }
        });
    }

    @Override
    public void outputMessageNoArtist() {
        textError.post(new Runnable() {
            @Override
            public void run() {
                textError.setVisibility(View.VISIBLE);
                textError.setText(R.string.artist_error);
            }
        });
    }
}
