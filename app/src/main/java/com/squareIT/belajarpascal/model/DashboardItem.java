package com.squareIT.belajarpascal.model;

public class DashboardItem {

    private int imageIcon;
    private String title;

    public DashboardItem(int imageIcon, String title) {
        this.imageIcon = imageIcon;
        this.title = title;
    }

    public int getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(int imageIcon) {
        this.imageIcon = imageIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
