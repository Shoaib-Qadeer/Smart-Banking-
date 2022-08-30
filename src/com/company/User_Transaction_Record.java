package com.company;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.*;

class User_Transaction_Record extends JFrame {

    JFrame m=new JFrame();
    JTextArea textArea;
    JButton button;
    JTable jTable;
    JScrollPane scrollPane;
    String [][] array;
    static int account_id;
    int  row;

    User_Transaction_Record() throws SQLException {
        m.setLocationRelativeTo(null);

        String [] columnnames={"Transaction ID ","DatenTime","Type ","Mode ","Account ID ","Amount","Description"};

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
        PreparedStatement pst = con.prepareStatement("Select count(*) as rowcount from transaction where transaction.account_account_id=? ");
        pst.setInt(1,account_id);
        ResultSet res = pst.executeQuery();

        if(res.next()==false){
            JOptionPane.showMessageDialog(null,"Error in fetching Record");
        }
        int total=res.getInt(1);
        array=new String[total][7];
        pst=con.prepareStatement("select transaction.id, transaction.datentime, transaction.type, transaction.t_mode, transaction.account_account_id, add_funds.amount, transaction.description\n" +
                "from transaction, add_funds \n" +
                "where transaction.id=add_funds.id and transaction.account_account_id=? ");
        pst.setInt(1,account_id);
        res=pst.executeQuery();
        row=-1;
        if (res.next() == false) {

        } else {
            do {
                row++;

                array[row][0]= String.valueOf(res.getInt(1));
                array[row][1]=res.getString(2);
                array[row][2]= res.getString(3);
                array[row][3]=res.getString(4);
                array[row][4]=  res.getString(5);
                array[row][5]=  String.valueOf(res.getInt(6));
                array[row][6]=res.getString(7);


            } while (res.next());


        };

        pst=con.prepareStatement("select transaction.id, transaction.datentime, transaction.type, transaction.t_mode, transaction.account_account_id, bill_payment.payable_amount, transaction.description\n" +
                "from transaction, bill_payment \n" +
                "where transaction.id=bill_payment.id and transaction.account_account_id=? ");
        pst.setInt(1,account_id);
        res=pst.executeQuery();
        if (res.next() == false) {

        } else {
            do {
                row++;

                array[row][0]= String.valueOf(res.getInt(1));
                array[row][1]=res.getString(2);
                array[row][2]= res.getString(3);
                array[row][3]=res.getString(4);
                array[row][4]=  res.getString(5);
                array[row][5]=  String.valueOf(res.getInt(6));
                array[row][6]=res.getString(7);


            } while (res.next());


        }

        pst=con.prepareStatement("select transaction.id, transaction.datentime, transaction.type, transaction.t_mode, transaction.account_account_id, donation.amount, transaction.description\n" +
                "from transaction, donation\n" +
                "where transaction.id=donation.id and transaction.account_account_id=? ");
        pst.setInt(1,account_id);
        res=pst.executeQuery();
        if (res.next() == false) {

        } else {
            do {
                row++;

                array[row][0]= String.valueOf(res.getInt(1));
                array[row][1]=res.getString(2);
                array[row][2]= res.getString(3);
                array[row][3]=res.getString(4);
                array[row][4]=  res.getString(5);
                array[row][5]=  String.valueOf(res.getInt(6));
                array[row][6]=res.getString(7);


            } while (res.next());


        }

        pst=con.prepareStatement("select transaction.id, transaction.datentime, transaction.type, transaction.t_mode, transaction.account_account_id, funds_transfer.funds, transaction.description\n" +
                "from transaction, funds_transfer\n" +
                "where transaction.id=funds_transfer.id and transaction.account_account_id=? ");
        pst.setInt(1,account_id);
        res=pst.executeQuery();
        if (res.next() == false) {

        } else {
            do {
                row++;

                array[row][0]= String.valueOf(res.getInt(1));
                array[row][1]=res.getString(2);
                array[row][2]= res.getString(3);
                array[row][3]=res.getString(4);
                array[row][4]=  res.getString(5);
                array[row][5]=  String.valueOf(res.getInt(6));
                array[row][6]=res.getString(7);


            } while (res.next());


        }


        pst=con.prepareStatement("select transaction.id, transaction.datentime, transaction.type, transaction.t_mode, transaction.account_account_id, mobile_topup.amount, transaction.description\n" +
                "from transaction, mobile_topup\n" +
                "where transaction.id= mobile_topup.id and transaction.account_account_id=? ");
        pst.setInt(1,account_id);
        res=pst.executeQuery();
        if (res.next() == false) {

        } else {
            do {
                row++;

                array[row][0]= String.valueOf(res.getInt(1));
                array[row][1]=res.getString(2);
                array[row][2]= res.getString(3);
                array[row][3]=res.getString(4);
                array[row][4]=  res.getString(5);
                array[row][5]=  String.valueOf(res.getInt(6));
                array[row][6]=res.getString(7);


            } while (res.next());


        }


        pst=con.prepareStatement("select transaction.id, transaction.datentime, transaction.type, transaction.t_mode, transaction.account_account_id, virtual_card.limit, transaction.description\n" +
                "from transaction, virtual_card\n" +
                "where transaction.id= virtual_card.id and transaction.account_account_id=? ");
        pst.setInt(1,account_id);
        res=pst.executeQuery();
        if (res.next() == false) {

        } else {
            do {
                row++;

                array[row][0]= String.valueOf(res.getInt(1));
                array[row][1]=res.getString(2);
                array[row][2]= res.getString(3);
                array[row][3]=res.getString(4);
                array[row][4]=  res.getString(5);
                array[row][5]=  String.valueOf(res.getInt(6));
                array[row][6]=res.getString(7);


            } while (res.next());


        }

        pst=con.prepareStatement("select transaction.id, transaction.datentime, transaction.type, transaction.t_mode, transaction.account_account_id,withdraw.amount , transaction.description\n" +
                "from transaction, withdraw\n" +
                "where transaction.id=withdraw.id and transaction.account_account_id=? ");
        pst.setInt(1,account_id);
        res=pst.executeQuery();
        if (res.next() == false) {

        } else {
            do {
                row++;

                array[row][0]= String.valueOf(res.getInt(1));
                array[row][1]=res.getString(2);
                array[row][2]= res.getString(3);
                array[row][3]=res.getString(4);
                array[row][4]=  res.getString(5);
                array[row][5]=  String.valueOf(res.getInt(6));
                array[row][6]=res.getString(7);


            } while (res.next());


        }
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
        m.setTitle("ABC BANK TRANSACTION Record");
        m.add(scrollPane,BorderLayout.CENTER);

        m.setVisible(true);


    }





}




