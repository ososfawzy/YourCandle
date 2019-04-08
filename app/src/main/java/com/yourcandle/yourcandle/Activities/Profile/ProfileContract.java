package com.yourcandle.yourcandle.Activities.Profile;

import android.content.Context;

import com.yourcandle.yourcandle.Activities.Register.RegisterContract;
import com.yourcandle.yourcandle.Models.User;


public interface ProfileContract {
    interface View {
        void showProgress();

        void hideProgress();

        void setsave();

        void setError();


    }
    interface Presenter {
        void checkData(User user);

        void onDestroy();
    }

    interface Interactor {

        void downloadData(onEditFinished finished, Context context, String url);

        interface onEditFinished {

            void onSuccess(String json);

            void onFailed(String success);
        }

    }
}
