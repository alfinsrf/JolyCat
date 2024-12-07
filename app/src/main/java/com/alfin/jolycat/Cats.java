package com.alfin.jolycat;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Cats {
    private String catID;
    private String catName;
    private String catDescription;
    private String catImage;
    private int catPrice;

    public Cats(String catID, String catName, String catDescription, String catImage, int catPrice) {
        this.catID = catID;
        this.catName = catName;
        this.catDescription = catDescription;
        this.catImage = catImage;
        this.catPrice = catPrice;
    }

    public String getCatID() {
        return catID;
    }

    public String getCatName() {
        return catName;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }
}
