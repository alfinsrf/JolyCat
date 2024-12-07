package com.alfin.jolycat;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class User implements Parcelable {
    private String userID;
    private String username;
    private String password;
    private String phoneNumber;

    public User(String userID, String username, String password, String phoneNumber) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    protected User(Parcel in) {
        userID = in.readString();
        username = in.readString();
        password = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(userID);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(phoneNumber);
    }
}
