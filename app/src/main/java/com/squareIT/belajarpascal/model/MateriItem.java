package com.squareIT.belajarpascal.model;

public class MateriItem {

    //header
    private String id;
    private String title;
    private String caption;

    public MateriItem() {
    }

    public MateriItem(String id, String title, String caption) {
        this.id = id;
        this.title = title;
        this.caption = caption;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCaption() {
        return caption;
    }
}
