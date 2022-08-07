package com.example.javalesson32.singltone;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    private static MySharedPreference mySharedPreference = new MySharedPreference();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static MySharedPreference getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("Login",Context.MODE_PRIVATE);
        }

        return mySharedPreference;
    }

    private MySharedPreference() {
    }

    public String getUsers() {
        return sharedPreferences.getString("users","");
    }
    public boolean setUsers(String str) {
        editor = sharedPreferences.edit();
        editor.putString("users",str).commit();

        return  editor.commit();
    }
}
