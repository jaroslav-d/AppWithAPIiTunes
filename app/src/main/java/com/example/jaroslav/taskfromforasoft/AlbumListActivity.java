package com.example.jaroslav.taskfromforasoft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumListActivity extends AppCompatActivity implements NetworkThread.Callback {
    RecyclerView recyclerView;
    NetworkThread network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        network = new NetworkThread("network");
        network.setArtistName(getIntent().getStringExtra("firstName"),
                getIntent().getStringExtra("lastName"));
        network.setCallback(this);
        network.start();
        TextView textView = findViewById(R.id.textInList);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
    }

    public void unloadData() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(new AlbumAdapter(network.getAlbums()));
            }
        });
    }
}
