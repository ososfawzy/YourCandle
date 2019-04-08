package com.yourcandle.yourcandle.Activities.Admin.AddAdmin;

import android.content.Context;

import com.yourcandle.yourcandle.Models.User;

/**
 * Created by new on 6/2/2018.
 */

public interface AddAdminContract {
    interface view {
        void showProgress();

        void hideProgress();

        void setSuccess();

        void setEmailError();

        void setUsernameMailError();

        void setUsernameError();

        void setError();

    }
    interface Presenter {
        void checkData(User user);

        void destroy();
    }

    interface Interactor {
        void DownloadData(onFinishedAdd onFinishedAdd, Context context, String url);

        interface onFinishedAdd {
            void onSuccess(String json);

            void onFailed(String success);
        }

    }



}
