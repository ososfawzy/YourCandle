package com.yourcandle.yourcandle.Activities.Profile;

import android.content.Context;
import android.net.Uri;
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
import com.yourcandle.yourcandle.Utilities.StoredManager;
import com.yourcandle.yourcandle.Utilities.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    @BindView(R.id.nameEdt)
    EditText nameEdt;
    @BindView(R.id.emailEdt)
    EditText emailEdt;
    @BindView(R.id.passwordEdt)
    EditText passwordEdt;
    @BindView(R.id.phoneEdt)
    EditText phoneEdt;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    private ProfilePresenter presenter;
    private User user;

    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        setData();
        presenter = new ProfilePresenter(getActivity(), this, new ProfileInteractor());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        return view;
    }

    void setData() {
        user = new User();
        user = StoredManager.getInstance().ReadUser(getActivity());
        nameEdt.setText(user.getUser_name());
        emailEdt.setText(user.getUser_email());
        passwordEdt.setText(user.getUser_password());
        phoneEdt.setText(user.getUser_phone());
    }

    void getData() {
        user = new User();
        if (Validation.validateName(nameEdt)) {
            if (Validation.validateEmail(emailEdt)) {
                if (Validation.validatePassword(passwordEdt)) {
                    if (Validation.validatePhone(phoneEdt)) {
                        user.setUser_id(StoredManager.getInstance().ReadUser(getActivity()).getUser_id());
                        user.setUser_name(nameEdt.getText().toString());
                        user.setUser_email(emailEdt.getText().toString());
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
    public void setsave() {
        Toasty.success(getActivity(),getResources().getString(R.string.error),Toast.LENGTH_LONG,true).show();


    }

    @Override
    public void setError() {
        Toasty.error(getActivity(),getResources().getString(R.string.error),Toast.LENGTH_LONG,true).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
