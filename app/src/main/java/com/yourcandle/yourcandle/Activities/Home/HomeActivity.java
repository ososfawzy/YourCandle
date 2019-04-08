package com.yourcandle.yourcandle.Activities.Home;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;
import com.yourcandle.yourcandle.Activities.Home.AddFeedback.UserFeedbackFragment;
import com.yourcandle.yourcandle.Activities.Login.LoginActivity;
import com.yourcandle.yourcandle.Activities.Profile.ProfileFragment;
import com.yourcandle.yourcandle.R;
import com.yourcandle.yourcandle.Utilities.StoredManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.bottom_bar)
    BottomNavigationBar bottom_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        setButtomBar();
    }

    public void setButtomBar() {
        BottomBarItem home_item = new BottomBarItem(R.drawable.home_icon, R.string.Home);
        bottom_bar.addTab(home_item);
        BottomBarItem profile_item = new BottomBarItem(R.drawable.profile_icon, R.string.Profile);
        bottom_bar.addTab(profile_item);
        BottomBarItem feedback_item = new BottomBarItem(R.drawable.feedback_icon, R.string.Feedback);
        bottom_bar.addTab(feedback_item);
        BottomBarItem help_item = new BottomBarItem(R.drawable.help_icon, R.string.Help);
        bottom_bar.addTab(help_item);

        if (bottom_bar.getSelectedPosition() == 0) {
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.homeUserContainer, homeFragment).commit();
        }

        bottom_bar.setOnSelectListener(new BottomNavigationBar.OnSelectListener() {
            @Override
            public void onSelect(int position) {
                switch (position) {
                    case 0:
                        HomeFragment homeFragment = new HomeFragment();
                        FragmentManager manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.homeUserContainer, homeFragment).commit();
                        break;
                    case 1:
                        ProfileFragment profileFragment = new ProfileFragment();
                        FragmentManager manager1 = getSupportFragmentManager();
                        manager1.beginTransaction().replace(R.id.homeUserContainer, profileFragment).commit();
                        break;
                    case 2:
                        UserFeedbackFragment feedbackFragment = new UserFeedbackFragment();
                        FragmentManager manager2 = getSupportFragmentManager();
                        manager2.beginTransaction().replace(R.id.homeUserContainer, feedbackFragment).commit();
                        break;
                    case 3:
                        HelpFragment helpFragment = new HelpFragment();
                        FragmentManager manager3 = getSupportFragmentManager();
                        manager3.beginTransaction().replace(R.id.homeUserContainer, helpFragment).commit();
                        break;
                    default:
                        HomeFragment homeFragment1 = new HomeFragment();
                        FragmentManager manager4 = getSupportFragmentManager();
                        manager4.beginTransaction().replace(R.id.homeUserContainer, homeFragment1).commit();
                        break;
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.user_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.userLogout:
                Intent intent = new Intent(this, LoginActivity.class);
                StoredManager.getInstance().SaveUser(this, "");
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
