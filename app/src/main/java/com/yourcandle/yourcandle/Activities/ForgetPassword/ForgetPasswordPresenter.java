package com.yourcandle.yourcandle.Activities.ForgetPassword;

import android.content.Context;

import com.yourcandle.yourcandle.Utilities.Network;
import com.yourcandle.yourcandle.Utilities.URLs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by new on 5/10/2018.
 */

public class ForgetPasswordPresenter implements ForgetPasswordContract.Presenter, ForgetPasswordContract.Interactor.onForgetFinished {

    private Context context;
    private ForgetPasswordActivity view;
    private ForgetPasswordInteractor interactor;
    private Network network;
    private String url;

    public ForgetPasswordPresenter(Context context, ForgetPasswordActivity view, ForgetPasswordInteractor interactor) {
        this.context = context;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void sendMail(String email) {
        if (view != null) {
            view.showProgress();
            network = new Network();
            Map<String, String> map = new HashMap<>();
            map.put("email", email);
            url = network.buildURL(URLs.Forget, map);
            interactor.downloadData(this, context, url);
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onSuccess() {
        if (view != null) {
            view.hideProgress();
            view.setSuccess();
        }
    }

    @Override
    public void onFailed(String Type) {
        if (view != null) {
            view.hideProgress();
            if (Type.equals("0")) {
                view.setError();
            }
        } else {
            view.setError();
        }

    }
}
