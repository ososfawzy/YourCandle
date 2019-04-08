package com.yourcandle.yourcandle.Models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Feedback {
    private String Feedback_id;
    private String Feedback_rate;
    private String Feedback_issue;
    private String user_id;
    private String call_id;

public Feedback() {

}

    public String getFeedback_id() {
        return Feedback_id;
    }

    public void setFeedback_id(String feedback_id) {
        Feedback_id = feedback_id;
    }

    public String getFeedback_rate() {
        return Feedback_rate;
    }

    public void setFeedback_rate(String feedback_rate) {
        Feedback_rate = feedback_rate;
    }

    public String getFeedback_issue() {
        return Feedback_issue;
    }

    public void setFeedback_issue(String feedback_issue) {
        Feedback_issue = feedback_issue;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCall_id() {
        return call_id;
    }

    public void setCall_id(String call_id) {
        this.call_id = call_id;
    }

    public ArrayList<Feedback> parseFeedbackList(String json) {
        ArrayList<Feedback> FeedbackArrayList = new ArrayList<>();
        Feedback feedback = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("Feedbacks");
            for (int i = 0; i < jsonArray.length(); i++) {
                feedback = new Feedback();
                JSONObject newObject = jsonArray.getJSONObject(i);
                feedback.setFeedback_id(newObject.getString("feedback_id"));
                feedback.setFeedback_rate(newObject.getString("feedback_rate"));
                feedback.setFeedback_issue(newObject.getString("feedback_issue"));
                feedback.setUser_id(newObject.getString("user_id"));
                feedback.setCall_id(newObject.getString("call_id"));
                FeedbackArrayList.add(feedback);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return FeedbackArrayList;
    }



   }