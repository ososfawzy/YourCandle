package com.yourcandle.yourcandle.Activities.Login;

import android.content.Context;

import com.yourcandle.yourcandle.Models.User;

public interface LoginContract {

    interface View{
        void showProgress();

        void hideProgress();

        void MoveToHome();

        void MoveToAdmin();

        void BlockedUser();

        void setEmailError();

        void setPasswordError();

        void setError();
    }

    interface Presenter{
        void checkData(User user);

        void onDestroy();
    }

    interface Interactor{

        void downloadData(onLoginFinished finished, Context context,String url);

        interface onLoginFinished{

            void onSuccess(String json);

            void onFailed(String success);
        }

    }

}
