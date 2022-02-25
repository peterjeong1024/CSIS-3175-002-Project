package com.example.bookmanageapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanageapp.featureclass.BookItem;
import com.example.bookmanageapp.featureclass.UserMessages;
import com.example.bookmanageapp.featureclass.UserAccount;
import com.example.bookmanageapp.utils.UseLog;

import java.util.ArrayList;

public class DBQuery {

    /*
        define table name and column name
        table : USERS, BOOK, MESSAGES
     */

    public static class USERS {
        public static final String TABLE_NAME = "USERS";
        public static final String COLUMN_NAME_USERID = "UserID";
        public static final String COLUMN_NAME_USERPW = "UserPW";
        public static final String COLUMN_NAME_USERNAME = "UserName";
        public static final String COLUMN_NAME_USERAGE = "UserAge";
        public static final String COLUMN_NAME_USERADDR = "UserAddr";
        public static final String COLUMN_NAME_GENRE = "Genre";
    }

    public static class BOOK {
        public static final String TABLE_NAME = "BOOK";
        public static final String COLUMN_NAME_BOOKID = "BookID";
        public static final String COLUMN_NAME_TITLE = "Title";
        public static final String COLUMN_NAME_AUTHOR = "Author";
        public static final String COLUMN_NAME_PUBLISHER = "Publisher";
        public static final String COLUMN_NAME_YEAR = "PublishYear";
        public static final String COLUMN_NAME_OWNERID = "OwnerID";
        public static final String COLUMN_NAME_RENTERID = "RenterID";
        public static final String COLUMN_NAME_STATUS = "Status";
        public static final String COLUMN_NAME_RENTFEE = "RentFee";
        public static final String COLUMN_NAME_ISREAD = "IsRead";
    }

    public static class MESSAGES {
        public static final String TABLE_NAME = "MESSAGES";
        public static final String COLUMN_NAME_USERID = "UserID";
        public static final String COLUMN_NAME_SENDERID = "SenderID";
        public static final String COLUMN_NAME_SENTMSGTEXT = "SentMsgText";
    }


    public static final String SQL_CREATE_USERS_ENTRIES =
            "CREATE TABLE " + USERS.TABLE_NAME + " (" +
                    USERS.COLUMN_NAME_USERID + " TEXT PRIMARY KEY," +
                    USERS.COLUMN_NAME_USERPW + " TEXT," +
                    USERS.COLUMN_NAME_USERNAME + " TEXT," +
                    USERS.COLUMN_NAME_USERAGE + " INTEGER," +
                    USERS.COLUMN_NAME_USERADDR + " TEXT," +
                    USERS.COLUMN_NAME_GENRE + " TEXT)";

    public static final String SQL_CREATE_BOOK_ENTRIES =
            "CREATE TABLE " + BOOK.TABLE_NAME + " (" +
                    BOOK.COLUMN_NAME_BOOKID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    BOOK.COLUMN_NAME_TITLE + " TEXT," +
                    BOOK.COLUMN_NAME_AUTHOR + " TEXT," +
                    BOOK.COLUMN_NAME_PUBLISHER + " TEXT," +
                    BOOK.COLUMN_NAME_YEAR + " TEXT, " +
                    BOOK.COLUMN_NAME_OWNERID + " TEXT," +
                    BOOK.COLUMN_NAME_RENTERID + " TEXT," +
                    BOOK.COLUMN_NAME_STATUS + " TEXT," +
                    BOOK.COLUMN_NAME_RENTFEE + " NUMERIC(4,2), " +
                    BOOK.COLUMN_NAME_ISREAD + " BOOLEAN)";

    public static final String SQL_CREATE_MESSAGES_ENTRIES =
            "CREATE TABLE " + MESSAGES.TABLE_NAME + " (" +
                    MESSAGES.COLUMN_NAME_USERID + " TEXT," +
                    MESSAGES.COLUMN_NAME_SENDERID + " TEXT," +
                    MESSAGES.COLUMN_NAME_SENTMSGTEXT + " TEXT)";


    public static final String SQL_DELETE_USERS_ENTRIES =
            "DROP TABLE IF EXISTS " + USERS.TABLE_NAME;

    public static final String SQL_DELETE_BOOK_ENTRIES =
            "DROP TABLE IF EXISTS " + BOOK.TABLE_NAME;

    public static final String SQL_DELETE_MESSAGES_ENTRIES =
            "DROP TABLE IF EXISTS " + MESSAGES.TABLE_NAME;



