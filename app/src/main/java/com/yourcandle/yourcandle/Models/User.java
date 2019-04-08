package com.yourcandle.yourcandle.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by new on 4/17/2018.
 */

public class User {
    private String user_id;
    private String user_name;
    private String user_email;
    private String user_password;
    private String user_phone;
    private String user_address;
    private String user_type;
    private String user_profile_image;
    private String user_facebook;
    private String user_google;
    private String user_last_login;

    public User() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_profile_image() {
        return user_profile_image;
    }

    public void setUser_profile_image(String user_profile_image) {
        this.user_profile_image = user_profile_image;
    }

    public String getUser_facebook() {
        return user_facebook;
    }

    public void setUser_facebook(String user_facebook) {
        this.user_facebook = user_facebook;
    }

    public String getUser_google() {
        return user_google;
    }

    public void setUser_google(String user_google) {
        this.user_google = user_google;
    }

    public String getUser_last_login() {
        return user_last_login;
    }

    public void setUser_last_login(String user_last_login) {
        this.user_last_login = user_last_login;
    }

    public User parseUser(String json) {
        User user = new User();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("user");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject newObject = jsonArray.getJSONObject(i);
                user.setUser_id(newObject.getString("user_id"));
                user.setUser_name(newObject.getString("user_name"));
                user.setUser_email(newObject.getString("user_email"));
                user.setUser_password(newObject.getString("user_password"));
                user.setUser_type(newObject.getString("user_type"));
                user.setUser_profile_image(newObject.getString("user_profile_image"));
                user.setUser_last_login(newObject.getString("user_last_login"));
                user.setUser_address(newObject.getString("user_address"));
                user.setUser_phone(newObject.getString("user_phone"));
                user.setUser_facebook(newObject.getString("user_facebook"));
                user.setUser_google(newObject.getString("user_google"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> parseUserList(String json) {
        ArrayList<User> userArrayList = new ArrayList<>();
        User user = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("Users");
            for (int i = 0; i < jsonArray.length(); i++) {
                user = new User();
                JSONObject newObject = jsonArray.getJSONObject(i);
                user.setUser_id(newObject.getString("user_id"));
                user.setUser_name(newObject.getString("user_name"));
                user.setUser_email(newObject.getString("user_email"));
                user.setUser_password(newObject.getString("user_password"));
                user.setUser_type(newObject.getString("user_type"));
                user.setUser_profile_image(newObject.getString("user_profile_image"));
                user.setUser_last_login(newObject.getString("user_last_login"));
                user.setUser_address(newObject.getString("user_address"));
                user.setUser_phone(newObject.getString("user_phone"));
                user.setUser_facebook(newObject.getString("user_facebook"));
                user.setUser_google(newObject.getString("user_google"));
                userArrayList.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userArrayList;
    }

}
