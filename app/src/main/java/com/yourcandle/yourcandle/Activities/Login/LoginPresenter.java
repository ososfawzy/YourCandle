package com.yourcandle.yourcandle.Activities.Login;

import android.content.Context;

import com.yourcandle.yourcandle.Models.User;
import com.yourcandle.yourcandle.Utilities.Network;
import com.yourcandle.yourcandle.Utilities.StoredManager;
import com.yourcandle.yourcandle.Utilities.URLs;

import java.util.HashMap;
import java.util.Map;

import static com.yourcandle.yourcandle.Utilities.URLs.login;

/**
 * Created by new on 4/17/2018.
 */

public class LoginPresenter implements LoginContract.Presenter, LoginContract.Interactor.onLoginFinished {

    private Context context;
    private String url;
    private LoginActivity view;
    private LoginInteractor interactor;
    private Network network;

    public LoginPresenter(Context context, LoginActivity view, LoginInteractor interactor) {
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
            map.put("email", user.getUser_email());
            map.put("password", user.getUser_password());
            url = network.buildURL(URLs.login, map);
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
            User user = new User();
            StoredManager.getInstance().SaveUser(context, json);
            user = StoredManager.getInstance().ReadUser(context);
            if (user.getUser_type().equals("0") || user.getUser_type().equals("1")) {
                view.MoveToHome();
            }else if(user.getUser_type().equals("2")){
                view.MoveToAdmin();
            }else {
                view.BlockedUser();
            }

        }
    }

    @Override
    public void onFailed(String success) {
        if (view != null) {
            view.hideProgress();
            if (success.equals("0")) {
                view.setEmailError();
            } else if (success.equals("-1")) {
                view.setPasswordError();
            } else {
                view.setError();
            }
        }
    }
}
