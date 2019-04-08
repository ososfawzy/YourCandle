package com.yourcandle.yourcandle.Activities.Admin.Users;

import android.content.Context;

import com.yourcandle.yourcandle.Models.User;

import java.util.ArrayList;

/**
 * Created by new on 6/2/2018.
 */

public interface UsersContract {
    interface view {
        void showProgress();

        void hideProgress();

        void setUserList(ArrayList<User> userList);

        void setBlockedSuccessflly();

        void setBlockedFailed();

        void setError();

    }

    interface Presenter {
        void checkData();

        void onDestroy();

        void blockUser(String id);
    }

    interface Interactor {
        void DownloadData(onListFinished listFinished, Context context, String url);

        interface onListFinished {
            void onSuccess(String json);

            void onFailed();
        }

        void Blocked(onBlockedFinished blockedFinished, Context context, String url);

        interface onBlockedFinished {
            void onBlockSuccess();

            void onBlockFailed();
        }

    }
}
