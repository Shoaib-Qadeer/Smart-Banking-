package com.company;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Transaction implements Serializable {
    private int transaction_id;
    private   String datentime;
    private String transaction_type;
    private String trans_Details;
    private String mode;
    private int account_id;

 static  int trxn_id=26962;

    public static Scanner input=new Scanner(System.in);

    Transaction(){}

    Transaction(String trns_type, String mod,int acc_id, String tran_description) throws SQLException {
        this.transaction_id=assigntransactionid();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd \nHH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String datentime=dtf.format(now);
        this.datentime=datentime;
        this.transaction_type=trns_type;
        this.mode=mod;
       this.trans_Details=tran_description;
       this.account_id=acc_id;


        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
        PreparedStatement pstmt=con.prepareStatement("Insert into transaction values (?,?,?,?,?,?)");
        pstmt.setInt(1,transaction_id);
        pstmt.setString(2,datentime);
        pstmt.setString(3,transaction_type);
        pstmt.setString(4,trans_Details);
        pstmt.setString(5,mode);
        pstmt.setInt(6,account_id);
        ResultSet rs =pstmt.executeQuery();



    }




    public int  getAcc_id() {
        return account_id;
    }

    public String getDatentime() {
        return datentime;
    }

    public String getMode() {
        return mode;
    }

    public String getTrans_Details() {
        return trans_Details;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public static int assigntransactionid() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
        PreparedStatement pst=con.prepareStatement("select st_id from record_ids where id_type=? ");
        pst.setString(1,"transaction");
        ResultSet res=pst.executeQuery();
        res.next();

        int se_id=res.getInt(1);
        int trnsid=se_id+1;
        pst=con.prepareStatement("update record_ids set st_id=? where id_type=?");
        pst.setInt(1,trnsid);
        pst.setString(2,"transaction");
        pst.execute();

        return trnsid;
    }


}
