package com.company;

import java.io.*;
import java.util.ArrayList;

public class Address {

    int home;
    int street;
    String city;


    public Address(){}

    public Address(int hm,int str,String cit){
        home=hm;street=str;city=cit;
    }
    public Address(Address a){
        this.city=a.city; this.home=a.home;this.street=a.street;
    }

    public void setCity(String ci) {
        this.city = ci;
    }

    public void setHome(int hm) {
        this.home = hm;
    }

    public void setStreet(int st) {
        this.street = st;
    }

    public int gethome(){return home;}

    public int getStreet(){return street;}

    public String getcity(){return city;}

    public void display(){
        System.out.println("\nAddress: Home#"+home+" Street#"+street+","+city);
    }


    public String toString (){
        return "Home #"+this.home+",Street #"+this.street+","+this.city;
    }
}

