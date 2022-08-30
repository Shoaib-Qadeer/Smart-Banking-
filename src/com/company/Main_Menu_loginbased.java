package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.sql.*;
import java.util.ArrayList;

public class Main_Menu_loginbased extends JFrame  {
    JLabel l1,l2,l3;
    JTextField tx1;
    JPasswordField tx2;
    JButton b1,b2,b3,b4, close_button;
  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "bankapp", "05822");
    Statement stt =  con.createStatement();


    Main_Menu_loginbased() throws SQLException {
        setSize(600,600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("src/main login.GIF")));


        l1=new JLabel("For Admin Login:  ");
        l1.setForeground(Color.WHITE);
        Dimension adlab=l1.getPreferredSize();
        l1.setBounds(200,30,adlab.width,adlab.height);

        close_button=new JButton("X");
        close_button.setBackground(Color.RED);
        close_button.setForeground(Color.white);
        Dimension db_cld=close_button.getPreferredSize();
        close_button.setBounds(550,10,db_cld.width,db_cld.height);

        b1=new JButton("Admin Login");
        Dimension adminbutton=b1.getPreferredSize();
        b1.setForeground(Color.black);
        b1.setBounds(300,30,adminbutton.width,adminbutton.height);
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


        l2=new JLabel("Enter Your User Name: ");
        l2.setForeground(Color.WHITE);
        tx1=new JTextField(null,20);

        Dimension sizeofuser=l2.getPreferredSize();

        l3=new JLabel("Enter your Password");
        l3.setForeground(Color.WHITE);
        tx2=new JPasswordField(null,20);
        Dimension sizeofpasswordlabel=l3.getPreferredSize();




        Dimension size1= tx1.getPreferredSize();
        Dimension size2=tx2.getPreferredSize();


        l2.setBounds(100,250,sizeofuser.width,sizeofuser.height);
        l3.setBounds(100,310,sizeofpasswordlabel.width,sizeofpasswordlabel.height);
        tx1.setBounds(300,250,size1.width,size1.height);
        tx2.setBounds(300,310,size2.width,size2.height);



        b2=new JButton("Login");
        b3=new JButton("Forgot Password?");


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

        b3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b3.setForeground(Color.WHITE);
                b3.setBackground(Color.RED);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b3.setBackground(UIManager.getColor("control"));
                b3.setForeground(Color.black);
            }
        });


        b4=new JButton("Signup");
        Dimension dim_sign=b4.getPreferredSize();
        b4.setBounds(250,500,dim_sign.width,dim_sign.height);
        b4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b4.setForeground(Color.WHITE);
                b4.setBackground(Color.blue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b4.setBackground(UIManager.getColor("control"));
                b4.setForeground(Color.black);
            }
        });

        Dimension loginbu=b2.getPreferredSize();
        Dimension fg_pass_d=b3.getPreferredSize();


        b2.setBounds(200,430,loginbu.width,loginbu.height);
        b3.setBounds(300,430,fg_pass_d.width,fg_pass_d.height);

        add(l1);add(l2);add(l3);add(b1);add(b2);add(b3);
        add(tx1); add(tx2);
        add(b4);
        add(close_button);

        myActionListener j=new myActionListener();
        b2.addActionListener(j);
        b3.addActionListener(j);
        b1.addActionListener(j);
        b4.addActionListener(j);
        close_button.addActionListener(j);



        setVisible(true);


    }





    public class myActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="Admin Login"){
                dispose();
                AdminLogin m=new AdminLogin();
            }

            if(e.getActionCommand()=="Login"){
               if (tx1.getText().length()>0 ){
                   boolean found= false;
                   ResultSet res=null;
                   try {
                       Connection   con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                       PreparedStatement pst = con.prepareStatement("select customer_id, username, password from customer where username=? and password=?");
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
                           PreparedStatement pset = con.prepareStatement("select account_id from account where  CUSTOMER_CUSTOMER_ID=?");
                           pset.setInt(1,Integer.parseInt(res.getString(1)));
                           ResultSet nm=pset.executeQuery();
                           nm.next();
                           UserMainFrame.customer_index=res.getInt(1);
                           UserMainFrame.acc_no=nm.getInt(1);
                       } catch (SQLException throwables) {
                           throwables.printStackTrace();
                       }

                       dispose();

                       UserMainFrame m=new UserMainFrame();
                   }
                   else {
                       JOptionPane.showMessageDialog(null,"Incorrect UserName or Password ");
                       tx2.setText("");
                       tx1.setText("");
                   }
               }
               else{
                   JOptionPane.showMessageDialog(null,"First Enter Requried Details");
               }
            }

            if(e.getActionCommand()=="Forgot Password?"){
                dispose();
                Forgot_Password n=new Forgot_Password();
            }

            if(e.getActionCommand()=="Signup"){
                dispose();
                Signup_GUI m=new Signup_GUI();
            }
            if(e.getActionCommand()=="X"){
                dispose();
            }


        }



    }

    public boolean user_found(String unam,String passd) throws SQLException {
        boolean rec_found=false;
        PreparedStatement pstmt=con.prepareStatement("Select  CUSTOMER_ID  from customer where username=? and password=?");
        pstmt.setString(1,unam);
        pstmt.setString(2,passd);

        ResultSet rs =pstmt.executeQuery();
        if(!rs.next()) {
                rec_found=true;
        }

        return rec_found;
    }






}
