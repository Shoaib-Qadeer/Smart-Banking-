package com.company;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer  {
    static Scanner input=new Scanner(System.in);
    long customer_id;
    String customer_name;
    Date date_of_birth;
    Address address;
    String contactnumber;
    String email;
    Login_Signup lg_sg;




    Customer(){
    }

    Customer(int cust_id,String Username, String password, String secretky,String name, Address add, String cntno, String eml, Date dateof_birth) throws SQLException {
        this.customer_id=cust_id;
        assigncustomerid(this.customer_id);
        this.customer_name=name;
        this.address=new Address(add);
        this.date_of_birth= new Date(dateof_birth);
        this.contactnumber=cntno;
        this.email=eml;
        this.lg_sg=new Login_Signup(Username,password,secretky);
    }

    Customer(Customer cd){
        this.customer_id=cd.getCustomer_id();
        this.customer_name=cd.getCustomer_name();
        this.contactnumber=cd.getContactnumber();
        this.email=cd.getEmail();
        this.date_of_birth=new Date(cd.getDate_of_birth());
        this.address=new Address(cd.getAddress());
    }


    public String toString(){
        return "(Customer Details)   \nCustomer Id: "+this.customer_id+"\nName: "+this.customer_name+"\nDate of Birth: "+date_of_birth.toString()+"\nAddress: "+address.toString()+"\nContact Number: "+this.contactnumber+"\nEmail: "+this.email+"\n  (Login Details)  "+"\n"+lg_sg.toString();
    }







    public void display(){
        System.out.println("Customer Id: "+this.customer_id+"\nName: "+this.customer_name+"\nContact No: "+this.contactnumber);
        System.out.println("Email Address: "+this.email);
        this.date_of_birth.display();
        System.out.println("Address : Home No#"+this.address.gethome()+", Street No#"+this.address.getStreet()+","+this.address.getcity());
        this.lg_sg.display();
        System.out.println("<----------------------------------------->");
    }


    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address add) {
        this.address = new Address(add);
    }
    public Date getDate_of_birth() {
        return date_of_birth;
    }
    public long getCustomer_id() {
        return customer_id;
    }
    public String getEmail() {
        return email;
    }
    public String getContactnumber() {
        return contactnumber;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = new Date(date_of_birth);
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public static void assigncustomerid(long id){
        long x=readvalue();
        x=id+1;
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("Customer_id_Rec"))) {
            dos.writeLong(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long readvalue( ) {
        long id=0;
        if (!new File("Customer_id_Rec").canRead()) {
            id= 190021;}

        try  {
            DataInputStream dis = new DataInputStream(new FileInputStream("Customer_id_Rec"));
            id= dis.readLong();
        } catch (IOException ignored) {

        }
        return id;
    }

    public static void deleteall(){

    }




    public static void updatename(int id,String name) {


    }

    public static boolean searchacustomer(int id) {

        return true;
    }

    public static int getsearchacustomer(int id) {

        return 1;
    }

    public static void updateAddress(int id,Address ad) {

    }

    public static void updateDateofbirth(int id,Date da) {

    }

    public static void Contactnum(int id,Address ad) {

    }

    public static void Deletecustomer(int id) {
    }

}
