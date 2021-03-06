package com.example.jaroslav.taskfromforasoft;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jaroslav.taskfromforasoft.adapter.SongAdapter;
import com.example.jaroslav.taskfromforasoft.networkthread.SongNetworkThread;

public class SongListActivity extends ListActivity{
    SongNetworkThread network;
    LinearLayout linearLayoutMain;
    LinearLayout linearLayoutPhotoText;
    TextView mainTextSong;
    ImageView mainPhotoSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        // set a new thread for the connection and send the parameters
        network = new SongNetworkThread("networkSong");
        network.setCallback(this);
        network.setAlbumId(getIntent().getIntExtra("albumID",0));
        network.start();
        // determine the elements of the activity layout for further work with them
        mainTextSong = findViewById(R.id.mainTextSong);
        mainPhotoSong = findViewById(R.id.mainPhotoSong);
        textError = findViewById(R.id.textErrorSong);
        progressBar = findViewById(R.id.progressBarSong);
        recyclerView = findViewById(R.id.recyclerViewSong);
        linearLayoutMain = findViewById(R.id.linearLayoutSongMain);
        linearLayoutPhotoText = findViewById(R.id.linearLayoutSongPhotoText);
        createThisLayout();
        // set the adapter manager
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
    }

    private void createThisLayout(){
        mainTextSong.setText(getIntent().getStringExtra("mainTextSong"));
        mainPhotoSong.setImageBitmap((Bitmap) getIntent().getParcelableExtra("mainPhotoSong"));
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            linearLayoutMain.setOrientation(LinearLayout.VERTICAL);
            linearLayoutPhotoText.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            linearLayoutMain.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutPhotoText.setOrientation(LinearLayout.VERTICAL);
        }
    }

    @Override
    public void unloadData() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                // we obtain from the connection stream already converted data obtained
                progressBar.setVisibility(View.INVISIBLE);
                mainTextSong.setVisibility(View.VISIBLE);
                mainPhotoSong.setVisibility(View.VISIBLE);
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
