package com.yourcandle.yourcandle.Activities.Register;

import android.content.Context;

import com.yourcandle.yourcandle.Activities.Login.LoginContract;
import com.yourcandle.yourcandle.Models.User;

/**
 * Created by new on 4/27/2018.
 */

public interface RegisterContract {

    interface View {
        void showProgress();

        void hideProgress();

        void MoveToHome();

        void setEmailError();

        void setUsernameMailError();

        void setUsernameError();

        void setError(String error);
    }

    interface Presenter {
        void checkData(User user);

        void onDestroy();
    }

    interface Interactor {

        void downloadData(onRegisterFinished finished, Context context, String url);

        interface onRegisterFinished {

            void onSuccess(String json);

            void onFailed(String success);
        }

    }

}
