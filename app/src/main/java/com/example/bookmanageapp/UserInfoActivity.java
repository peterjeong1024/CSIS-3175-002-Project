package com.example.bookmanageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookmanageapp.featureclass.UserAccount;
import com.example.bookmanageapp.utils.ConstantValue;
import com.example.bookmanageapp.utils.UseLog;

public class UserInfoActivity extends BasementActivity {

    private EditText mUserIdEdit;
    private EditText mUserPwEdit;
    private EditText mUserNameEdit;
    private EditText mUserAgeEdit;
    private EditText mUserAddrEdit;
    private EditText mUserGenreEdit;
    private Button mSubmitBtn;
    private Button mCheckIDBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        if (!getUserAccount().isLogin(getBaseContext())) {
            UseLog.i("logout status");
            // send it to login activity
        }
        ;

        mUserIdEdit = findViewById(R.id.edit_user_info_id);
        mUserPwEdit = findViewById(R.id.edit_user_info_pw);
        mUserNameEdit = findViewById(R.id.edit_user_info_name);
        mUserAgeEdit = findViewById(R.id.edit_user_info_age);
        mUserAddrEdit = findViewById(R.id.edit_user_info_addr);
        mUserGenreEdit = findViewById(R.id.edit_user_info_genre);
        mSubmitBtn = findViewById(R.id.btn_user_info_submit);
        mCheckIDBtn = findViewById(R.id.btn_edit_user_info_id_check);

        mSubmitBtn.setOnClickListener(mSubmitBtnClickListener);
        mCheckIDBtn.setOnClickListener(mCheckBtnClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // layout redraw
        if (getUserAccount().isLogin(getBaseContext())) {
            // update user info
            mCheckIDBtn.setVisibility(View.GONE);

            UserAccount ua = getUserAccount();
            mUserIdEdit.setText(ua.getId());
            mUserPwEdit.setText(ua.getPassword());

            // need to contact DB

//            mUserNameEdit.setText(ua.getName());
//            mUserAgeEdit.setText(String.valueOf(ua.getAge()));
//            mUserAddrEdit.setText(ua.getAddress());
//            mUserGenreEdit.setText(ua.getGenre());
        }
    }

    View.OnClickListener mSubmitBtnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            UseLog.i("onClick");

        }
    };

    View.OnClickListener mCheckBtnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            UseLog.i("onClick");

        }
    };
}