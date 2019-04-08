package com.yourcandle.yourcandle.Activities.Admin.Feedback;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.wang.avi.AVLoadingIndicatorView;
import com.yourcandle.yourcandle.Models.Feedback;
import com.yourcandle.yourcandle.R;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;


public class FeedbackFragment extends Fragment implements FeedbackAdminContract.view {

    @BindView(R.id.feedbackRecycler)
    RecyclerView feedbackRecycler;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;

    private feedbackAdminPresenter presenter;
    private FeedbackListAdapter adapter;

    public FeedbackFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        ButterKnife.bind(this, view);

        setRecycler();

        presenter = new feedbackAdminPresenter(this, new FeedbackAdminInteractor(), getActivity());
        presenter.checkData();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Feedback feedback = new Feedback();
                feedback = (Feedback) viewHolder.itemView.getTag();
                presenter.DeleteFeedback(feedback.getFeedback_id());
            }
        };
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(feedbackRecycler);
        return view;
    }
    public void setRecycler() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        feedbackRecycler.setLayoutManager(manager);
        adapter = new FeedbackListAdapter();
        feedbackRecycler.setAdapter(adapter);
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
    public void setFeedbackList(ArrayList<Feedback> feedbackList) {
        adapter.setFeedbackList(feedbackList);
    }

    @Override
    public void setError() {
        Toasty.error(getActivity(),getResources().getString(R.string.error),Toast.LENGTH_LONG,true).show();
    }

    @Override
    public void setDeleteSuccess() {
        Toasty.success(getActivity(),getResources().getString(R.string.success),Toast.LENGTH_LONG,true).show();

    }

    @Override
    public void setDeleteFailed() {
        Toast.makeText(getActivity(), "Error in Deletion process", Toast.LENGTH_SHORT).show();
    }
}
