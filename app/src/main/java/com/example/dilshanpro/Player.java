package com.example.dilshanpro;

import android.graphics.Bitmap;

public class Player {
    private Bitmap image;

    private String regn;
    private String name;
    private String age;
    private String score;
    private String overs;
    private String cent;
    private String hcen;
    private String wicket;
    private String position;
    private String srate;
    private String eco;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegn() {
        return regn;
    }

    public void setRegn(String regn) {
        this.regn = regn;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getOvers() {
        return overs;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    public String getCent() {
        return cent;
    }

    public void setCent(String cent) {
        this.cent = cent;
    }

    public String getHcen() {
        return hcen;
    }

    public void setHcen(String hcen) {
        this.hcen = hcen;
    }

    public String getWicket() {
        return wicket;
    }

    public void setWicket(String wicket) {
        this.wicket = wicket;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public Player(Bitmap image, String regn, String name, String age, String score, String overs, String cent, String hcen, String wicket, String position) {
        this.image = image;
        this.regn = regn;
        this.name = name;
        this.age = age;
        this.score = score;
        this.overs = overs;
        this.cent = cent;
        this.hcen = hcen;
        this.wicket = wicket;
        this.position = position;


}




}
