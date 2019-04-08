package com.yourcandle.yourcandle.Activities.Home.AddFeedback;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.yourcandle.yourcandle.Models.Feedback;
import com.yourcandle.yourcandle.R;
import com.yourcandle.yourcandle.Utilities.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import hyogeun.github.com.colorratingbarlib.ColorRatingBar;

public class UserFeedbackFragment extends Fragment implements AddFeedbackContract.view {

    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @BindView(R.id.feedbackIssue)
    EditText feedbackIssue;
    @BindView(R.id.rating)
    ColorRatingBar rating;
    @BindView(R.id.addBtn)
    Button addBtn;
    private AddFeedbackPresenter presenter;

    public UserFeedbackFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_feedback, container, false);
        ButterKnife.bind(this, view);
        presenter = new AddFeedbackPresenter(this, getActivity(), new AddFeedbackInteractor());
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();

            }
        });
        return view;
    }

    private void getData() {
        Feedback feedback = new Feedback();
        if (!feedbackIssue.getText().toString().isEmpty()) {
            feedback.setFeedback_issue(feedbackIssue.getText().toString());
            feedback.setFeedback_rate(String.valueOf(rating.getRating()));
            presenter.checkData(feedback);
        } else {
            Toast.makeText(getActivity(), "Please Enter Your Issue", Toast.LENGTH_SHORT).show();
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
    public void setFeedbackSuccess() {
        Toast.makeText(getActivity(), "Feedback Added Successfully", Toast.LENGTH_SHORT).show();
        feedbackIssue.setText("");
        rating.setRating(2.5f);
    }

    @Override
    public void setError() {
        Toasty.error(getActivity(),getResources().getString(R.string.error),Toast.LENGTH_LONG,true).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onnDestroy();
    }
}
