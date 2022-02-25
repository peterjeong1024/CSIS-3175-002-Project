package com.example.bookmanageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.bookmanageapp.featureclass.UserAccount;
import com.example.bookmanageapp.utils.ConstantValue;
import com.example.bookmanageapp.utils.UseLog;

public class BasementActivity extends AppCompatActivity {

    private UserAccount mUserAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserAccount = new UserAccount();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActionBar ab = getSupportActionBar();
        if (!getUserAccount().isLogin(getBaseContext())) {
            ab.setTitle(R.string.need_to_login);
        } else {
            ab.setTitle(getUserAccount().getId());
        }
        invalidateOptionsMenu();
    }

    public UserAccount getUserAccount() {
        return mUserAccount;
    }

    public void setUserAccount(UserAccount mUserAccount) {
        this.mUserAccount = mUserAccount;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        if (getUserAccount().isLogin(getBaseContext())) {
            menuInflater.inflate(R.menu.actionbar_login, menu);
        } else {
            menuInflater.inflate(R.menu.actionbar_logout, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update_user_info:
                // go to update info screen
                if (!getUserAccount().isLogin(getBaseContext())) {
                    UseLog.i("UA is wrong");
                    return false;
                }
                Intent uIntent = new Intent(getBaseContext(), UserInfoActivity.class);
                startActivity(uIntent);
                return true;
            case R.id.action_receive_msg:
                // go to msg list screen
                if (!getUserAccount().isLogin(getBaseContext())) {
                    UseLog.i("UA is wrong");
                    return false;
                }
                Intent mIntent = new Intent(getBaseContext(), MessageActivity.class);
                startActivity(mIntent);
                return true;
            case R.id.action_log_out:
                // log out and go to main screen
                getUserAccount().tryLogout(getBaseContext());
                UseLog.i("action_log_out");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}