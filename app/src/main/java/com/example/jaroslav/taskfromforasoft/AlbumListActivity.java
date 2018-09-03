package com.example.jaroslav.taskfromforasoft;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.jaroslav.taskfromforasoft.adapter.AlbumAdapter;
import com.example.jaroslav.taskfromforasoft.networkthread.AlbumNetworkThread;

public class AlbumListActivity extends ListActivity{
    AlbumNetworkThread network;
//    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        network = new AlbumNetworkThread("network");
        network.setArtistName(getIntent().getStringExtra("nameArtist"));
        network.setCallback(this);
        network.start();
        textError = findViewById(R.id.textErrorAlbum);
        progressBar = findViewById(R.id.progressBarAlbum);
        recyclerView = findViewById(R.id.recyclerViewAlbum);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        bundle = savedInstanceState;
//        ListAdapter adapter = new ListAdapter();
//        adapter.setAlbumName(bundle.getStringArrayList("albumName"));
//        adapter.setAlbumPhoto(bundle.<Bitmap>getParcelableArrayList("albumPhoto"));
//        adapter.setAlbumIDs(bundle.getIntegerArrayList("albumIDs"));
//        progressBarList = findViewById(R.id.progressBarList);
//        progressBarList.setVisibility(View.INVISIBLE);
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
//        recyclerView.setAdapter(adapter);
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putAll(bundle);
//    }

    public void unloadData() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                AlbumAdapter adapter = new AlbumAdapter(network.getAlbums());
                recyclerView.setAdapter(adapter);
//                bundle = new Bundle();
//                bundle.putStringArrayList("albumName", adapter.getAlbumName());
//                bundle.putParcelableArrayList("albumPhoto",adapter.getAlbumPhoto());
//                bundle.putIntegerArrayList("albumIDs", adapter.getAlbumIDs());
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
