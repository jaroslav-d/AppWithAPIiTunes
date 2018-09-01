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
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemAlbum;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {
    private ArrayList<String> albumName;
    private ArrayList<Bitmap> albumPhoto;

    // Provide a reference to the views for each albumName item
    // Complex albumName items may need more than one view per item, and
    // you provide access to all the views for a albumName item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each albumName item is just a string in this case
        public TextView textList;
        public ImageView imageList;

        public MyViewHolder(View v) {
            super(v);
            textList = v.findViewById(R.id.textInList);
            imageList = v.findViewById(R.id.imageInList);
        }
    }

    public AlbumAdapter(ITunesCollectionAlbum dataAlbums) {
        albumName = new ArrayList<String>();
        albumPhoto = new ArrayList<Bitmap>();
        for (ITunesItemAlbum itemAlbum : dataAlbums.getResults()) {
            albumName.add(itemAlbum.getName());
            albumPhoto.add(itemAlbum.getPhoto());
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
        String text = albumName.get(position);
        holder.textList.setText(text + " " + text + " " +text);
        if (albumPhoto.get(position) != null) {
            holder.imageList.setImageBitmap(albumPhoto.get(position));
            holder.imageList.setVisibility(View.VISIBLE);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return albumName.size();
    }
}
