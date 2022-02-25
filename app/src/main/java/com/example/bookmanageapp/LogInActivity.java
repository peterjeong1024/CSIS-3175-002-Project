package com.example.bookmanageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanageapp.database.DBHelper;
import com.example.bookmanageapp.database.DBQuery;
import com.example.bookmanageapp.featureclass.UserAccount;
import com.example.bookmanageapp.utils.ConstantValue;
import com.example.bookmanageapp.utils.UseLog;

public class LogInActivity extends BasementActivity {

    private EditText mIdText;
    private EditText mPwText;
    private Button mSignInBtn;
    private Button mSignUpBtn;

    private DBHelper mDbHelper;
    private int mClickCount;

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

        findViewById(R.id.layout_hidden_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickCount > 0) {
                    Toast.makeText(getBaseContext(), mClickCount + " " + getBaseContext().getResources().getString(R.string.toast_clicks_left), Toast.LENGTH_SHORT).show();
                    mClickCount--;
                } else {
                    Intent uIntent = new Intent(getBaseContext(), AdminActivity.class);
                    startActivity(uIntent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mClickCount = 5;
    }

    View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_login_sign_in) {
                // run login code
                mDbHelper = new DBHelper(getBaseContext());
                UserAccount loginUA = DBQuery.findUserInUSERS(mDbHelper, new UserAccount(mIdText.getText().toString(), mPwText.getText().toString()));
                if (loginUA == null) {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.toast_cannot_find_id), Toast.LENGTH_LONG).show();
                } else {
                    setUserAccount(loginUA);
                    getUserAccount().tryLogin(getBaseContext());
                    setResult(Activity.RESULT_OK);
                    finish();
                }
            } else {
                // go to sign up activity
                Intent intent = new Intent(getBaseContext(), UserInfoActivity.class);
                startActivity(intent);
            }
        }
    };
}
