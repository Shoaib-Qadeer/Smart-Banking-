package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login_Signup  {
    private String user_name;
    private String password;
    private String secret_key;

    static Scanner input=new Scanner(System.in);

    Login_Signup(){}

    Login_Signup(String u_name, String pass,String sec_key){
        this.user_name=u_name;
        this.password=pass;
        this.secret_key=sec_key;

    }

    Login_Signup(Login_Signup lg_sg){
        this.user_name=lg_sg.getUser_name();
        this.password=lg_sg.getPassword();
        this.secret_key=lg_sg.getSecret_key();
    }


    public void display(){
        System.out.println("Username: "+user_name+"\nPassword: "+password+"\nSecret Key: "+secret_key);
    }
    public void edit_details(){
        int choice=0;
        while (choice!=3){
            System.out.println("1)Edit Password\n2)Edit Secret Key\n Enter Your choice ");
            choice=input.nextInt();
            if(choice==1){
                System.out.println("Enter New Password");
                String pass=input.nextLine();
                this.password=pass;
            }
            else if(choice==2){
                System.out.println("Enter New Secret Key");
                String seckey=input.nextLine();
                this.secret_key=seckey;
            }
            else if(choice!=1 && choice!=2 && choice!=3){
                System.out.println("Invalid Input - Try Again");
            }
        }
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return "\nUserName: "+this.user_name+"\nPassword"+this.password+"\nSecret Key: "+this.secret_key+"\n<----------------->";
    }



}