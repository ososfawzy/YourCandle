package com.yourcandle.yourcandle.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.yourcandle.yourcandle.Models.User;

/**
 * Created by new on 5/7/2018.
 */

public class StoredManager {
    private static final StoredManager ourInstance = new StoredManager();

    public static StoredManager getInstance() {
        return ourInstance;
    }

    private StoredManager() {
    }

    public void SaveUser(Context context, String json) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userJson", json);
        editor.commit();
    }

    public User ReadUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        User user = null;
        String json = sharedPreferences.getString("userJson", "0");
        if(json != null || !json.equals("") ){
            user = new User();
            return user.parseUser(json);
        }
        return user;
    }
}
