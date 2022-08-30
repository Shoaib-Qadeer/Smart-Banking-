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

public class Transaction_Donation extends JFrame {
    JLabel l1,sec;
    JTextField tx1;
    JButton b1,b2;
    JRadioButton eidhi,darul_sukun,pchf,EHSAS;
    static int user_Acc;

    Transaction_Donation(){
        setSize(600,600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("src/transaction_donation.GIF")));

        l1=new JLabel("Enter Amount to Donate: ");

        sec=new JLabel("Select Organization:");
        Dimension dim_sec=sec.getPreferredSize();
        sec.setBounds(100,200,dim_sec.width,dim_sec.height);

        Dimension sizeofound=l1.getPreferredSize();
        l1.setBounds(100,250,sizeofound.width,sizeofound.height);

        b1=new JButton("Donate");
        b2=new JButton("Return");

        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b1.setForeground(Color.WHITE);
                b1.setBackground(Color.GREEN);
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



        b1.setBounds(190,390,sendmoney_bu.width,sendmoney_bu.height);
        b2.setBounds(320,390,return_bu.width,return_bu.height);

        eidhi=new JRadioButton("Eidhi");
        darul_sukun=new JRadioButton("Darul Sukun");
        pchf=new JRadioButton("PCHF");
        EHSAS=new JRadioButton("EHSAS");

        eidhi.setForeground(Color.WHITE);
        darul_sukun.setForeground(Color.WHITE);
        pchf.setForeground(Color.WHITE);
        EHSAS.setForeground(Color.WHITE);

        ButtonGroup bg=new ButtonGroup();
        bg.add(eidhi);
        bg.add(darul_sukun);
        bg.add(pchf);
        bg.add(EHSAS);


        Dimension sizeofeidhi=eidhi.getPreferredSize();
        eidhi.setForeground(Color.white);
        eidhi.setBackground(Color.black);
        eidhi.setBounds(240,200,sizeofeidhi.width,sizeofeidhi.height);


        Dimension sizeofpchf=pchf.getPreferredSize();
        pchf.setForeground(Color.white);
        pchf.setBackground(Color.black);
        pchf.setBounds(300,200,sizeofpchf.width,sizeofpchf.height);

        Dimension sizeofdarul_sukun=darul_sukun.getPreferredSize();
        darul_sukun.setForeground(Color.white);
        darul_sukun.setBackground(Color.black);
        darul_sukun.setBounds(370,200,sizeofdarul_sukun.width,sizeofdarul_sukun.height);


        Dimension sizeofEHSAS=EHSAS.getPreferredSize();
        EHSAS.setForeground(Color.white);
        EHSAS.setBackground(Color.black);
        EHSAS.setBounds(480,200,sizeofEHSAS.width,sizeofEHSAS.height);




        tx1=new JTextField(null,20);
        Dimension size1= tx1.getPreferredSize();
        tx1.setBounds(240,250,size1.width,size1.height);
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

        donation_action m=new donation_action();
        b1.addActionListener(m);
        b2.addActionListener(m);
        b1.setBackground(Color.white);
        b2.setBackground(Color.white);

        add(b1);
        add(b2);
        add(l1);
        add(sec);
        add(eidhi);
        add(darul_sukun);
        add(pchf);
        add(EHSAS);
        add(tx1);
        setVisible(true);

    }

    public class donation_action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="Donate"){
                if(eidhi.isSelected() || pchf.isSelected() || EHSAS.isSelected() || darul_sukun.isSelected()){
                    if(tx1.getText().length()>0){
                        String found="";
                        if(eidhi.isSelected()){
                            found="Eidhi";
                        }
                        else if(EHSAS.isSelected()){
                            found="EHSAS";
                        }
                        else if(pchf.isSelected()){
                            found="PCHF";
                        }
                        else if(darul_sukun.isSelected()){
                            found="Darul Sukun";
                        }

                        try {
                            Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                            PreparedStatement get_wit=con2.prepareStatement("select account_balance from account where account_id=?");
                            get_wit.setInt(1,user_Acc);
                            ResultSet set_fund=get_wit.executeQuery();
                            set_fund.next();
                            int current_balance=set_fund.getInt(1);

                            if(Integer.parseInt(tx1.getText())>current_balance){

                                JOptionPane.showMessageDialog(null,"Insufficient Balance");
                                tx1.setText("");
                            }
                            else{
                                current_balance=current_balance-Integer.parseInt(tx1.getText());
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


                                Transaction txn=new Transaction("Donation","Debit",user_Acc,"Donation PKR "+tx1.getText()+" has given to "+found);
                                get_wit=con2.prepareStatement("Insert into donation values (?,?,?)");
                                get_wit.setInt(1,txn.getTransaction_id());
                                get_wit.setString(2,found);
                                get_wit.setInt(3,Integer.parseInt(tx1.getText()));
                                get_wit.execute();

                                String toshow="Txn#"+txn.getTransaction_id()+" Donation of amount PKR"+tx1.getText()+" has given to "+found+" at "+txn.getDatentime()+" Remaining balance PKR "+current_balance;
                                JOptionPane.showMessageDialog(null,toshow);
                                tx1.setText("");

                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Please Enter Amount to Donate ");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"First Select Organization to Donate");
                }
            }
            if(e.getActionCommand()=="Return"){
                dispose();
                Transaction_GUI m=new Transaction_GUI();
            }

        }
    }


}
