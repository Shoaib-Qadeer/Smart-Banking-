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

public class Transaction_Order_Card extends JFrame {
    JLabel l1,l2;
    JTextField tx1;
    JButton b1,b2;
    static int user_Acc;

    Transaction_Order_Card(){
        setSize(600,600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("src/transaction_order_credit.GIF")));


        l1=new JLabel("Enter Amount : ");
        l1.setForeground(Color.WHITE);
        Dimension sizeofound=l1.getPreferredSize();
        l1.setBounds(100,200,sizeofound.width,sizeofound.height);

        l2=new JLabel("");
        Dimension siz_of_card=l2.getPreferredSize();
        l2.setBounds(100,230,siz_of_card.width,siz_of_card.height);

        b1=new JButton("Get Card");
        b2=new JButton("Return");

        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b1.setForeground(Color.WHITE);
                b1.setBackground(Color.DARK_GRAY);
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


        Dimension order_card_bu=b1.getPreferredSize();
        Dimension return_bu=b2.getPreferredSize();

        b1.setBounds(190,330,order_card_bu.width,order_card_bu.height);
        b2.setBounds(320,330,return_bu.width,return_bu.height);
        tx1=new JTextField(null,20);
        Dimension size1= tx1.getPreferredSize();
        tx1.setBounds(240,200,size1.width,size1.height);
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

        virtual_card_action m=new virtual_card_action();
        b1.addActionListener(m);
        b2.addActionListener(m);

        b1.setBackground(Color.white);
        b2.setBackground(Color.white);


        add(l1);
        add(l2);
        add(b1);
        add(tx1);
        add(b2);

        setVisible(true);
    }

    public class virtual_card_action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Get Card") {

                try {
                    Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                    PreparedStatement get_wit=con2.prepareStatement("select account_balance from account where account_id=?");
                    get_wit.setInt(1,user_Acc);
                    ResultSet set_fund=get_wit.executeQuery();
                    set_fund.next();
                    int current_balance=set_fund.getInt(1);

                    if (tx1.getText().length() >=1) {

                        if (Integer.parseInt(tx1.getText())>current_balance) {
                            JOptionPane.showMessageDialog(null, "Insufficient Balance");
                            tx1.setText("");
                        } else {

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

                            int randomNum1 = ThreadLocalRandom.current().nextInt(4200, 4998 + 1);
                            int randomNum2 = ThreadLocalRandom.current().nextInt(1000, 4999 + 1);
                            int randomNum3 = ThreadLocalRandom.current().nextInt(1000, 4999 + 1);
                            int randomNum4 = ThreadLocalRandom.current().nextInt(1000, 4999 + 1);
                            int cvv = ThreadLocalRandom.current().nextInt(100, 998 + 1);
                            int mon = ThreadLocalRandom.current().nextInt(1, 11 + 1);
                            int year = ThreadLocalRandom.current().nextInt(15, 35 + 1);
                            String cardno=randomNum1+" "+randomNum2+" "+randomNum3+" "+randomNum2;
                            String expiry=mon+"/"+year;

                            String card_detail="\nCard Details: "+randomNum1+" "+randomNum2+" "+randomNum3+" "+randomNum4+"        CVV: "+cvv+"         Exp: "+mon+"/"+year;

                            l2.setText(card_detail);
                            l2.setForeground(Color.WHITE);
                            l2.setBackground(Color.darkGray);
                            Dimension siz_of_card=l2.getPreferredSize();
                            l2.setBounds(100,250,siz_of_card.width,siz_of_card.height);

                            Transaction txn=new Transaction("Virtual Credit Card","Debit",user_Acc,"VCC of worth "+tx1.getText()+" with Exp: "+mon+"/"+year);
                            get_wit=con2.prepareStatement("Insert into virtual_card values (?,?,?,?,?)");
                            get_wit.setInt(1,txn.getTransaction_id());
                            get_wit.setInt(2,Integer.parseInt(tx1.getText()));
                            get_wit.setString(3,cardno);
                            get_wit.setString(4,String.valueOf(cvv));
                            get_wit.setString(5,expiry);
                            get_wit.execute();



                            String toshow= "Trxn#"+txn.getTransaction_id()+ " Virtual Credit Card of worth Rs: " + tx1.getText() + " PKR ending with "+ randomNum4+" has been generated at " +txn.getDatentime()+" Remaining balance PKR "+current_balance;
                           JOptionPane.showMessageDialog(null,toshow);
                            tx1.setText("");

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "First Enter Amount ");
                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
            if(e.getActionCommand()=="Return"){
                dispose();
                Transaction_GUI m=new Transaction_GUI();
            }

        }


    }
}