    /*
        design the query methods
     */

    public static long insertUserToUSERS(DBHelper dbHelper, UserAccount ua) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERS.COLUMN_NAME_USERID, ua.getId());
        values.put(USERS.COLUMN_NAME_USERPW, ua.getPassword());
        values.put(USERS.COLUMN_NAME_USERNAME, ua.getName());
        values.put(USERS.COLUMN_NAME_USERAGE, ua.getAge());
        values.put(USERS.COLUMN_NAME_USERADDR, ua.getAddress());
        values.put(USERS.COLUMN_NAME_GENRE, ua.getGenre());

        return db.insert(USERS.TABLE_NAME, null, values);
    }

    public static UserAccount findUserInUSERS(DBHelper dbHelper, UserAccount ua) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = USERS.COLUMN_NAME_USERID + " = ? AND " + USERS.COLUMN_NAME_USERPW + " = ?";
        String[] selectionArgs = {ua.getId(), ua.getPassword()};

        Cursor cursor = db.query(
                USERS.TABLE_NAME,   // The table to query
                null,           // The array of columns to return (pass null to get all) = SELECT
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,           // groupBy
                null,            // having
                null            // orderBy
        );

        if (cursor != null && cursor.moveToNext()) {
            ua.setId(cursor.getString(cursor.getColumnIndexOrThrow(USERS.COLUMN_NAME_USERID)));
            ua.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(USERS.COLUMN_NAME_USERPW)));
            ua.setName(cursor.getString(cursor.getColumnIndexOrThrow(USERS.COLUMN_NAME_USERNAME)));
            ua.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(USERS.COLUMN_NAME_USERAGE)));
            ua.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(USERS.COLUMN_NAME_USERADDR)));
            ua.setGenre(cursor.getString(cursor.getColumnIndexOrThrow(USERS.COLUMN_NAME_GENRE)));
            cursor.close();
        } else {
            UseLog.i("cursor is wrong");
            cursor.close();
            return null;
        }

        return ua;
    }

    public static long insertBookInfoToBOOK(DBHelper dbHelper, BookItem bi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BOOK.COLUMN_NAME_TITLE, bi.getTitle());
        values.put(BOOK.COLUMN_NAME_AUTHOR, bi.getAuthor());
        values.put(BOOK.COLUMN_NAME_PUBLISHER, bi.getPublisher());
        values.put(BOOK.COLUMN_NAME_YEAR, bi.getPublishYear());
        values.put(BOOK.COLUMN_NAME_OWNERID, bi.getOwnerID());
        values.put(BOOK.COLUMN_NAME_RENTERID, bi.getRenterID());
        values.put(BOOK.COLUMN_NAME_STATUS, bi.getStatus());
        values.put(BOOK.COLUMN_NAME_RENTFEE, bi.getRentFee());
        values.put(BOOK.COLUMN_NAME_ISREAD, bi.isRead());

        return db.insert(BOOK.TABLE_NAME, null, values);
    }

    public static ArrayList<BookItem> findUserOwnBookList(DBHelper dbHelper, UserAccount ua) {
        ArrayList<BookItem> ownBookList = new ArrayList<BookItem>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = BOOK.COLUMN_NAME_OWNERID + " = ?";
        String[] selectionArgs = {ua.getId()};

        Cursor cursor = db.query(
                BOOK.TABLE_NAME,   // The table to query
                null,           // The array of columns to return (pass null to get all) = SELECT
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,           // groupBy
                null,            // having
                null            // orderBy
        );

        if (cursor == null) {
            UseLog.i("cursor is wrong");
            cursor.close();
            return null;
        } else {
            while (cursor.moveToNext()) {
                BookItem bi = new BookItem();
                bi.setBookID(cursor.getInt(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_BOOKID)));
                bi.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_TITLE)));
                bi.setAuthor(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_AUTHOR)));
                bi.setPublisher(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_PUBLISHER)));
                bi.setPublishYear(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_YEAR)));
                bi.setOwnerID(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_OWNERID)));
                bi.setRenterID(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_RENTERID)));
                bi.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_STATUS)));
                bi.setRentFee(cursor.getFloat(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_RENTFEE)));
                bi.setRead(cursor.getInt(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_ISREAD)) == 1);

                ownBookList.add(bi);
            }
            cursor.close();
        }

        return ownBookList;
    }

    public static ArrayList<BookItem> findBorrowBookList(DBHelper dbHelper, UserAccount ua) {
        ArrayList<BookItem> ownBookList = new ArrayList<BookItem>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = BOOK.COLUMN_NAME_OWNERID + " != ? AND " + BOOK.COLUMN_NAME_RENTERID + " != ?";
        String[] selectionArgs = {ua.getId(), ua.getId()};

        Cursor cursor = db.query(
                BOOK.TABLE_NAME,   // The table to query
                null,           // The array of columns to return (pass null to get all) = SELECT
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,           // groupBy
                null,            // having
                null            // orderBy
        );

        if (cursor == null) {
            UseLog.i("cursor is wrong");
            cursor.close();
            return null;
        } else {
            while (cursor.moveToNext()) {
                BookItem bi = new BookItem();
                bi.setBookID(cursor.getInt(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_BOOKID)));
                bi.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_TITLE)));
                bi.setAuthor(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_AUTHOR)));
                bi.setPublisher(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_PUBLISHER)));
                bi.setPublishYear(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_YEAR)));
                bi.setOwnerID(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_OWNERID)));
                bi.setRenterID(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_RENTERID)));
                bi.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_STATUS)));
                bi.setRentFee(cursor.getFloat(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_RENTFEE)));
                bi.setRead(cursor.getInt(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_ISREAD)) == 1);

                ownBookList.add(bi);
            }
            cursor.close();
        }

        return ownBookList;
    }

    public static ArrayList<BookItem> findReadingBookList(DBHelper dbHelper, UserAccount ua) {
        ArrayList<BookItem> ownBookList = new ArrayList<BookItem>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = BOOK.COLUMN_NAME_RENTERID + " = ?";
        String[] selectionArgs = {ua.getId()};

        Cursor cursor = db.query(
                BOOK.TABLE_NAME,   // The table to query
                null,           // The array of columns to return (pass null to get all) = SELECT
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,           // groupBy
                null,            // having
                null            // orderBy
        );

        if (cursor == null) {
            UseLog.i("cursor is wrong");
            cursor.close();
            return null;
        } else {
            while (cursor.moveToNext()) {
                BookItem bi = new BookItem();
                bi.setBookID(cursor.getInt(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_BOOKID)));
                bi.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_TITLE)));
                bi.setAuthor(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_AUTHOR)));
                bi.setPublisher(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_PUBLISHER)));
                bi.setPublishYear(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_YEAR)));
                bi.setOwnerID(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_OWNERID)));
                bi.setRenterID(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_RENTERID)));
                bi.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_STATUS)));
                bi.setRentFee(cursor.getFloat(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_RENTFEE)));
                bi.setRead(cursor.getInt(cursor.getColumnIndexOrThrow(BOOK.COLUMN_NAME_ISREAD)) == 1);

                ownBookList.add(bi);
            }
            cursor.close();
        }

        return ownBookList;
    }

    public static long insertMessagesToMSG(DBHelper dbHelper, UserMessages m) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MESSAGES.COLUMN_NAME_USERID, m.getUserID());
        values.put(MESSAGES.COLUMN_NAME_SENDERID, m.getSenderID());
        values.put(MESSAGES.COLUMN_NAME_SENTMSGTEXT, m.getSentMsgTxt());

        return db.insert(MESSAGES.TABLE_NAME, null, values);
    }

    public static ArrayList<UserMessages> findUserMsgList(DBHelper dbHelper, UserAccount ua) {
        ArrayList<UserMessages> msgList = new ArrayList<UserMessages>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = MESSAGES.COLUMN_NAME_USERID + " = ?";
        String[] selectionArgs = {ua.getId()};

        Cursor cursor = db.query(
                MESSAGES.TABLE_NAME,   // The table to query
                null,           // The array of columns to return (pass null to get all) = SELECT
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,           // groupBy
                null,            // having
                null            // orderBy
        );

        if (cursor == null) {
            UseLog.i("cursor is wrong");
            cursor.close();
            return null;
        } else {
            while (cursor.moveToNext()) {
                UserMessages um = new UserMessages();
                um.setUserID(cursor.getString(cursor.getColumnIndexOrThrow(MESSAGES.COLUMN_NAME_USERID)));
                um.setSenderID(cursor.getString(cursor.getColumnIndexOrThrow(MESSAGES.COLUMN_NAME_SENDERID)));
                um.setSentMsgTxt(cursor.getString(cursor.getColumnIndexOrThrow(MESSAGES.COLUMN_NAME_SENTMSGTEXT)));
                msgList.add(um);
            }
            cursor.close();
        }

        return msgList;
    }


}
