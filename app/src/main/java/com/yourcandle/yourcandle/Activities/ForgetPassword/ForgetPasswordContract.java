package com.yourcandle.yourcandle.Activities.ForgetPassword;

import android.content.Context;

import com.yourcandle.yourcandle.Models.User;

/**
 * Created by new on 5/10/2018.
 */

public interface ForgetPasswordContract {
    interface View {
        void showProgress();

        void hideProgress();

        void setError();

        void setSuccess();

    }

    interface Presenter {
        void sendMail(String email);

        void onDestroy();

    }

    interface Interactor {
        void downloadData(onForgetFinished finished, Context context, String url);

        interface onForgetFinished {

            void onSuccess();

            void onFailed(String Type);
        }


    }

}
