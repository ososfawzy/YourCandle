package com.yourcandle.yourcandle.Activities.Register;

import android.content.Context;

import com.yourcandle.yourcandle.Activities.Login.LoginActivity;
import com.yourcandle.yourcandle.Activities.Login.LoginInteractor;
import com.yourcandle.yourcandle.Models.User;
import com.yourcandle.yourcandle.Utilities.Network;
import com.yourcandle.yourcandle.Utilities.StoredManager;

import java.util.HashMap;
import java.util.Map;

import static com.yourcandle.yourcandle.Utilities.URLs.Register;
import static com.yourcandle.yourcandle.Utilities.URLs.login;

/**
 * Created by new on 4/23/2018.
 */

public class RegisterPresenter implements RegisterContract.Presenter, RegisterContract.Interactor.onRegisterFinished {


    private Context context;
    private String url;
    private RegisterActivity view;
    private RegisterInteractor interactor;
    private Network network;
    private User user;

    public RegisterPresenter(Context context, RegisterActivity view, RegisterInteractor interactor) {
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
            map.put("name", user.getUser_name());
            map.put("email", user.getUser_email());
            map.put("password", user.getUser_password());
            map.put("phone", user.getUser_phone());
            map.put("type", user.getUser_type());
            url = network.buildURL(Register, map);
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
            view.MoveToHome();
            user = new User();
            StoredManager.getInstance().SaveUser(context, json);
        }
    }

    @Override
    public void onFailed(String success) {
        if (view != null) {
            view.hideProgress();
            if (success.equals("0")) {
                view.setError(success);
            } else if (success.equals("-1")) {
                view.setUsernameMailError();
            } else if (success.equals("-2")) {
                view.setEmailError();
            } else if (success.equals("-3")) {
                view.setUsernameError();
            } else {
                view.setError(success);
            }
        }
    }

}