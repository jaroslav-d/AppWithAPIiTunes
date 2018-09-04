package com.example.jaroslav.taskfromforasoft.models.collection;

// this class essentially adds one variable to the inherited classes,
// but it is created for convenience and helps to implement the data
// model returned from the itunes when requested json
public class ITunesCollection {
    private int resultCount;

    public int getCount() {
        return resultCount;
    }
}