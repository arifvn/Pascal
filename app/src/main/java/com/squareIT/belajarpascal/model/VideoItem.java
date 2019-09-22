package com.squareIT.belajarpascal.model;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoItem implements Parcelable {
    //Youtube API and URL
    private static final String API_KEY = "AIzaSyB578XQ9uxMCrNRB4J-4e-WbCbPaZXIi3U";

    private String videoTitle;
    private String videoCaption;
    private int colorBg;

    public VideoItem() {
    }

    public VideoItem(String videoTitle, String videoCaption, int colorBg) {
        this.videoTitle = videoTitle;
        this.videoCaption = videoCaption;
        this.colorBg = colorBg;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    protected VideoItem(Parcel in) {
        videoTitle = in.readString();
        videoCaption = in.readString();
        colorBg = in.readInt();
    }

    public static final Creator<VideoItem> CREATOR = new Creator<VideoItem>() {
        @Override
        public VideoItem createFromParcel(Parcel in) {
            return new VideoItem(in);
        }

        @Override
        public VideoItem[] newArray(int size) {
            return new VideoItem[size];
        }
    };

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoCaption() {
        return videoCaption;
    }

    public int getColorBg() {
        return colorBg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videoTitle);
        dest.writeString(videoCaption);
        dest.writeInt(colorBg);
    }
}
