package com.company;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Account  {
    private long account_id;
    private int acc_balance;
    private String account_level;
    private String account_status;
    Customer custaccount;


    Account(String Username, String password, String secretky,String name,Address add, String cntno, String eml,Date dateof_birth,int balance,String acc_level,String act_status) throws SQLException {
        this.account_id=assignaccountid();
        int cust_idd=assigncustomerid();
        custaccount=new Customer(cust_idd,Username,password,secretky,name, add, cntno, eml, dateof_birth);
        this.acc_balance=balance;
        this.account_level=acc_level;
        this.account_status=act_status;

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
        PreparedStatement pstmt=con.prepareStatement("Insert into Customer values (?,?,?,?,?,?,?,?,?)");
        pstmt.setString(1,custaccount.getCustomer_name());
        pstmt.setString(2,custaccount.getContactnumber());
        pstmt.setString(3,custaccount.getEmail());
        pstmt.setString(4,custaccount.lg_sg.getUser_name());
        pstmt.setString(5,custaccount.lg_sg.getPassword());
        pstmt.setString(6,custaccount.lg_sg.getSecret_key());
        pstmt.setString(7,custaccount.address.toString());
        pstmt.setInt(8, (int) custaccount.getCustomer_id());
        pstmt.setString(9,custaccount.date_of_birth.toString());



        PreparedStatement pst=con.prepareStatement("INSERT  into Account values (?,?,?,?,?,?)");
        pst.setInt(1, (int) account_id);
        pst.setString(2,account_status);
        pst.setString(3,acc_level);
        pst.setInt(4,acc_balance);
        pst.setInt(5,2790);
        pst.setInt(6,(int) custaccount.getCustomer_id());


       ResultSet rs =pstmt.executeQuery();
        ResultSet res =pst.executeQuery();

    }

    Account (){}

    Account(Account acc)
    {
        this.account_id=acc.getAccount_id();
        this.account_id=acc.getAccount_id();
        this.acc_balance=acc.getAcc_balance();
        this.account_level=acc.getAccount_level();
        this.account_status=acc.getAccount_status();
        this.custaccount=new Customer(acc.getCustaccount());

    }

    public void display(){
        System.out.println("\nAccount Id: "+account_id+"\nAccount Balance: "+acc_balance+"\nAccount Level: "+account_level+"\nAccount Status: "+account_status);
        custaccount.display();
    }


    public static void displayall(){

    }

    public static void deleteall(){

    }

    public Customer getCustaccount() {
        return custaccount;
    }
    public int getAcc_balance() {
        return acc_balance;
    }
    public long getAccount_id() {
        return account_id;
    }
    public String getAccount_level() {
        return account_level;
    }
    public String getAccount_status() {
        return account_status;
    }
    public void setAccount_level(String account_level) {
        this.account_level = account_level;
    }
    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }
    public void setCustaccount(Customer custaccount) {
        this.custaccount = new Customer(custaccount);
    }
    public void setAcc_balance(int acc_balance) {
        this.acc_balance = acc_balance;
    }

    public static int assignaccountid() throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
        PreparedStatement pst=con.prepareStatement("select st_id from record_ids where id_type=? ");
        pst.setString(1,"account");
        ResultSet res=pst.executeQuery();
        res.next();

        int se_id=res.getInt(1);
        int acc_id=se_id+1;
        pst=con.prepareStatement("update record_ids set st_id=? where id_type=?");
        pst.setInt(1,acc_id);
        pst.setString(2,"account");
        pst.execute();


        return acc_id;
    }

    public static int assigncustomerid() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
        PreparedStatement pst=con.prepareStatement("select st_id from record_ids where id_type=? ");
        pst.setString(1,"customer");
        ResultSet res=pst.executeQuery();
        res.next();

        int se_id=res.getInt(1);
        int cust_id=se_id+1;
        pst=con.prepareStatement("update record_ids set st_id=? where id_type=?");
        pst.setInt(1,cust_id);
        pst.setString(2,"customer");
        pst.execute();

        return cust_id;
    }



    public String toString(){
        return "\nAccount Id: "+account_id+"\nAccount Balance(PKR): "+acc_balance+"\nAccount Status: "+account_status+"\nAccount Level: "+account_level+"\n"+custaccount.toString();
    }

    public static boolean searchaaccount(int id) {
        return true;
    }

    public static int getsearchaaccount(int id) {
       return 0;
    }

}
