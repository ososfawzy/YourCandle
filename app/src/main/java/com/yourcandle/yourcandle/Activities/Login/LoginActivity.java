package com.yourcandle.yourcandle.Activities.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.yourcandle.yourcandle.Activities.ForgetPassword.ForgetPasswordActivity;
import com.yourcandle.yourcandle.Activities.Home.HomeActivity;
import com.yourcandle.yourcandle.Activities.Admin.AdminHomeActivity;
import com.yourcandle.yourcandle.Models.User;
import com.yourcandle.yourcandle.R;
import com.yourcandle.yourcandle.Utilities.Network;
import com.yourcandle.yourcandle.Utilities.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @BindView(R.id.LoginBtn)
    Button LoginBtn;

    @BindView(R.id.passwordEdt)
    EditText passwordEdt;
    @BindView(R.id.emailEdt)
    EditText emailEdt;

    private LoginPresenter presenter;
    private User user;
    private Network network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        user = new User();
        network = new Network();
        network.register(network, LoginActivity.this);

        presenter = new LoginPresenter(this, this, new LoginInteractor());
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserData();

            }
        });


        passwordEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    getUserData();
                }
                return false;
            }
        });


    }

    private void getUserData() {
        if (Validation.validateEmail(emailEdt)) {
            if (Validation.validatePassword(passwordEdt)) {
                user.setUser_email(emailEdt.getText().toString());
                user.setUser_password(passwordEdt.getText().toString());
                presenter.checkData(user);
            } else {
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid mail", Toast.LENGTH_SHORT).show();
        }


    }

    public void GoToForgetPass(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);

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
    public void MoveToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void MoveToAdmin() {
        Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void BlockedUser() {
        Toast.makeText(this, "You are Blocked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setEmailError() {
        Toast.makeText(this, "Error In Email", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswordError() {
        Toast.makeText(this, "Error In Password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setError() {

        Toasty.error(this, getResources().getString(R.string.error), Toast.LENGTH_LONG, true).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        network.unregister(network,LoginActivity.this);
    }
}
