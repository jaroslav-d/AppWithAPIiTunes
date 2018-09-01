package com.example.jaroslav.taskfromforasoft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class AlbumListActivity extends AppCompatActivity implements NetworkThread.Callback {
    RecyclerView recyclerView;
    NetworkThread network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        String[] data = new String[10];
        for (int i = 0; i < 10; i++) {
            data[i] = getString(R.string.greeting);
        }
        network = new NetworkThread("network");
        network.setArtistName(getIntent().getStringExtra("firstName"),
                getIntent().getStringExtra("lastName"));
        network.setCallback(this);
        network.start();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(new AlbumAdapter(data));
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
