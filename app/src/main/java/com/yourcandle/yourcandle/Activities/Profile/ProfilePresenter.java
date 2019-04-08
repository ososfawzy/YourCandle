package com.yourcandle.yourcandle.Activities.Profile;

import android.content.Context;

import com.yourcandle.yourcandle.Activities.Register.RegisterActivity;
import com.yourcandle.yourcandle.Activities.Register.RegisterInteractor;
import com.yourcandle.yourcandle.Models.User;
import com.yourcandle.yourcandle.Utilities.Network;
import com.yourcandle.yourcandle.Utilities.StoredManager;
import com.yourcandle.yourcandle.Utilities.URLs;

import java.util.HashMap;
import java.util.Map;

import static com.yourcandle.yourcandle.Utilities.URLs.Register;

/**
 * Created by new on 6/7/2018.
 */

public class ProfilePresenter implements ProfileContract.Presenter, ProfileContract.Interactor.onEditFinished {

    private Context context;
    private String url;
    private ProfileFragment view;
    private ProfileInteractor interactor;
    private Network network;
    private User user;

    public ProfilePresenter(Context context, ProfileFragment view, ProfileInteractor interactor) {
        this.context = context;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void checkData(User user) {
        if (view != null) {
            view.showProgress();
            network = new Network();
            Map<String, String> map = new HashMap<>();
            map.put("id", user.getUser_id());
            map.put("name", user.getUser_name());
            map.put("email", user.getUser_email());
            map.put("password", user.getUser_password());
            map.put("phone", user.getUser_phone());
            url = network.buildURL(URLs.EditProfile, map);
            interactor.downloadData(this, context, url);

        }
    }
    @Override
    public void onDestroy() {
        view = null;
    }
    @Override
    public void onSuccess(String json) {
        if (view != null) {
            view.hideProgress();
            view.setsave();
            user = new User();
            StoredManager.getInstance().SaveUser(context, json);
        }
    }
    @Override
    public void onFailed(String success) {
        if (view != null) {
            view.hideProgress();
            view.setError();
        }
    }
}
