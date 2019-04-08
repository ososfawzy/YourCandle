package com.yourcandle.yourcandle.Activities.Admin.Users;

import android.content.Context;

import com.yourcandle.yourcandle.Models.User;
import com.yourcandle.yourcandle.Utilities.Network;
import com.yourcandle.yourcandle.Utilities.URLs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.yourcandle.yourcandle.Utilities.URLs.UserList;

/**
 * Created by new on 6/2/2018.
 */

public class UsersPresenter implements UsersContract.Presenter, UsersContract.Interactor.onListFinished ,UsersContract.Interactor.onBlockedFinished{

    private UsersFragment view;
    private UsersInteractor interactor;
    private Context context;
    private String URL;
    private Network network;

    public UsersPresenter(UsersFragment view, UsersInteractor interactor, Context context) {
        this.view = view;
        this.interactor = interactor;
        this.context = context;
    }

    @Override
    public void checkData() {
        if (view != null) {
            view.showProgress();
            network = new Network();
            URL = network.buildURLWithoutParam(UserList);
            interactor.DownloadData(this, context, URL);
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void blockUser(String id) {
        if(view != null){
            view.showProgress();
            network = new Network();
            Map<String,String> map= new HashMap<>();
            map.put("id",id);
            URL = network.buildURL(URLs.blockUser,map);
            interactor.Blocked(this,context,URL);
        }
    }

    @Override
    public void onSuccess(String json) {
        if (view != null) {
            view.hideProgress();
            User user = new User();
            ArrayList<User> userArrayList = user.parseUserList(json);
            view.setUserList(userArrayList);
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
    public void onBlockSuccess() {
        if(view != null){
            view.hideProgress();
            checkData();
            view.setBlockedSuccessflly();
        }
    }

    @Override
    public void onBlockFailed() {
        if(view != null){
            view.hideProgress();
            view.setBlockedFailed();
        }
    }
}
