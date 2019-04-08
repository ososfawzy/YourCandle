package com.yourcandle.yourcandle.Activities.Register;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.yourcandle.yourcandle.Activities.Home.HomeActivity;
import com.yourcandle.yourcandle.Models.User;
import com.yourcandle.yourcandle.R;
import com.yourcandle.yourcandle.Utilities.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {
    @BindView(R.id.nameEdt)
    EditText nameEdt;
    @BindView(R.id.mailEdt)
    EditText mailEdt;
    @BindView(R.id.passwordEdt)
    EditText passwordEdt;
    @BindView(R.id.phoneEdt)
    EditText phoneEdt;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.voulnterOption)
    RadioButton voulnterOption;
    @BindView(R.id.blindOption)
    RadioButton blindOption;
    @BindView(R.id.goToHome)
    Button goToHome;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    private User user;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        user = new User();
        presenter = new RegisterPresenter(this, this, new RegisterInteractor());

        phoneEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    getUserData();
                }
                return false;
            }
        });
        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserData();
            }
        });

    }


    private void getUserData() {
        if (Validation.validateName(nameEdt)) {
            if (Validation.validateEmail(mailEdt)) {
                if (Validation.validatePassword(passwordEdt)) {
                    if (Validation.validatePhone(phoneEdt)) {
                        if (user.getUser_type() == null) {
                            user.setUser_type("0");
                        }
                        user.setUser_name(nameEdt.getText().toString());
                        user.setUser_email(mailEdt.getText().toString());
                        user.setUser_password(passwordEdt.getText().toString());
                        user.setUser_phone(phoneEdt.getText().toString());
                        presenter.checkData(user);
                    } else {
                        Toast.makeText(this, "Invalid phone", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Invalid mail", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid Name", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.blindOption:
                if (checked) {
                    user.setUser_type("0");
                }
                break;
            case R.id.voulnterOption:
                if (checked) {
                    user.setUser_type("1");
                }
                break;
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
    public void MoveToHome() {
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void setEmailError() {
        Toast.makeText(this, "Email is already exist", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUsernameMailError() {
        Toast.makeText(this, "username and mail is exist", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setUsernameError() {
        Toasty.error(this,getResources().getString(R.string.usernameExist),Toast.LENGTH_LONG,true).show();
    }


    @Override
    public void setError(String error) {
        Toasty.error(this,getResources().getString(R.string.error),Toast.LENGTH_LONG,true).show();
    }
}
