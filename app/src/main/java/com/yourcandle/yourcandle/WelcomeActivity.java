package com.yourcandle.yourcandle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yourcandle.yourcandle.Activities.Home.HomeActivity;
import com.yourcandle.yourcandle.Activities.Login.LoginActivity;
import com.yourcandle.yourcandle.Activities.Register.RegisterActivity;
import com.yourcandle.yourcandle.Activities.Admin.AdminHomeActivity;
import com.yourcandle.yourcandle.Models.User;
import com.yourcandle.yourcandle.Utilities.StoredManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.signUpBtn)
    Button signUpBtn;
    @BindView(R.id.loginBtn)
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        User user = new User();
        user = StoredManager.getInstance().ReadUser(this);
        if (user.getUser_id() != null) {
            if (user.getUser_type().equals("0") || user.getUser_type().equals("1")) {
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(intent);
            } else if (user.getUser_type().equals("2")) {
                Intent intent = new Intent(WelcomeActivity.this, AdminHomeActivity.class);
                startActivity(intent);
            }
        }

    }

    public void GoToRegister(View view) {
        Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void GoToLogin(View view) {
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
