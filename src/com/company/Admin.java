package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;

public class Admin extends JFrame {
    JButton v_all_customer,v_all_account,v_all_transactions,delete_an_account,edit_account,return_login;
    JLabel background;

    Admin(){
        setSize(600,700);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setContentPane(new JLabel(new ImageIcon("src/admin.PNG")));


        v_all_customer=new JButton("  View All Customers ");
        Dimension cust_dim=v_all_customer.getPreferredSize();
        v_all_customer.setBounds(200,100,cust_dim.width,cust_dim.height);
        v_all_customer.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                v_all_customer.setForeground(Color.WHITE);
                v_all_customer.setBackground(Color.DARK_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                v_all_customer.setBackground(UIManager.getColor("control"));
                v_all_customer.setForeground(Color.black);
            }
        });


        v_all_account=new JButton("  View All Account  ");
        Dimension account_dim=v_all_account.getPreferredSize();
        v_all_account.setBounds(200,200,account_dim.width,account_dim.height);
        v_all_account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                v_all_account.setForeground(Color.WHITE);
                v_all_account.setBackground(Color.DARK_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                v_all_account.setBackground(UIManager.getColor("control"));
                v_all_account.setForeground(Color.black);
            }
        });


        v_all_transactions=new JButton(" View All Transactions ");
        Dimension trans_dim=v_all_transactions.getPreferredSize();
        v_all_transactions.setBounds(200,300,trans_dim.width,trans_dim.height);
        v_all_transactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                v_all_transactions.setForeground(Color.WHITE);
                v_all_transactions.setBackground(Color.DARK_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                v_all_transactions.setBackground(UIManager.getColor("control"));
                v_all_transactions.setForeground(Color.black);
            }
        });


        delete_an_account=new JButton("  Delete An Account  ");
        Dimension del_an_account=delete_an_account.getPreferredSize();
        delete_an_account.setBounds(200,400,del_an_account.width,del_an_account.height);
        delete_an_account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                delete_an_account.setForeground(Color.WHITE);
                delete_an_account.setBackground(Color.DARK_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                delete_an_account.setBackground(UIManager.getColor("control"));
                delete_an_account.setForeground(Color.black);
            }
        });

        edit_account=new JButton("Edit Account Details");
        Dimension edit_dim=edit_account.getPreferredSize();
        edit_account.setBounds(200,500,edit_dim.width,edit_dim.height);
        edit_account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                edit_account.setForeground(Color.WHITE);
                edit_account.setBackground(Color.GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                edit_account.setBackground(UIManager.getColor("control"));
                edit_account.setForeground(Color.black);
            }
        });


        return_login=new JButton("  Return to Login ");
        Dimension return_dim=return_login.getPreferredSize();
        return_login.setBounds(200,600,return_dim.width,return_dim.height);
        return_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                return_login.setForeground(Color.WHITE);
                return_login.setBackground(Color.RED);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                return_login.setBackground(UIManager.getColor("control"));
                return_login.setForeground(Color.black);
            }
        });

        adminlistener m=new adminlistener();
        v_all_transactions.addActionListener(m);
        v_all_account.addActionListener(m);
        v_all_customer.addActionListener(m);
        delete_an_account.addActionListener(m);
        edit_account.addActionListener(m);
        return_login.addActionListener(m);
        add(v_all_account);
        add(v_all_customer);
        add(v_all_transactions);
        add(delete_an_account);
        add(edit_account);
        add(return_login);
        setVisible(true);

    }

    public class adminlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="  View All Customers "){
//                Connection con = null;
//                String view_all_customer="";
//                try {
//                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
//                    Statement stmt = con.createStatement();
//                    PreparedStatement pst = con.prepareStatement("select customer_id,name,dob, phone_no, email, address, username, password, secret_key from customer ");
//                    ResultSet res=pst.executeQuery();
//
//                    while (res.next()){
//                        view_all_customer=view_all_customer+"\nCustomer ID: "+res.getInt(1)+"\nName: "+res.getString(2)+"\nDOB: "+res.getString(3)+"\nPhone No: "+res.getString(4)+"\nEmail: "+res.getString(5)+"\nAddress: "+res.getString(6)+"\n ----- ↓Login Details↓ -----   "+"\nUsername: "+res.getString(7)+"\nPassword: "+res.getString(8)+"\nSecret Key: "+res.getString(9)+"\n ---------------------------\n\n";
//                    }
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//
//                JFrame frame = new JFrame();
//
//                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//                frame.setPreferredSize(new Dimension(450, 600));
//                JTextArea textArea = new JTextArea(30, 40);
//                textArea.setText(view_all_customer);
//                textArea.setEditable(false);
//                JScrollPane scrollPane = new JScrollPane(textArea);
//                JOptionPane.showMessageDialog(frame, scrollPane);
//
                try {
                    Admin_CustomerRecord m=new Admin_CustomerRecord();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            if(e.getActionCommand()=="  View All Account  "){

                try {
                    Admin_Account_Record mj=new Admin_Account_Record();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
            if(e.getActionCommand()==" View All Transactions "){
                dispose();
               Admin_transaction_record_type mj=new Admin_transaction_record_type();

            }
            if(e.getActionCommand()=="  Delete An Account  "){
                dispose();
                Admin_Delete_Account a=new Admin_Delete_Account();

            }
            if(e.getActionCommand()=="Edit Account Details"){
                dispose();
                Admin_Edit_Account n=new Admin_Edit_Account();
            }
            if(e.getActionCommand()=="  Return to Login "){
                dispose();
                try {
                    Main_Menu_loginbased m=new Main_Menu_loginbased();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
