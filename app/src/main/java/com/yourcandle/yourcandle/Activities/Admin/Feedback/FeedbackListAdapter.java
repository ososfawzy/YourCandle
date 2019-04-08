package com.yourcandle.yourcandle.Activities.Admin.Feedback;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yourcandle.yourcandle.Models.Feedback;
import com.yourcandle.yourcandle.R;

import java.util.ArrayList;

public class FeedbackListAdapter extends RecyclerView.Adapter<FeedbackListAdapter.ViewHolder> {

    private ArrayList<Feedback> feedbackList;

    public FeedbackListAdapter() {
        feedbackList = new ArrayList<>();
    }

    public void setFeedbackList(ArrayList<Feedback> Feedbacks) {
        this.feedbackList = Feedbacks;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.feedbackIssue.setText(feedbackList.get(position).getFeedback_issue());
        holder.itemView.setTag(feedbackList.get(position));
    }


    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView deleteIcon;
        private TextView feedbackIssue;

        public ViewHolder(View itemView) {
            super(itemView);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
            feedbackIssue = itemView.findViewById(R.id.feedbackIssue);
        }
    }
}
