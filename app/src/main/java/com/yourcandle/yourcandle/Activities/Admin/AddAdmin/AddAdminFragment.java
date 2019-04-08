package com.yourcandle.yourcandle.Activities.Admin.AddAdmin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;


import com.yourcandle.yourcandle.Models.User;
import com.yourcandle.yourcandle.R;
import com.yourcandle.yourcandle.Utilities.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class AddAdminFragment extends Fragment implements AddAdminContract.view {


    @BindView(R.id.nameEdt)
    EditText nameEdt;
    @BindView(R.id.mailEdt)
    EditText mailEdt;
    @BindView(R.id.passwordEdt)
    EditText passwordEdt;
    @BindView(R.id.phoneEdt)
    EditText phoneEdt;
    @BindView(R.id.addBtn)
    Button addBtn;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    private User user;
    private AddAdminPresenter presenter;



    public AddAdminFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_admin, container, false);
        ButterKnife.bind(this, view);
        user = new User();
        presenter = new AddAdminPresenter(getActivity(), this, new AddAdminInteractor());
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserData();
            }
        });
        return view;
    }

    private void getUserData() {
        if (Validation.validateName(nameEdt)) {
            if (Validation.validateEmail(mailEdt)) {
                if (Validation.validatePassword(passwordEdt)) {
                    if (Validation.validatePhone(phoneEdt)) {
                        user.setUser_name(nameEdt.getText().toString());
                        user.setUser_email(mailEdt.getText().toString());
                        user.setUser_password(passwordEdt.getText().toString());
                        user.setUser_phone(phoneEdt.getText().toString());
                        presenter.checkData(user);
                    } else {
                        Toast.makeText(getActivity(), "Invalid phone", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Invalid Password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Invalid mail", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Invalid Name", Toast.LENGTH_SHORT).show();
        }
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
    public void setSuccess() {
        Toasty.success(getActivity(),getResources().getString(R.string.error),Toast.LENGTH_LONG,true).show();

    }

    @Override
    public void setEmailError() {
        Toast.makeText(getActivity(), "Email is already exist", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUsernameMailError() {
        Toast.makeText(getActivity(), "username and mail is exist", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setUsernameError() {
        Toast.makeText(getActivity(), "username is already exist", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void setError() {
        Toasty.error(getActivity(),getResources().getString(R.string.error),Toast.LENGTH_LONG,true).show();
    }
}
