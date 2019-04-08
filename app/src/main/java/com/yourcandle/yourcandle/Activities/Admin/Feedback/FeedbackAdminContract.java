package com.yourcandle.yourcandle.Activities.Admin.Feedback;

import android.content.Context;


import com.yourcandle.yourcandle.Models.Feedback;


import java.util.ArrayList;

public interface FeedbackAdminContract {

    interface view {
        void showProgress();

        void hideProgress();

        void setFeedbackList(ArrayList<Feedback> feedbackList);

        void setError();

        void setDeleteSuccess();

        void setDeleteFailed();
    }

    interface Presenter {
        void checkData();

        void onDestroy();

        void DeleteFeedback(String feedback_id);
    }


    interface Interactor {
        void DownloadData(onListFinished listFinished, Context context, String url);

        interface onListFinished {
            void onSuccess(String json);

            void onFailed();
        }

        void DeleteData(onDeleteFinished deleteFinished, Context context, String url);

        interface onDeleteFinished {
            void onDeleteSuccess();

            void onDeleteFailed();
        }

    }
}
