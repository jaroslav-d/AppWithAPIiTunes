package com.example.jaroslav.taskfromforasoft.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
// parser for json files using bibilotec gson from google
public class JSONParser {
    public <T> T parse(String jsonString, Class<T> type) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(jsonString, type);
    }
}
