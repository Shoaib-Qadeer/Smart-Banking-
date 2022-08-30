package com.company;

import java.io.*;
import java.util.ArrayList;

public class Date {

    int day;
    int month;
    int year;

    public Date() {
    }


    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(Date d){
        this.day=d.getDay();
        this.month=d.getMonth();
        this.year=d.getYear();
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void display(){
        System.out.println("Date of Birth: "+day+"/"+month+"/"+year);
    }



    public String toString(){
        return this.day+"-"+this.month+"-"+this.year;
    }

}
