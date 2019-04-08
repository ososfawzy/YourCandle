package com.yourcandle.yourcandle.Activities.Home.AddFeedback;

import android.content.Context;

import com.yourcandle.yourcandle.Models.Feedback;

public interface AddFeedbackContract {
    interface view {
        void showProgress();

        void hideProgress();

        void setFeedbackSuccess();

        void setError();

    }
    interface Presenter {
        void checkData(Feedback feedback);

        void onnDestroy();
    }

    interface Interactor {
        void DownloadData(onFinishedAdd onFinishedAdd, Context context, String url);

        interface onFinishedAdd {
            void onSuccess();

            void onFailed(String error);
        }

    }

}
