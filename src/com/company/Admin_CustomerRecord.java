package com.company;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Admin_CustomerRecord extends JFrame {

    JFrame m=new JFrame();
    JTextArea textArea;
    JButton button;
    JTable jTable;
    JScrollPane scrollPane;

    String [][] array;

    Admin_CustomerRecord() throws SQLException {
        m.setLocationRelativeTo(null);

        String [] columnnames={"Customer Id ","Customer Name","Phone No ","Email ","Address","DOB ","Username ","Password","Secret Key"};

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
        PreparedStatement pst = con.prepareStatement("Select count(*) as rowcount from customer");
        ResultSet res = pst.executeQuery();

        if(res.next()==false){
            JOptionPane.showMessageDialog(null,"Error in fetching Record");
        }
        int total=res.getInt(1);
        array=new String[total][9];
        pst=con.prepareStatement("select  customer_id, name,phone_no,email, address, dob, username, password, secret_key  from customer");
        res=pst.executeQuery();
        if (res.next() == false) {

        } else {
            int row=-1;
            do {
                row++;

                array[row][0]= String.valueOf(res.getInt(1));
                array[row][1]=res.getString(2);
                array[row][2]= res.getString(3);
                array[row][3]=res.getString(4);
                array[row][4]= res.getString(5);
                array[row][5]= res.getString(6);
                array[row][6]= res.getString(7);
                array[row][7]= res.getString(8);
                array[row][8]= res.getString(9);




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
        m.setTitle("Customer Record");
        m.add(scrollPane,BorderLayout.CENTER);

        m.setVisible(true);


    }





}



