package com.example.bookmanageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.bookmanageapp.featureclass.BookItem;
import com.example.bookmanageapp.featureclass.UserAccount;
import com.example.bookmanageapp.utils.ConstantValue;
import com.example.bookmanageapp.utils.UseLog;

public class BookInfoActivity extends BasementActivity {

    private EditText mTitleEdit;
    private EditText mAuthorEdit;
    private EditText mPublisherEdit;
    private EditText mYearEdit;
    private RadioGroup mBookStatusRG;
    private EditText mRentFeeEdit;
    private Button mSubmitBtn;

    private BookItem mBook;
    private int mBookID;

    private boolean mIsAddBookActivity = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UseLog.i("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        if (!getUserAccount().isLogin(getBaseContext())) {
            UseLog.i("logout status");
            // send it to login activity
        };

        mTitleEdit = findViewById(R.id.edit_book_info_title);
        mAuthorEdit = findViewById(R.id.edit_book_info_author);
        mPublisherEdit = findViewById(R.id.edit_book_info_publisher);
        mYearEdit = findViewById(R.id.edit_book_info_year);
        mRentFeeEdit = findViewById(R.id.edit_book_info_rent_fee);

        mBookStatusRG = findViewById(R.id.rg_book_info_book_status);
        mSubmitBtn = findViewById(R.id.btn_book_info_submit);
    }

    @Override
    protected void onResume() {
        UseLog.i("onResume");
        if (getIntent() != null && getIntent().getParcelableExtra(ConstantValue.BOOK_ITEM_INTENT_DATA) != null) {
            mBook = getIntent().getParcelableExtra(ConstantValue.BOOK_ITEM_INTENT_DATA);
            mIsAddBookActivity = false;

            // update book data to activity
            mBookID = mBook.getBookID();
            mTitleEdit.setText(mBook.getTitle());
            mAuthorEdit.setText(mBook.getAuthor());
            mPublisherEdit.setText(mBook.getPublisher());
            mYearEdit.setText(mBook.getPublishYear());

            mRentFeeEdit.setText("" + mBookID);
            mSubmitBtn.setText(getBaseContext().getResources().getString(R.string.book_info_update_book));

        } else {
            mIsAddBookActivity = true;
            mSubmitBtn.setText(getBaseContext().getResources().getString(R.string.book_info_add_book));
        }




        super.onResume();
    }
}