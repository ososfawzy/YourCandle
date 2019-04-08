package com.yourcandle.yourcandle.Activities.Admin.AddAdmin;

import android.content.Context;

import com.yourcandle.yourcandle.Models.User;
import com.yourcandle.yourcandle.Utilities.Network;

import java.util.HashMap;
import java.util.Map;

import static com.yourcandle.yourcandle.Utilities.URLs.Register;

/**
 * Created by new on 6/2/2018.
 */

public class AddAdminPresenter implements  AddAdminContract.Presenter,AddAdminContract.Interactor.onFinishedAdd {

    private Context context;
    private String url;
    private AddAdminFragment view;
    private AddAdminInteractor interactor;
    private Network network;
    private User user;

    public AddAdminPresenter(Context context, AddAdminFragment  view, AddAdminInteractor interactor) {
        this.context = context;
        this.view = view;
        this.interactor = interactor;
    }


    @Override
    public void onSuccess(String json) {
        if (view != null) {
            view.hideProgress();
            view.setSuccess();
        }
    }

    @Override
    public void onFailed(String success) {
        if (view != null) {
            view.hideProgress();
            if (success.equals("0")) {
                view.setError();
            } else if (success.equals("-1")) {
                view.setUsernameMailError();
            } else if (success.equals("-2")) {
                view.setEmailError();
            } else if (success.equals("-3")) {
                view.setUsernameError();
            } else {
                view.setError();
            }
        }
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
            map.put("type", "2");
            url = network.buildURL(Register, map);
            interactor.DownloadData(this, context, url);
        }
    }

    @Override
    public void destroy() {
        view = null;
    }
}
