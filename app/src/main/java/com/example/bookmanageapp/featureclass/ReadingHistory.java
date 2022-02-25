package com.example.bookmanageapp.featureclass;

public class ReadingHistory {
    private String userID;
    private int bookID;
    private boolean isRead;

    public ReadingHistory(String userID, int bookID, boolean isRead) {
        this.userID = userID;
        this.bookID = bookID;
        this.isRead = isRead;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
