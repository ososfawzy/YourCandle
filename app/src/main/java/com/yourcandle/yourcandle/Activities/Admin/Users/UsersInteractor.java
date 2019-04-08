package com.yourcandle.yourcandle.Activities.Admin.Users;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by new on 6/2/2018.
 */

public class UsersInteractor implements UsersContract.Interactor {
    @Override
    public void DownloadData(final onListFinished listFinished, Context context, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                listFinished.onSuccess(response);
                            } else {
                                listFinished.onFailed();
                            }
                        } catch (JSONException e) {
                            listFinished.onFailed();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listFinished.onFailed();
            }
        });
        queue.add(stringRequest);
    }

    @Override
    public void Blocked(final onBlockedFinished blockedFinished, Context context, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                blockedFinished.onBlockSuccess();
                            } else {
                                blockedFinished.onBlockFailed();
                            }
                        } catch (JSONException e) {
                            blockedFinished.onBlockFailed();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                blockedFinished.onBlockFailed();
            }
        });
        queue.add(stringRequest);
    }
}
