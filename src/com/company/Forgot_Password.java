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

public class Forgot_Password extends JFrame {
    JLabel l1,l2;
    JTextField tx1,tx2;
    JButton b1,b2;

    Forgot_Password(){
        setSize(600,600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("src/Forgot_Password.GIF")));

        l1=new JLabel("Enter Your User Name: ");
        l1.setForeground(Color.WHITE);
        Dimension sizeofuser=l1.getPreferredSize();

        l2=new JLabel("Enter Secret key");
        l2.setForeground(Color.WHITE);
        Dimension sizeofpasswordlabel=l2.getPreferredSize();

        tx1=new JTextField(null,20);
        tx2=new JTextField(null,20);
        Dimension size1= tx1.getPreferredSize();
        Dimension size2=tx2.getPreferredSize();

        l1.setBounds(100,250,sizeofuser.width,sizeofuser.height);
        l2.setBounds(100,310,sizeofpasswordlabel.width,sizeofpasswordlabel.height);
        tx1.setBounds(300,250,size1.width,size1.height);
        tx2.setBounds(300,310,size2.width,size2.height);

        b1=new JButton("Get Password");
        b2=new JButton("Return");

        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b1.setForeground(Color.WHITE);
                b1.setBackground(Color.DARK_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b1.setBackground(UIManager.getColor("control"));
                b1.setForeground(Color.black);
            }
        });

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

        Dimension loginbu=b1.getPreferredSize();
        Dimension fg_pass_d=b2.getPreferredSize();

        b1.setBounds(170,430,loginbu.width,loginbu.height);
        b2.setBounds(300,430,fg_pass_d.width,fg_pass_d.height);
        myActionListener m=new myActionListener();
        b1.addActionListener(m);
        b2.addActionListener(m);

        add(l1);
        add(l2);
        add(tx1);
        add(tx2);
        add(b1);
        add(b2);
        setVisible(true);

    }

    public class myActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String password="";
            String username="";
            if(e.getActionCommand()=="Get Password"){
                boolean found=false;
                ResultSet res = null;
                try {
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                    PreparedStatement pst = con.prepareStatement("select  username, password from customer where username=? and secret_key=?");
                    pst.setString(1,tx1.getText());
                    pst.setString(2,tx2.getText());
                    res=pst.executeQuery();
                    if(!res.next()){
                        found=false;

                    }
                    else{
                        found=true;
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }



                if(found){
                    try {
                        username=res.getString(1);
                        password=res.getString(2);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null,"Your Username is: "+username+"\nYour Password is: "+password);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Username or Secret Key ");
                    tx1.setText("");
                    tx2.setText("");
                }

            }

            if(e.getActionCommand()=="Return"){
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
