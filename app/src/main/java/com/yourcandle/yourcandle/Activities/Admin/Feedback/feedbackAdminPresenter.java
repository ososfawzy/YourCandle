package com.yourcandle.yourcandle.Activities.Admin.Feedback;

import android.content.Context;

import com.yourcandle.yourcandle.Models.Feedback;
import com.yourcandle.yourcandle.Utilities.Network;
import com.yourcandle.yourcandle.Utilities.URLs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class feedbackAdminPresenter implements FeedbackAdminContract.Presenter, FeedbackAdminContract.Interactor.onListFinished, FeedbackAdminInteractor.onDeleteFinished {
    private Context context;
    private FeedbackAdminInteractor interactor;
    private FeedbackFragment view;
    private Network network;
    private String url;

    public feedbackAdminPresenter(FeedbackFragment view, FeedbackAdminInteractor interactor, Context context) {
        this.view = view;
        this.interactor = interactor;
        this.context = context;
    }

    @Override
    public void onSuccess(String json) {
        if (view != null) {
            view.hideProgress();
            Feedback feedback = new Feedback();
            ArrayList<Feedback> Feedbacklist = feedback.parseFeedbackList(json);
            view.setFeedbackList(Feedbacklist);

        }
    }

    @Override
    public void onFailed() {
        if (view != null) {
            view.hideProgress();
            view.setError();
        }
    }

    @Override
    public void checkData() {
        if (view != null) {
            view.showProgress();
            network = new Network();
            url = network.buildURLWithoutParam(URLs.FeedbackList);
            interactor.DownloadData(this, context, url);
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void DeleteFeedback(String feedback_id) {
        if (view != null) {
            view.showProgress();
            network = new Network();
            Map<String, String> map = new HashMap<>();
            map.put("id", feedback_id);
            url = network.buildURL(URLs.deleteFeedback, map);
            interactor.DeleteData(this, context, url);
        }
    }

    @Override
    public void onDeleteSuccess() {
        if(view!=null){
            view.hideProgress();
            checkData();
            view.setDeleteSuccess();
        }

    }

    @Override
    public void onDeleteFailed() {
        if(view!=null){
            view.hideProgress();
            view.setDeleteFailed();
        }

    }
}
