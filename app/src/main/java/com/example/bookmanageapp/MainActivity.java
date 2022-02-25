package com.example.bookmanageapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.example.bookmanageapp.database.DBHelper;
import com.example.bookmanageapp.database.DBQuery;
import com.example.bookmanageapp.featureclass.BookItem;
import com.example.bookmanageapp.featureclass.UserMessages;
import com.example.bookmanageapp.featureclass.ReadingHistory;
import com.example.bookmanageapp.featureclass.UserAccount;
import com.example.bookmanageapp.utils.ConstantValue;
import com.example.bookmanageapp.utils.UseLog;
import com.example.bookmanageapp.view.MainViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends BasementActivity {

    private ViewPager mPager;
    private TabLayout mTabLayout;
    private DBHelper mDbHelper;

    private boolean mLoginActivityIsRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.main_activity_pager);
        mTabLayout = findViewById(R.id.main_activity_pager_tab_layout);

        mDbHelper = new DBHelper(getBaseContext());

        // temp code
//        insertDummyData();

        // code for Database debug
        DebugDB.getAddressLog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UseLog.i("onResume()");
        if (getUserAccount().isLogin(getBaseContext())) {
            mLoginActivityIsRunning = false;

            ArrayList<BookItem> ownBookList = DBQuery.findUserOwnBookList(mDbHelper, getUserAccount());
            ArrayList<BookItem> borrowBookList = DBQuery.findBorrowBookList(mDbHelper, getUserAccount());
            ArrayList<BookItem> readingBookLIst = DBQuery.findReadingBookList(mDbHelper, getUserAccount());

            MainViewPagerAdapter mPagerAdapter =
                    new MainViewPagerAdapter(getBaseContext(), ownBookList, borrowBookList, readingBookLIst);
            mPager.setAdapter(mPagerAdapter);
            mTabLayout.setupWithViewPager(mPager);
        } else {
            if (!mLoginActivityIsRunning) {
                mStartForResult.launch(new Intent(this, LogInActivity.class));
                mLoginActivityIsRunning = true;
            }
        }
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
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.toast_succeed_to_login), Toast.LENGTH_LONG).show();
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