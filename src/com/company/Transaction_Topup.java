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

public class Transaction_Topup extends JFrame {
    JLabel l1,l2,l3;
    JTextField tx1,tx2;
    JButton b1,b2;
    JRadioButton jazz,zong,ufone,warid,telenor;
    static int user_Acc;

    Transaction_Topup(){
        setSize(600,600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("src/Transaction_Topup_Back.GIF")));

        l1=new JLabel("Enter Mobile No: ");
        l1.setForeground(Color.WHITE);
        l2=new JLabel("Enter Amount:");
        l2.setForeground(Color.WHITE);
        Dimension sizeofamount=l2.getPreferredSize();
        Dimension sizeofreciever=l1.getPreferredSize();

        l3=new JLabel("Select Operator:");
        Dimension sizeofoperator=l1.getPreferredSize();
        l3.setForeground(Color.white);
        l3.setBounds(100,200,sizeofoperator.width,sizeofoperator.height);


        l1.setBounds(100,250,sizeofreciever.width,sizeofreciever.height);
        l2.setBounds(100,310,sizeofamount.width,sizeofamount.height);

        b1=new JButton("Topup");
        b2=new JButton("Return");
        b1.setBackground(Color.white);
        b2.setBackground(Color.white);

        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b1.setForeground(Color.WHITE);
                b1.setBackground(Color.BLUE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b1.setBackground(Color.white);
                b1.setForeground(Color.black);
            }
        });

        b2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b2.setForeground(Color.WHITE);
                b2.setBackground(Color.DARK_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b2.setBackground(Color.white);
                b2.setForeground(Color.black);
            }
        });

        Dimension sendmoney_bu=b1.getPreferredSize();
        Dimension return_bu=b2.getPreferredSize();



        b1.setBounds(170,430,sendmoney_bu.width,sendmoney_bu.height);
        b2.setBounds(300,430,return_bu.width,return_bu.height);

        jazz=new JRadioButton("Jazz");
        zong=new JRadioButton("Zong");
        ufone=new JRadioButton("Ufone");
        telenor=new JRadioButton("Telenor");
        warid=new JRadioButton("Warid");

        jazz.setForeground(Color.WHITE);
        zong.setForeground(Color.WHITE);
        ufone.setForeground(Color.WHITE);
        telenor.setForeground(Color.WHITE);
        warid.setForeground(Color.WHITE);

        ButtonGroup bg=new ButtonGroup();
        bg.add(jazz);
        bg.add(zong);
        bg.add(ufone);
        bg.add(telenor);
        bg.add(warid);


        Dimension sizeofjazz=jazz.getPreferredSize();
        jazz.setForeground(Color.white);
        jazz.setBackground(Color.black);
        jazz.setBounds(210,200,sizeofjazz.width,sizeofjazz.height);

        Dimension sizeoftelenor=telenor.getPreferredSize();
        telenor.setForeground(Color.white);
        telenor.setBackground(Color.black);
        telenor.setBounds(280,200,sizeoftelenor.width,sizeoftelenor.height);

        Dimension sizeofufone=ufone.getPreferredSize();
        ufone.setForeground(Color.white);
        ufone.setBackground(Color.black);
        ufone.setBounds(360,200,sizeofufone.width,sizeofufone.height);

        Dimension sizeofzong=zong.getPreferredSize();
        zong.setForeground(Color.white);
        zong.setBackground(Color.black);
        zong.setBounds(430,200,sizeofzong.width,sizeofzong.height);


        Dimension sizeofwarid=warid.getPreferredSize();
        warid.setForeground(Color.white);
        warid.setBackground(Color.black);
        warid.setBounds(500,200,sizeofwarid.width,sizeofwarid.height);




        tx1=new JTextField(null,20);
        tx2=new JTextField(null,20);

        tx1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                    getToolkit().beep();
                }
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });
        tx2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                    getToolkit().beep();
                }
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });
        Dimension size1= tx1.getPreferredSize();
        Dimension size2=tx2.getPreferredSize();
        tx1.setBounds(260,250,size1.width,size1.height);
        tx2.setBounds(260,310,size2.width,size2.height);

        topup_action  m=new topup_action();
        b1.addActionListener(m);
        b2.addActionListener(m);

        add(b1);
        add(l3);
        add(b2);
        add(l1);
        add(l2);
        add(jazz);
        add(zong);
        add(ufone);
        add(warid);
        add(telenor);
        add(tx2);
        add(tx1);
        add(jazz);
        add(zong);
        add(ufone);
        add(telenor);
        add(warid);
        setVisible(true);

    }

    public class topup_action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="Topup"){
                if(zong.isSelected() || telenor.isSelected() || ufone.isSelected() || jazz.isSelected() || warid.isSelected()){
                    if(tx1.getText().length()>=1){
                        if(tx2.getText().length()>=1){
                            String operator="";
                            if(jazz.isSelected()){
                                operator="Jazz";
                            }
                            else if(zong.isSelected()){
                                operator="Zong";
                            }
                            else if(ufone.isSelected()){
                                operator="Ufone";
                            }
                            else if(telenor.isSelected()){
                                operator="Telenor";
                            }
                            else if(warid.isSelected()){
                                operator="Warid";
                            }

                            if(tx1.getText().matches("\\d{11}")){

                                try {
                                    Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                                    PreparedStatement get_wit=con2.prepareStatement("select account_balance from account where account_id=?");
                                    get_wit.setInt(1,user_Acc);
                                    ResultSet set_fund=get_wit.executeQuery();
                                    set_fund.next();
                                    int current_balance=set_fund.getInt(1);



                                    if(Integer.parseInt(tx2.getText())>current_balance){

                                        JOptionPane.showMessageDialog(null,"Insufficient Balance");
                                    }
                                    else{

                                        current_balance=current_balance-Integer.parseInt(tx2.getText());
                                        get_wit=con2.prepareStatement("UPDATE account SET account_balance = ? WHERE account_id=?");
                                        get_wit.setInt(1,current_balance);
                                        get_wit.setInt(2,user_Acc);
                                        get_wit.execute();

                                        String status="";
                                        if (current_balance>=20000){
                                            status="Premium";
                                        }
                                        else{
                                            status="Normal";
                                        }
                                        PreparedStatement set_level=con2.prepareStatement("UPDATE account SET account_level = ? WHERE account_id=?");
                                        set_level.setString(1,status);
                                        set_level.setInt(2,user_Acc);
                                        set_level.execute();

                                        Transaction txn=new Transaction("Mobile Topup ","Debit",user_Acc,"Mobiile Topup PKR"+tx2.getText()+" has sent to no "+tx1.getText());

                                        get_wit=con2.prepareStatement("Insert into mobile_topup values (?,?,?,?)");
                                        get_wit.setInt(1,txn.getTransaction_id());
                                        get_wit.setInt(2,Integer.parseInt(tx2.getText()));
                                        get_wit.setString(3,tx1.getText());
                                        get_wit.setString(4,operator);
                                        get_wit.execute();

                                        String to_show="Txn#"+txn.getTransaction_id()+"PKR "+tx2.getText()+" Mobile Topup has been sent to "+operator+" number: "+tx1.getText()+" on : "+txn.getDatentime();
                                        JOptionPane.showMessageDialog(null,  to_show);
                                        tx1.setText("");
                                        tx2.setText("");

                                    }


                                } catch (SQLException throwables) {
                                    JOptionPane.showMessageDialog(null,throwables.toString());
                                }


                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Invalid Mobile No Format Enter 11 digit number without spaces");
                                tx1.setText("");
                                tx2.setText("");
                            }

                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Enter Amount First ");

                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"First Enter Mobile Number");
                        tx2.setText("");
                        tx1.setText("");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"First Select Sim Operator");
                    tx1.setText("");
                    tx2.setText("");
                }
            }
            if(e.getActionCommand()=="Return"){
                dispose();
                Transaction_GUI m=new Transaction_GUI();
            }
        }
    }
}
