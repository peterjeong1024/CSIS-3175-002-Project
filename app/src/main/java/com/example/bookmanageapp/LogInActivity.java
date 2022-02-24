package com.example.bookmanageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookmanageapp.featureclass.UserAccount;
import com.example.bookmanageapp.utils.ConstantValue;
import com.example.bookmanageapp.utils.UseLog;

public class LogInActivity extends BasementActivity {

    private EditText mIdText;
    private EditText mPwText;
    private Button mSignInBtn;
    private Button mSignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mIdText = findViewById(R.id.edit_login_id);
        mPwText = findViewById(R.id.edit_login_pw);
        mSignInBtn = findViewById(R.id.btn_login_sign_in);
        mSignUpBtn = findViewById(R.id.btn_login_sign_up);

        mSignInBtn.setOnClickListener(btnClickListener);
        mSignUpBtn.setOnClickListener(btnClickListener);
    }

    View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_login_sign_in) {
                // run login code

                // temp code
                UserAccount userAccount = new UserAccount("sampleID", "test", "John", 33, "New Westminster", "Computer");
                setUserAccount(userAccount);
                getUserAccount().tryLogin(getBaseContext());
                setResult(Activity.RESULT_OK);
                finish();
                // temp code

            } else {
                // go to sign up activity
                Intent intent = new Intent(getBaseContext(), UserInfoActivity.class);
                startActivity(intent);
            }
        }
    };

//    @Override
//    public void onBackPressed() {
//        UseLog.i("onBackPressed");
////        super.onBackPressed();
////        Intent intent = new Intent();
////        intent.putExtra(ConstantValue.LOGIN_ACTIVITY_INTENT, false);
//        setResult(ConstantValue.LOGIN_ACTIVITY_RESULT_VALUE);
//        super.onBackPressed();
//    }
}
