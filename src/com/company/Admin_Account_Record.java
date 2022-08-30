package com.company;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Admin_Account_Record extends JFrame {



    Admin_Account_Record() throws SQLException {
        JFrame m=new JFrame();
        JTextArea textArea;
        setLocationRelativeTo(null);
        JTable jTable;
        JScrollPane scrollPane;
        String [][] array;


        String [] columnnames={"Account ID ","Account Status","Account Level ","Account Balance ","Bank Code","Customer ID ","Customer Name ",};

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
        PreparedStatement pst = con.prepareStatement("Select count(*) as rowcount from customer");
        ResultSet res = pst.executeQuery();

        if(res.next()==false){
            JOptionPane.showMessageDialog(null,"Error in fetching Record");
        }
        int total=res.getInt(1);
        array=new String[total][7];
        pst=con.prepareStatement("select account_id, account_status, account_level, account_balance , bank_bank_code, customer_customer_id, customer.name\n" +
                "from account, customer\n" +
                "where account.customer_customer_id=customer.customer_id");
        res=pst.executeQuery();
        if (res.next() == false) {

        } else {
            int row=-1;
            do {
                row++;

                array[row][0]= String.valueOf(res.getInt(1));
                array[row][1]=res.getString(2);
                array[row][2]= res.getString(3);
                array[row][3]=String.valueOf(res.getInt(4));
                array[row][4]=  String.valueOf(res.getString(5));
                array[row][5]=  String.valueOf(res.getString(6));
                array[row][6]= res.getString(7);

            } while (res.next());


        }
        ;
        jTable=new JTable(array,columnnames) ;
        jTable.setRowHeight(30);
        jTable.setFillsViewportHeight(true);
        TableColumn tableColumn;
        tableColumn=jTable.getColumnModel().getColumn(0);
        jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
        tableColumn.setPreferredWidth(30);
        scrollPane=new JScrollPane(jTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        m.setLayout(new BorderLayout());
        m.setBounds(150,100,1200,600);
        m.setTitle("ABC BANK ACCOUNTS RECORD");
        m.add(scrollPane,BorderLayout.CENTER);


        m.setVisible(true);


    }





}




