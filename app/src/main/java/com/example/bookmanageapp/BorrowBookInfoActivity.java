package com.example.bookmanageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.bookmanageapp.featureclass.BookItem;
import com.example.bookmanageapp.utils.ConstantValue;
import com.example.bookmanageapp.utils.UseLog;

public class BorrowBookInfoActivity extends BasementActivity {

    private EditText mTitleEdit;
    private EditText mAuthorEdit;
    private EditText mPublisherEdit;
    private EditText mYearEdit;
    private EditText mRentFeeEdit;
    private EditText mOwnerIDEdit;
    private Button mSubmitBtn;

    private BookItem mBook;
    private int mBookID;


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
        mOwnerIDEdit = findViewById(R.id.edit_book_info_owner_id);
        mSubmitBtn = findViewById(R.id.btn_book_info_submit);

        mTitleEdit.setEnabled(false);
        mAuthorEdit.setEnabled(false);
        mPublisherEdit.setEnabled(false);
        mYearEdit.setEnabled(false);
        mRentFeeEdit.setEnabled(false);
        mOwnerIDEdit.setEnabled(false);

        // enabled elements
        findViewById(R.id.tv_book_info_owner_id).setVisibility(View.VISIBLE);
        findViewById(R.id.edit_book_info_owner_id).setVisibility(View.VISIBLE);

        // disabled unnecessary elements
        findViewById(R.id.tv_book_info_book_status).setVisibility(View.GONE);
        findViewById(R.id.rg_book_info_book_status).setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        UseLog.i("onResume");
        if (getIntent() != null && getIntent().getParcelableExtra(ConstantValue.BOOK_ITEM_INTENT_DATA) != null) {
            mBook = getIntent().getParcelableExtra(ConstantValue.BOOK_ITEM_INTENT_DATA);
        } else {
            UseLog.i("onResume book data is wrong");
            finish();
        }

        mBookID = mBook.getBookID();

        mTitleEdit.setText(mBook.getTitle());
        mAuthorEdit.setText(mBook.getAuthor());
        mPublisherEdit.setText(mBook.getPublisher());
        mYearEdit.setText(mBook.getPublishYear());
        mOwnerIDEdit.setText("" + mBookID);




        mSubmitBtn.setText(getBaseContext().getResources().getString(R.string.book_info_share_book));


        super.onResume();
    }
}