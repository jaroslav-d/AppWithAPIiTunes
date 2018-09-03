package com.example.jaroslav.taskfromforasoft.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jaroslav.taskfromforasoft.R;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionAlbum;
import com.example.jaroslav.taskfromforasoft.models.collection.ITunesCollectionSong;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemAlbum;
import com.example.jaroslav.taskfromforasoft.models.item.ITunesItemSong;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    protected ArrayList<String> nameElement;
    protected ArrayList<Bitmap> photoElement;
    protected ArrayList<Integer> albumIDs;

    public ListAdapter(){}

    public void createElements(ITunesCollectionSong data) {
        nameElement = new ArrayList<>();
        photoElement = new ArrayList<>();
        for (ITunesItemSong itemSong : data.getAll()) {
            nameElement.add(itemSong.getTrackName());
            photoElement.add(itemSong.getPhoto());
        }
    }

    public void createElements(ITunesCollectionAlbum data) {
        nameElement = new ArrayList<>();
        photoElement = new ArrayList<>();
        albumIDs = new ArrayList<>();
        for (ITunesItemAlbum itemSong : data.getAll()) {
            nameElement.add(itemSong.getAlbumName());
            photoElement.add(itemSong.getPhoto());
            albumIDs.add(itemSong.getAlbumId());
        }
    }

    // Provide a reference to the views for each albumName item
    // Complex albumName items may need more than one view per item, and
    // you provide access to all the views for a albumName item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each albumName item is just a string in this case
        public TextView textList;
        public ImageView imageList;
        private Intent intent;
        private boolean click = true;

        public MyViewHolder(View v) {
            super(v);
            textList = v.findViewById(R.id.textInList);
            imageList = v.findViewById(R.id.imageInList);
            v.setOnClickListener(clickListener);
        }

        public void setIntent(Intent intent) {
            this.intent = intent;
        }

        public void setClick(boolean click) {
            this.click = click;
        }

        private View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!click) { return;}
                v.getContext().startActivity(intent);
            }
        };
    }

    // Create new views (invoked by the layout manager)
    @Override
    @NonNull
    public ListAdapter.MyViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_view, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textList.setText(nameElement.get(position));
        if (photoElement.get(position) != null) {
            holder.imageList.setImageBitmap(photoElement.get(position));
            holder.imageList.setVisibility(View.VISIBLE);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {return 0;}
}
