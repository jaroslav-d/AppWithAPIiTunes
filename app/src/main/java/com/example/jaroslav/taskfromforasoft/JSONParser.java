package com.example.jaroslav.taskfromforasoft;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
    String jsonString;

    public JSONParser(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            jsonObject.getInt("resultCount");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
