package com.example.redstar.ctourbeta.dto;

import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by RedStar on 22.05.2016.
 */
public class JSONParser {
    Gson gson = new Gson();

    public <T> T fromJSON(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

}
