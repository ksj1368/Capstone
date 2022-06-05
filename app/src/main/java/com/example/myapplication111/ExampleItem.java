package com.example.myapplication111;

public class ExampleItem {
    private String text1;
    private String text2;
    private String lat;
    private String lon;

    public ExampleItem(String text1,String text2,String lat,String lon){
        this.text1 = text1;
        this.text2 = text2;
        this.lat = lat;
        this.lon = lon;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }
    public String getLat() {
        return lat;
    }
    public String getLon() {
        return lon;
    }
}
