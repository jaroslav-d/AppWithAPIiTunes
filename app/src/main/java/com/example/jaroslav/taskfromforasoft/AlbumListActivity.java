package com.example.jaroslav.taskfromforasoft;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.jaroslav.taskfromforasoft.adapter.AlbumAdapter;
import com.example.jaroslav.taskfromforasoft.networkthread.AlbumNetworkThread;
import com.squareup.picasso.Picasso;

public class AlbumListActivity extends ListActivity{
    AlbumNetworkThread network;
//    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        // set a new thread for the connection and send the parameters
        network = new AlbumNetworkThread("network");
        network.setArtistName(getIntent().getStringExtra("nameArtist"));
        network.setCallback(this);
        network.start();
        // determine the elements of the activity layout for further work with them
        textError = findViewById(R.id.textErrorAlbum);
        progressBar = findViewById(R.id.progressBarAlbum);
        recyclerView = findViewById(R.id.recyclerViewAlbum);
        // set the adapter manager
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
                // we obtain from the connection stream already converted data obtained
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
}
