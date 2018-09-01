package com.example.jaroslav.taskfromforasoft;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONParser {

    public ITunesCollection parse(String jsonString) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(jsonString, ITunesCollection.class);
    }
}
