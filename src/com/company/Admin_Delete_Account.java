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

public class Admin_Delete_Account extends JFrame {
    JLabel label1, l2;
    JTextField tx;
    JButton b1, b2;

    Admin_Delete_Account() {
        setSize(500, 500);
        setLayout(null);
        setContentPane(new JLabel(new ImageIcon("src/admin_deleteaccount.PNG")));
        setLocationRelativeTo(null);
        setUndecorated(true);
        l2 = new JLabel("Delete An Account");
        l2.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
        l2.setForeground(Color.WHITE);
        Dimension title = l2.getPreferredSize();
        l2.setBounds(150, 100, title.width, title.height);
        label1 = new JLabel("Enter Account Id: ");
        label1.setForeground(Color.WHITE);
        Dimension custid = label1.getPreferredSize();
        tx = new JTextField(10);
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
        Dimension field = tx.getPreferredSize();
        b1 = new JButton("Delete");
        Dimension search_button = b1.getPreferredSize();
        b2 = new JButton("Home");
        Dimension back_button = b2.getPreferredSize();
        label1.setBounds(120, 200, custid.width, custid.height);
        tx.setBounds(230, 200, field.width, field.height);
        b1.setBounds(170, 300, search_button.width, search_button.height);

        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b1.setForeground(Color.WHITE);
                b1.setBackground(Color.RED);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b1.setBackground(UIManager.getColor("control"));
                b1.setForeground(Color.black);
            }
        });


        b2.setBounds(310, 300, back_button.width, back_button.height);
        b2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b2.setForeground(Color.WHITE);
                b2.setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b2.setBackground(UIManager.getColor("control"));
                b2.setForeground(Color.black);
            }
        });
        add(label1);
        add(l2);
        add(tx);
        add(b1);
        add(b2);
        admin_dellistener l = new admin_dellistener();
        b1.addActionListener(l);
        b2.addActionListener(l);
        setVisible(true);

    }

    public class admin_dellistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            if (e.getActionCommand() == "Delete") {
                if (tx.getText().length() > 0){

                    ResultSet res=null;
                    int cust_to_del;
                    boolean f = false;
                    try {
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                        PreparedStatement pst = con.prepareStatement("select customer_customer_id from account where  account.account_id=?");
                        pst.setInt(1,Integer.parseInt(tx.getText()));
                        res=pst.executeQuery();
                        if(!res.next()){
                            f=false;
                        }
                        else{
                            f=true;
                        }

                        if (f){
                            cust_to_del=res.getInt(1);
                            PreparedStatement pse1t = con.prepareStatement("delete from customer where customer_id=?");
                            pse1t.setInt(1,cust_to_del);
                            ResultSet reb=pse1t.executeQuery();
                            reb.next();
                            JOptionPane.showMessageDialog(null,"Customer and Account Details are Deleted");
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"No Account with this ID found");
                            tx.setText("");
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Enter Required Data");
                }


            }
            if(e.getActionCommand()=="Home"){
                dispose();
                Admin d=new Admin();
            }
        }
    }
}
