package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminLogin extends JFrame {
    JLabel l2,l3,l4;
    JTextField tx1,tx2;
    JButton b2,b3;

    AdminLogin(){
        setSize(600,600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("src/2001.png")));

        l2=new JLabel("Enter Your User Name: ");
        l2.setForeground(Color.WHITE);
        tx1=new JTextField(null,20);

        Dimension sizeofuser=l2.getPreferredSize();

        l3=new JLabel("Enter your Password");
        l3.setForeground(Color.WHITE);
        tx2=new JTextField(null,20);
        Dimension sizeofpasswordlabel=l3.getPreferredSize();

        l4=new JLabel("For Demo: Admin Username & password is: admin ");
        l4.setBounds(170,200,350,30);
        l4.setForeground(Color.black);

        Dimension size1= tx1.getPreferredSize();
        Dimension size2=tx2.getPreferredSize();


        l2.setBounds(100,250,sizeofuser.width,sizeofuser.height);
        l3.setBounds(100,310,sizeofpasswordlabel.width,sizeofpasswordlabel.height);
        tx1.setBounds(300,250,size1.width,size1.height);
        tx2.setBounds(300,310,size2.width,size2.height);

        b2=new JButton("Login");
        b3=new JButton("User Login");


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
                b3.setBackground(Color.DARK_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b3.setBackground(UIManager.getColor("control"));
                b3.setForeground(Color.black);
            }
        });

        Dimension loginbu=b2.getPreferredSize();
        Dimension fg_pass_d=b3.getPreferredSize();

        b2.setBounds(200,430,loginbu.width,loginbu.height);
        b3.setBounds(300,430,fg_pass_d.width,fg_pass_d.height);

        myActionListener m=new myActionListener();
        b2.addActionListener(m);
        b3.addActionListener(m);
        add(l2);
        add(l3);
        add(l4);
        add(tx1);
        add(tx2);
        add(b2);
        add(b3);

        setVisible(true);




    }
    public class myActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="Login"){
                if(tx1.getText().equals("admin") && tx2.getText().equals("admin")){
                    dispose();
                 Admin a=new Admin();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Username or Password");
                    tx2.setText("");
                    tx1.setText("");
                }
            }
            if(e.getActionCommand()=="User Login"){
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
