package com.yourcandle.yourcandle.Activities.Home.AddFeedback;

import android.content.Context;

import com.yourcandle.yourcandle.Models.Feedback;
import com.yourcandle.yourcandle.Models.User;
import com.yourcandle.yourcandle.Utilities.Network;
import com.yourcandle.yourcandle.Utilities.StoredManager;
import com.yourcandle.yourcandle.Utilities.URLs;

import java.util.HashMap;
import java.util.Map;

public class AddFeedbackPresenter implements AddFeedbackContract.Presenter, AddFeedbackContract.Interactor.onFinishedAdd {

    private UserFeedbackFragment view;
    private Context context;
    private AddFeedbackInteractor interactor;
    private Network network;
    private String url;

    public AddFeedbackPresenter(UserFeedbackFragment view, Context context, AddFeedbackInteractor interactor) {
        this.view = view;
        this.context = context;
        this.interactor = interactor;
    }

    @Override
    public void checkData(Feedback feedback) {
        if (view != null) {
            view.showProgress();

            User user = new User();
            user = StoredManager.getInstance().ReadUser(context);

            network = new Network();
            Map<String, String> map = new HashMap<>();
            map.put("rate", feedback.getFeedback_rate());
            map.put("issue", feedback.getFeedback_issue());
            map.put("userID", user.getUser_id());
            map.put("callID", "1");

            url = network.buildURL(URLs.AddFeedback, map);
            interactor.DownloadData(this, context, url);
        }

    }

    @Override
    public void onnDestroy() {
        view = null;
    }

    @Override
    public void onSuccess() {
        if (view != null) {
            view.hideProgress();
            view.setFeedbackSuccess();
        }
    }

    @Override
    public void onFailed(String error) {
        if (view != null) {
            view.hideProgress();
            view.setError();
        }
    }
}
