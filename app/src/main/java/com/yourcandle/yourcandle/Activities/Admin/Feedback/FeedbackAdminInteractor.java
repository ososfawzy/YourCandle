package com.yourcandle.yourcandle.Activities.Admin.Feedback;

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
 * Created by new on 6/3/2018.
 */

public class FeedbackAdminInteractor implements  FeedbackAdminContract.Interactor {

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
    public void DeleteData(final onDeleteFinished deleteFinished, final Context context, final String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                deleteFinished.onDeleteSuccess();
                            } else {
                                deleteFinished.onDeleteFailed();
                            }
                        } catch (JSONException e) {
                            deleteFinished.onDeleteFailed();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                deleteFinished.onDeleteFailed();
            }
        });
        queue.add(stringRequest);

    }
}

