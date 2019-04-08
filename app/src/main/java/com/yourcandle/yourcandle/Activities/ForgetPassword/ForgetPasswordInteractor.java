package com.yourcandle.yourcandle.Activities.ForgetPassword;

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
 * Created by new on 5/10/2018.
 */

public class ForgetPasswordInteractor implements ForgetPasswordContract.Interactor {
    @Override
    public void downloadData(final onForgetFinished finished, Context context, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("1")){
                                finished.onSuccess();
                            }else {
                                finished.onFailed(success);
                            }
                        } catch (JSONException e) {
                            finished.onFailed("-2");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                finished.onFailed("-2");
            }
        });
        queue.add(stringRequest);
    }
}
