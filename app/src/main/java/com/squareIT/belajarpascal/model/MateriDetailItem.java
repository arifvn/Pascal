package com.squareIT.belajarpascal.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MateriDetailItem implements Parcelable {

    //header
    private String kd;
    private String caption;
    private String number;

    public MateriDetailItem() {
    }

    public MateriDetailItem(String kd, String caption, String number) {
        this.kd = kd;
        this.caption = caption;
        this.number = number;
    }

    public String getKd() {
        return kd;
    }

    public String getCaption() {
        return caption;
    }

    public String getNumber() {
        return number;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kd);
        dest.writeString(caption);
        dest.writeString(number);
    }

    protected MateriDetailItem(Parcel in) {
        kd = in.readString();
        caption = in.readString();
        number = in.readString();
    }

    public static final Creator<MateriDetailItem> CREATOR = new Creator<MateriDetailItem>() {
        @Override
        public MateriDetailItem createFromParcel(Parcel in) {
            return new MateriDetailItem(in);
        }

        @Override
        public MateriDetailItem[] newArray(int size) {
            return new MateriDetailItem[size];
        }
    };
}

