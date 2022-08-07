package com.example.javalesson32.singltone;

import com.google.gson.Gson;

public class MyGson {
    private static MyGson myGson = new MyGson();
    private static Gson gson;

    public static MyGson getInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return myGson;
    }

    private MyGson() {
    }

    public Gson getGson() {
        return gson;
    }
}
