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
import java.util.concurrent.ThreadLocalRandom;

public class Transaction_bill_payment extends JFrame {
    JLabel l1,l2,l3;
    JLabel sec;
    JTextField tx1,tx2;
    JButton b1,b2,getbill;
    JRadioButton electricity,internet,gass,water;
    static int user_Acc;

    Transaction_bill_payment(){
        setSize(600,600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("src/transaction_billpay.PNG")));


        sec=new JLabel("First Click on Get bill after entering details \n and then click paybill to pay");
        sec.setForeground(Color.WHITE);
        Dimension dim_sec=sec.getPreferredSize();
        sec.setBounds(100,370,dim_sec.width,dim_sec.height);
        sec.setVisible(true);

        l1=new JLabel("Enter Bill Reference: ");

        l2=new JLabel("Payable Bill Amount:");
        Dimension sizeofamount=l2.getPreferredSize();
        Dimension sizeofreciever=l1.getPreferredSize();



        l3=new JLabel("Select Bill Type:");
        Dimension sizeofoperator=l1.getPreferredSize();
        l3.setBounds(100,200,sizeofoperator.width,sizeofoperator.height);


        l1.setBounds(100,250,sizeofreciever.width,sizeofreciever.height);
        l2.setBounds(100,310,sizeofamount.width,sizeofamount.height);

        b1=new JButton("Pay Bill");
        b2=new JButton("Return");
        getbill=new JButton("Get Bill");



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
                b2.setBackground(Color.RED);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b2.setBackground(Color.white);
                b2.setForeground(Color.black);
            }
        });

        getbill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                getbill.setForeground(Color.WHITE);
                getbill.setBackground(Color.DARK_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                getbill.setBackground(Color.white);
                getbill.setForeground(Color.black);
            }
        });
        getbill.setBackground(Color.white);
        b1.setBackground(Color.white);
        b2.setBackground(Color.white);

        Dimension paybill_bu=b1.getPreferredSize();
        Dimension return_bu=b2.getPreferredSize();
        Dimension dim_getbill=getbill.getPreferredSize();




        b1.setBounds(260,430,paybill_bu.width,paybill_bu.height);
        b2.setBounds(370,430,return_bu.width,return_bu.height);
        getbill.setBounds(140,430,dim_getbill.width,dim_getbill.height);


        electricity=new JRadioButton("Electricity");
        internet=new JRadioButton("Internet");
        gass=new JRadioButton("Gas");
        water=new JRadioButton("Water");

        electricity.setForeground(Color.WHITE);
        internet.setForeground(Color.WHITE);
        gass.setForeground(Color.WHITE);
        water.setForeground(Color.WHITE);

        ButtonGroup bg=new ButtonGroup();
        bg.add(electricity);
        bg.add(internet);
        bg.add(gass);
        bg.add(water);


        Dimension sizeofelectricity=electricity.getPreferredSize();
        electricity.setForeground(Color.white);
        electricity.setBackground(Color.black);
        electricity.setBounds(210,200,sizeofelectricity.width,sizeofelectricity.height);

        Dimension sizeofwater=water.getPreferredSize();
        water.setForeground(Color.white);
        water.setBackground(Color.black);
        water.setBounds(320,200,sizeofwater.width,sizeofwater.height);

        Dimension sizeofgass=gass.getPreferredSize();
        gass.setForeground(Color.white);
        gass.setBackground(Color.black);
        gass.setBounds(400,200,sizeofgass.width,sizeofgass.height);

        Dimension sizeofinternet=internet.getPreferredSize();
        internet.setForeground(Color.white);
        internet.setBackground(Color.black);
        internet.setBounds(470,200,sizeofinternet.width,sizeofinternet.height);







        tx1=new JTextField(null,20);
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
        tx2=new JTextField(null,20);
        tx2.setEditable(false);
        Dimension size1= tx1.getPreferredSize();
        Dimension size2=tx2.getPreferredSize();
        tx1.setBounds(260,250,size1.width,size1.height);
        tx2.setBounds(260,310,size2.width,size2.height);


        bill_action m=new bill_action();
        b1.addActionListener(m);
        b2.addActionListener(m);
        getbill.addActionListener(m);



        add(b1);
        add(l3);
        add(b2);
        add(l1);
        add(l2);
        add(electricity);
        add(internet);
        add(gass);
        add(water);
        add(tx2);
        add(tx1);
        add(sec);
        add(getbill);
        setVisible(true);

    }

    public class bill_action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="Get Bill"){
                if(electricity.isSelected() || internet.isSelected() || gass.isSelected() || water.isSelected()){
                    if(tx1.getText().length()>=1 ){
                        int randomNum = ThreadLocalRandom.current().nextInt(1000, 4999 + 1);
                        tx2.setText(randomNum+"");
                    }
                    else {
                        JOptionPane.showMessageDialog(null," Enter Reference Id");
                    }

                }
                else {
                    JOptionPane.showMessageDialog(null,"First Select Bill type ");
                    tx1.setText("");
                }

            }
            if(e.getActionCommand()=="Pay Bill"){

                if(tx1.getText().length()>=1 ){
                        String billtype="";
                        if(electricity.isSelected()){billtype="Electricity";}
                        else if(gass.isSelected()){billtype="Gas";}
                        else if(water.isSelected()){billtype="Water";}
                        else if(internet.isSelected()){billtype="Internet";}

                  if (billtype.length()>0){
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

                              Transaction txn=new Transaction("Bill Payment","Debit",user_Acc,billtype+"Bill of PKR "+tx2.getText()+" has been paid against Reference Id "+tx1.getText());
                              get_wit=con2.prepareStatement("insert into BILL_Payment values (?,?,?,?)");
                              get_wit.setInt(1,txn.getTransaction_id());
                              get_wit.setString(2,billtype);
                              get_wit.setInt(3,Integer.parseInt(tx1.getText()));
                              get_wit.setInt(4,Integer.parseInt(tx2.getText()));
                              get_wit.execute();

                              String toshow="Trx#"+txn.getTransaction_id()+billtype+"Bill of PKR "+tx2.getText()+" has been paid against Reference Id "+tx1.getText()+" on "+txn.getDatentime()+" Remaining Account Balance PKR"+current_balance;
                                JOptionPane.showMessageDialog(null,toshow);
                              tx1.setText("");
                              tx2.setText("");

                          }


                      } catch (SQLException throwables) {
                          throwables.printStackTrace();
                      }

                  }
                  else{
                      JOptionPane.showMessageDialog(null,"Select Bill Type ");
                  }




                }
                else{
                    JOptionPane.showMessageDialog(null,"Enter Reference Id First");
                    tx2.setText("");
                    tx1.setText("");
                }

            }
            if(e.getActionCommand()=="Return"){
                dispose();
                Transaction_GUI m=new Transaction_GUI();
            }



        }



    }
}

