package com.squareIT.belajarpascal.model;

public class IntroItem {

    private String introTitle, introCaption;
    private int imageId;

    public IntroItem(String introTitle, String introCaption, int imageId) {
        this.introTitle = introTitle;
        this.introCaption = introCaption;
        this.imageId = imageId;
    }

    public String getIntroTitle() {
        return introTitle;
    }

    public String getIntroCaption() {
        return introCaption;
    }

    public int getImageId() {
        return imageId;
    }

}
