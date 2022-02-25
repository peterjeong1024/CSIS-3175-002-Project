package com.example.bookmanageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookmanageapp.database.DBHelper;
import com.example.bookmanageapp.database.DBQuery;
import com.example.bookmanageapp.featureclass.BookItem;
import com.example.bookmanageapp.featureclass.UserAccount;
import com.example.bookmanageapp.featureclass.UserMessages;
import com.example.bookmanageapp.utils.ConstantValue;

import java.util.ArrayList;

public class AdminActivity extends BasementActivity {

    private DBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mDbHelper = new DBHelper(getBaseContext());

        Button createDemoDataBtn = findViewById(R.id.btn_create_demo_data);
        createDemoDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDummyData();
                Toast.makeText(getBaseContext(), getBaseContext().getResources().getString(R.string.toast_succeed_to_create_demo_data), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // temp code
    private void insertDummyData() {
        ArrayList<UserAccount> templist1 = createDemoUserData();
        for (UserAccount m : templist1) {
            DBQuery.insertUserToUSERS(mDbHelper, m);
        }

        ArrayList<BookItem> templist2 = createDemoBookData();
        for (BookItem bi : templist2) {
            DBQuery.insertBookInfoToBOOK(mDbHelper, bi);
        }

        ArrayList<UserMessages> templist4 = createDemoMessageData();
        for (UserMessages m : templist4) {
            DBQuery.insertMessagesToMSG(mDbHelper, m);
        }
    }

    private ArrayList<UserAccount> createDemoUserData() {
        ArrayList<UserAccount> demoData = new ArrayList();
        demoData.add(new UserAccount("user1", "test", "John", 33, "New Westminster", "Computer"));
        demoData.add(new UserAccount("user2", "test", "Sam", 45, "New Westminster", "Computer"));
        demoData.add(new UserAccount("user3", "test", "Tina", 22, "New Westminster", "Computer"));
        return demoData;
    }

    private ArrayList<BookItem> createDemoBookData() {
        ArrayList<BookItem> demoData = new ArrayList();

        demoData.add(new BookItem(1, "Book1", "John", "MG", "2022", "user1", "user1", ConstantValue.BOOK_STATUS_SHARE, (float) 1, true));
        demoData.add(new BookItem(1, "Book2", "John", "MG", "2022", "user1", "user2", ConstantValue.BOOK_STATUS_SHARE, (float) 1, false));
        demoData.add(new BookItem(1, "Book3", "John", "MG", "2022", "user1", "user2", ConstantValue.BOOK_STATUS_SHARE, (float) 1, true));
        demoData.add(new BookItem(1, "Book4", "John", "MG", "2022", "user1", "user3", ConstantValue.BOOK_STATUS_SHARE, (float) 1, false));
        demoData.add(new BookItem(1, "Book5", "John", "MG", "2022", "user1", "user3", ConstantValue.BOOK_STATUS_SHARE, (float) 1, true));
        demoData.add(new BookItem(1, "Book6", "John", "MG", "2022", "user2", "user2", ConstantValue.BOOK_STATUS_SHARE, (float) 1, false));
        demoData.add(new BookItem(1, "Book7", "John", "MG", "2022", "user2", "user1", ConstantValue.BOOK_STATUS_SHARE, (float) 1, false));
        demoData.add(new BookItem(1, "Book8", "John", "MG", "2022", "user2", "user1", ConstantValue.BOOK_STATUS_SHARE, (float) 1, true));
        demoData.add(new BookItem(1, "Book9", "John", "MG", "2022", "user2", "user3", ConstantValue.BOOK_STATUS_SHARE, (float) 1, false));
        demoData.add(new BookItem(1, "Book10", "John", "MG", "2022", "user3", "user1", ConstantValue.BOOK_STATUS_SHARE, (float) 1, true));
        demoData.add(new BookItem(1, "Book11", "John", "MG", "2022", "user3", "user2", ConstantValue.BOOK_STATUS_SHARE, (float) 1, false));
        demoData.add(new BookItem(1, "Book12", "John", "MG", "2022", "user3", "user3", ConstantValue.BOOK_STATUS_SHARE, (float) 1, true));

        return demoData;
    }

    private ArrayList<UserMessages> createDemoMessageData() {
        ArrayList<UserMessages> demoData = new ArrayList();
        demoData.add(new UserMessages("user1", "user2", "hello"));
        demoData.add(new UserMessages("user1", "user3", "hi"));
        demoData.add(new UserMessages("user1", "user3", "good bye"));

        demoData.add(new UserMessages("user2", "user1", "hello all"));
        demoData.add(new UserMessages("user2", "user1", "hi all"));
        demoData.add(new UserMessages("user2", "user3", "good bye all"));

        demoData.add(new UserMessages("user3", "user2", "hello guys"));
        demoData.add(new UserMessages("user3", "user3", "hi guys"));
        demoData.add(new UserMessages("user3", "user2", "good bye guys"));


        return demoData;
    }


    // temp code
}