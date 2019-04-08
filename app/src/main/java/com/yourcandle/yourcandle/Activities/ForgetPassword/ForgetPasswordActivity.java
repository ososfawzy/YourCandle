package com.yourcandle.yourcandle.Activities.ForgetPassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.yourcandle.yourcandle.R;
import com.yourcandle.yourcandle.Utilities.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class ForgetPasswordActivity extends AppCompatActivity implements ForgetPasswordContract.View {

    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @BindView(R.id.sendBtn)
    Button sendBtn;
    @BindView(R.id.emailEdt)
    EditText emailEdt;
    private ForgetPasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        presenter = new ForgetPasswordPresenter(this, this, new ForgetPasswordInteractor());
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.validateEmail(emailEdt)) {
                    presenter.sendMail(emailEdt.getText().toString());
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, "Invalid mail", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void showProgress() {
        avi.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        avi.setVisibility(View.GONE);
    }

    @Override
    public void setError() {

        Toasty.error(this,getResources().getString(R.string.error),Toast.LENGTH_LONG,true).show();
    }

    @Override
    public void setSuccess() {
        Toast.makeText(this, "Password send to Your Mail", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
