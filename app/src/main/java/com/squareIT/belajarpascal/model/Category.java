package com.squareIT.belajarpascal.model;

public class Category {

    public static final int LATIHAN_SOAL_1 = 1;
    public static final int LATIHAN_SOAL_2 = 2;
    public static final int LATIHAN_SOAL_3 = 3;

    private int id;
    private String name;
    private int highscore;

    public Category() {
    }

    public Category(String name, int highscore) {
        this.name = name;
        this.highscore = highscore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    @Override
    public String toString() {
        return getName();
    }

}
