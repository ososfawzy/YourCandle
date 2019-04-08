package com.yourcandle.yourcandle.Activities.Admin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yourcandle.yourcandle.Activities.Profile.ProfileFragment;
import com.yourcandle.yourcandle.Activities.Admin.AddAdmin.AddAdminFragment;
import com.yourcandle.yourcandle.Activities.Admin.Feedback.FeedbackFragment;
import com.yourcandle.yourcandle.Activities.Admin.Users.UsersFragment;


public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                UsersFragment userFragment = new UsersFragment();
                return userFragment;
            case 1:
                AddAdminFragment adminFragment = new AddAdminFragment();
                return adminFragment;
            case 2:
                FeedbackFragment feedbackFragment = new FeedbackFragment();
                return feedbackFragment;
            case 3:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
