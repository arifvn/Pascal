package com.squareIT.belajarpascal.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RPPItem implements Parcelable {
    private int id;
    private String kd;
    private String title;

    public RPPItem(int id, String kd, String title) {
        this.id = id;
        this.kd = kd;
        this.title = title;
    }

    protected RPPItem(Parcel in) {
        id = in.readInt();
        kd = in.readString();
        title = in.readString();
    }

    public static final Creator<RPPItem> CREATOR = new Creator<RPPItem>() {
        @Override
        public RPPItem createFromParcel(Parcel in) {
            return new RPPItem(in);
        }

        @Override
        public RPPItem[] newArray(int size) {
            return new RPPItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKd() {
        return kd;
    }

    public void setKd(String kd) {
        this.kd = kd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(kd);
        dest.writeString(title);
    }
}
