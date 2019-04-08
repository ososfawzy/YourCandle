package com.yourcandle.yourcandle.Activities.Admin.Users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yourcandle.yourcandle.Models.User;
import com.yourcandle.yourcandle.R;

import java.util.ArrayList;

/**
 * Created by new on 6/2/2018.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    private ArrayList<User> userList;
    private SendUser sendUser;

    public UsersListAdapter(SendUser sendUser) {
        userList = new ArrayList<>();
        this.sendUser = sendUser;
    }

    public void setUserList(ArrayList<User> users) {
        this.userList = users;
        notifyDataSetChanged();
    }

    public interface SendUser{
        void getOneUser(User user);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.userMail.setText(userList.get(position).getUser_email());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView deleteIcon;
        private TextView userMail;

        public ViewHolder(View itemView) {
            super(itemView);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
            userMail = itemView.findViewById(R.id.userMail);
            deleteIcon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            sendUser.getOneUser(userList.get(getAdapterPosition()));
        }
    }

}
