package com.example.jaroslav.taskfromforasoft.models.item;

// This class contains one element of the list returned from the statuses.
// It contains the basic variables json, which are found in the rest of the inherited classes.
// Inherited elements can have other parameters.
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