package com.yourcandle.yourcandle.Activities.Admin.Users;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.yourcandle.yourcandle.Models.User;
import com.yourcandle.yourcandle.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class UsersFragment extends Fragment implements UsersContract.view ,UsersListAdapter.SendUser{

    @BindView(R.id.userRecycler)
    RecyclerView userRecycler;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    private UsersPresenter presenter;
    private UsersListAdapter adapter;

    public UsersFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        ButterKnife.bind(this, view);
        setRecycler();
        presenter = new UsersPresenter(this, new UsersInteractor(), getActivity());
        presenter.checkData();

        return view;

    }

    public void setRecycler() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        userRecycler.setLayoutManager(manager);
        adapter = new UsersListAdapter(this);
        userRecycler.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        avi.setVisibility(View.VISIBLE);
        avi.show();
    }

    @Override
    public void hideProgress() {
        avi.setVisibility(View.GONE);
        avi.hide();
    }

    @Override
    public void setUserList(ArrayList<User> userList) {
        adapter.setUserList(userList);
    }

    @Override
    public void setBlockedSuccessflly() {
        Toasty.success(getActivity(),getResources().getString(R.string.error),Toast.LENGTH_LONG,true).show();
    }

    @Override
    public void setBlockedFailed() {
        Toast.makeText(getActivity(), "Error In Blocked User", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setError() {
        Toasty.error(getActivity(),getResources().getString(R.string.error),Toast.LENGTH_LONG,true).show();
    }

    @Override
    public void getOneUser(User user) {
        presenter.blockUser(user.getUser_id());
    }
}
