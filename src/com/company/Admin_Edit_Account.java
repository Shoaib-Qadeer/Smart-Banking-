package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;

public class Admin_Edit_Account extends JFrame {
    JLabel label1,l2,title;
    JLabel label_new_balance,label_new_account_status;
    JTextField tx,tx2,field_balance,field_status;
    JButton b1,b2;

    Admin_Edit_Account(){
        setSize(500,600);
        setUndecorated(true);
        setLayout(null);  //
        setUndecorated(true);
        setLocationRelativeTo(null);
        setContentPane(new JLabel(new ImageIcon("src/admin_updateaccount.PNG")));
        title= new JLabel("Update An Account");
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
        Dimension title1 = title.getPreferredSize();
        title.setForeground(Color.WHITE);
        title.setBounds(150, 100, title1.width, title1.height);
        label1=new JLabel("Enter Account Id: ");
        label1.setForeground(Color.WHITE);
        Dimension custid=label1.getPreferredSize();
        label1.setBounds(100,185,custid.width,custid.height);
        tx=new JTextField(14);
        Dimension txcustid=tx.getPreferredSize();
        tx.setBounds(200,185,txcustid.width,txcustid.height);
        tx.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        l2=new JLabel("Enter New Name: ");
        l2.setForeground(Color.WHITE);
        Dimension newname=l2.getPreferredSize();
        tx2=new JTextField(null,20);
        Dimension newtxt=tx2.getPreferredSize();
        l2.setBounds(100,235,newname.width,newname.height);
        tx2.setBounds(200,235,newtxt.width,newtxt.height);



        b1=new JButton("Update");
        Dimension search_but=b1.getPreferredSize();
        b1.setBounds(150,420,search_but.width,search_but.height);
         b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b1.setForeground(Color.WHITE);
                b1.setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b1.setBackground(UIManager.getColor("control"));
                b1.setForeground(Color.black);
            }
        });
        b2=new JButton("Home");
        Dimension home_but=b2.getPreferredSize();
        b2.setBounds(250,420,home_but.width,home_but.height);
        b2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b2.setForeground(Color.WHITE);
                b2.setBackground(Color.DARK_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b2.setBackground(UIManager.getColor("control"));
                b2.setForeground(Color.black);
            }
        });


        label_new_account_status=new JLabel("Enter New Account Level:");

        label_new_account_status.setForeground(Color.WHITE);
        Dimension ac_stat_dim=label_new_account_status.getPreferredSize();
        label_new_account_status.setBounds(70,285,ac_stat_dim.width,ac_stat_dim.height);


        field_status=new JTextField("",20);
        Dimension f_status_dim=field_status.getPreferredSize();
        field_status.setBounds(230,285,f_status_dim.width,f_status_dim.height);

        label_new_balance=new JLabel("Enter New Balance:");
        label_new_balance.setForeground(Color.WHITE);
        label1.setForeground(Color.WHITE);
        Dimension ac_bal_dim=label_new_balance.getPreferredSize();
        label_new_balance.setBounds(80,335,ac_bal_dim.width,ac_bal_dim.height);


        field_balance=new JTextField("",20);
        Dimension f_bal_dim=field_balance.getPreferredSize();
        field_balance.setBounds(200,335,f_bal_dim.width,f_bal_dim.height);
        field_balance.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });



        add(label1);
        add(title);
        add(tx);
        add(tx2);
        add(l2);
        add(b1);
        add(b2);
        add(field_balance);
        add(field_status);
        add(label_new_account_status);
        add(label_new_balance);
        admin_upd_mylistener l=new admin_upd_mylistener();
        b1.addActionListener(l);
        b2.addActionListener(l);
        setVisible(true);
    }

    public class admin_upd_mylistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="Update") {
                if (tx.getText().length() > 0 && tx2.getText().length() > 0 && field_balance.getText().length()>0 && field_status.getText().length()>0) {
                    int ac_id = Integer.parseInt(tx.getText());
                    ResultSet res = null;
                    boolean f = false;
                    try {
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                        PreparedStatement pst = con.prepareStatement("select customer_id, account_id from account, customer where account.customer_customer_id=customer.customer_id and account.account_id=?");
                        pst.setInt(1, Integer.parseInt(tx.getText()));
                        res = pst.executeQuery();
                        if (!res.next()) {
                            f = false;
                        } else {
                            f = true;
                        }


                        if (f) {
                            int cid_edit = res.getInt(1);
                            int accid_edit = res.getInt(2);

                            PreparedStatement newst = con.prepareStatement("update customer SET name=? where customer_id=?");
                            newst.setString(1, tx2.getText());
                            newst.setInt(2, cid_edit);
                            res = newst.executeQuery();

                            PreparedStatement updaccount = con.prepareStatement(" update Account SET account_level=?, account_balance=? where account_id=?");
                            updaccount.setString(1, field_status.getText());
                            updaccount.setInt(2, Integer.parseInt(field_balance.getText()));
                            updaccount.setInt(3, ac_id);
                            res = updaccount.executeQuery();
                            JOptionPane.showMessageDialog(null, "Account and Cutomer Record Updated");
                            tx.setText("");
                            tx2.setText("");
                            field_balance.setText("");
                            field_status.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "No Account Found - Enter Again");
                            tx.setText("");
                            tx2.setText("");
                            field_balance.setText("");
                            field_status.setText("");
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        JOptionPane.showMessageDialog(null,throwables.toString());
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Enter Required Data");
                }

            }




     if(e.getActionCommand()=="Home"){
                dispose();
                Admin ad=new Admin(); }
    }


    }

}




