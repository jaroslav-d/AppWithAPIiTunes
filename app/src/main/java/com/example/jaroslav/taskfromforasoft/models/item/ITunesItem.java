package com.example.jaroslav.taskfromforasoft.models.item;

class ITunesItem {
    private String wrapperType;
    private String collectionType;
    private int artistId;
    private int collectionId;
    private int amgArtistId;
    private String collectionName;

    public int getArtistId() {
        return artistId;
    }

    protected String getWrapperType() {
        return wrapperType;
    }

    protected String getCollectionType() {
        return collectionType;
    }

    protected int getCollectionId() {
        return collectionId;
    }

    protected int getAmgArtistId() {
        return amgArtistId;
    }

    public String getAlbumName(){
        return collectionName;
    }
}