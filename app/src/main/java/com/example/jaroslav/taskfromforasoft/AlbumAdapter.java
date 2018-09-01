package com.example.jaroslav.taskfromforasoft;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionAlbum;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {
    private String[] albumName;
    private Bitmap[] albumPhoto;

    // Provide a reference to the views for each albumName item
    // Complex albumName items may need more than one view per item, and
    // you provide access to all the views for a albumName item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each albumName item is just a string in this case
        public ProgressBar progressBarList;
        public TextView textList;
        public ImageView imageList;
        public MyViewHolder(View v) {
            super(v);
            textList = v.findViewById(R.id.textInList);
            imageList = v.findViewById(R.id.imageInList);
            progressBarList = v.findViewById(R.id.progressBarInList);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlbumAdapter(String[] albumName) {
        this.albumName = albumName;
    }

    public AlbumAdapter(ITunesCollectionAlbum dataAlbums) {
        albumName = new String[dataAlbums.getAlbumCount()];
        albumPhoto = new Bitmap[dataAlbums.getAlbumCount()];
        for (int i = 0; i < dataAlbums.getAlbumCount(); i++) {
            albumName[i] = dataAlbums.getAlbumName(i);
            albumPhoto[i] = dataAlbums.getAlbumPhoto(i);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlbumAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textList.setText(albumName[position]);
        if (albumPhoto != null) {
            holder.progressBarList.onWindowSystemUiVisibilityChanged(View.INVISIBLE);
            holder.imageList.setImageBitmap(albumPhoto[position]);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return albumName.length;
    }
}
