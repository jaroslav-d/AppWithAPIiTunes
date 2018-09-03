package com.example.jaroslav.taskfromforasoft;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.jaroslav.taskfromforasoft.adapter.SongAdapter;
import com.example.jaroslav.taskfromforasoft.networkthread.SongNetworkThread;

public class SongListActivity extends ListActivity{
    SongNetworkThread network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        network = new SongNetworkThread("networkSong");
        network.setCallback(this);
        network.setAlbumId(getIntent().getIntExtra("albumID",0));
        network.start();
        textError = findViewById(R.id.textErrorSong);
        progressBar = findViewById(R.id.progressBarSong);
        recyclerView = findViewById(R.id.recyclerViewSong);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
    }

    @Override
    public void unloadData() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                SongAdapter adapter = new SongAdapter(network.getSongs());
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void outputMessageNoConnection() {
        super.outputMessageNoConnection();
    }

    @Override
    public void outputMessageNoArtist() {
        super.outputMessageNoArtist();
    }
}
