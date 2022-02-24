package com.example.bookmanageapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.bookmanageapp.featureclass.UserAccount;
import com.example.bookmanageapp.utils.ConstantValue;
import com.example.bookmanageapp.utils.UseLog;
import com.example.bookmanageapp.view.MainViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends BasementActivity {

    ViewPager mPager;
    TabLayout mTabLayout;

    private boolean mLoginActivityIsRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.main_activity_pager);
        mTabLayout = findViewById(R.id.main_activity_pager_tab_layout);

        MainViewPagerAdapter mPagerAdapter = new MainViewPagerAdapter(getBaseContext());
        mPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UseLog.i("onResume()");
        if (getUserAccount().isLogin(getBaseContext())) {
            mLoginActivityIsRunning = false;
        } else {
            if (!mLoginActivityIsRunning) {
                mStartForResult.launch(new Intent(this, LogInActivity.class));
                mLoginActivityIsRunning = true;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        UseLog.i("onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        UseLog.i("onStop()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        UseLog.i("onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UseLog.i("onDestroy()");
        getUserAccount().tryLogout(getBaseContext());
    }

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case Activity.RESULT_OK:
                        UseLog.i("Activity.RESULT_OK");
                        break;
                    case Activity.RESULT_CANCELED:
                        UseLog.i("Activity.RESULT_CANCELED");
                        mLoginActivityIsRunning = true;
                        finish();
                        break;
                    default:
                        break;
                }
            }
    );


}