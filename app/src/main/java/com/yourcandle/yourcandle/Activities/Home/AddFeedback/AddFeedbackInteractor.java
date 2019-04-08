package com.yourcandle.yourcandle.Activities.Home.AddFeedback;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddFeedbackInteractor implements  AddFeedbackContract.Interactor {
    @Override
    public void DownloadData(final onFinishedAdd onFinishedAdd, Context context, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                onFinishedAdd.onSuccess();
                            } else {
                                onFinishedAdd.onFailed(success);
                            }
                        } catch (JSONException e) {
                            onFinishedAdd.onFailed("-1");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onFinishedAdd.onFailed("error in " + error.getMessage());
            }
        });
        queue.add(stringRequest);
    }
    }

