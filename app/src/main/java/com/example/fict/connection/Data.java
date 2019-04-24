package com.example.fict.connection;

import java.util.ArrayList;

public class Data {
    private Respones respones = new Respones();
    Parsing parsing = new Parsing();
    private float GetLastValue;
    private ArrayList<Float> getDay;
    private ArrayList<Float> getWeek;
    private ArrayList<Float> getMonth;
    private ArrayList<Float> getYear;


    public float getGetLastValue() {
        return GetLastValue;
    }

    public void setGetLastValue(float getLastValue) {
        GetLastValue = getLastValue;
    }

    public ArrayList<Float> getGetDay() {
        return getDay;
    }

    public void setGetDay(ArrayList<Float> getDay) {
        this.getDay = getDay;
    }

    public ArrayList<Float> getGetWeek() {
        return getWeek;
    }

    public void setGetWeek(ArrayList<Float> getWeek) {
        this.getWeek = getWeek;
    }

    public ArrayList<Float> getGetMonth() {
        return getMonth;
    }

    public void setGetMonth(ArrayList<Float> getMonth) {
        this.getMonth = getMonth;
    }

    public ArrayList<Float> getGetYear() {
        return getYear;
    }

    public void setGetYear(ArrayList<Float> getYear) {
        this.getYear = getYear;
    }


   getLastValue(String start, String end, int id, int step){
        respones.ResponesHistory(start,end,id,step);
        parsing.setRESPONSES(respones.getRESPONSES());
        setGetLastValue(parsing.getValuesArray());
  }


}
