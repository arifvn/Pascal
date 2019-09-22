package com.squareIT.belajarpascal.model;

public class KDItem {

    private String kd, kd2;
    private String caption, caption2;

    public KDItem() {
    }

    public KDItem(String kd, String caption, String kd2, String caption2) {
        this.kd = kd;
        this.caption = caption;
        this.kd2 = kd2;
        this.caption2 = caption2;
    }

    public String getKd() {
        return kd;
    }

    public String getCaption() {
        return caption;
    }

    public String getKd2() {
        return kd2;
    }

    public String getCaption2() {
        return caption2;
    }
}
