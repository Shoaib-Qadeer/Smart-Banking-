package com.company;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.*;

public class t_topup {

    JFrame m=new JFrame();
    JTextArea textArea;
    JButton button;
    JTable jTable;
    JScrollPane scrollPane;
    String [][] array;

    t_topup()  throws SQLException {
        m.setLocationRelativeTo(null);
        String [] columnnames={"Transaction ID ","DatenTime","Type ","Account Id ","Amount","Mobile No","Sim Operator"};

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
        PreparedStatement pst = con.prepareStatement("select count(*) from transaction, mobile_topup where transaction.id = mobile_topup.id");
        ResultSet res = pst.executeQuery();

        if(res.next()==false){
            JOptionPane.showMessageDialog(null,"Error in fetching Record");
        }
        int total=res.getInt(1);
        array=new String[total][7];
        pst=con.prepareStatement("select transaction.id, transaction.datentime, transaction.type, transaction.account_account_id, mobile_topup.amount, mobile_topup.mobile_no, mobile_topup.sim_operator from transaction,  mobile_topup where transaction.id=mobile_topup.id\n");
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
                array[row][4]=  String.valueOf(res.getInt(5));
                array[row][5]=  res.getString(6);
                array[row][6]=  res.getString(7);




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
        m.setTitle("Mobile Top Up Record Only");

        m.add(scrollPane,BorderLayout.CENTER);

        m.setVisible(true);


    }

}
