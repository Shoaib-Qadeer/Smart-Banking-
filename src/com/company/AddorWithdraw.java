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

public class AddorWithdraw extends JFrame {
    JLabel l1,l2,l3;
    JTextField tx1,tx2;
    JButton b1,b2;
    JRadioButton addmount,wi_amount;
    static int user_Acc;

    AddorWithdraw(){
        setSize(600,600);
        setLayout(null);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(new JLabel(new ImageIcon("src/addorwith.GIF")));


        l1=new JLabel("Enter Amount : ");
        Dimension sizeofound=l1.getPreferredSize();
        l1.setBounds(100,250,sizeofound.width,sizeofound.height);

        l2=new JLabel("Select option : ");
        Dimension sizeoftype=l1.getPreferredSize();
        l2.setBounds(100,200,sizeoftype.width,sizeoftype.height);



        b1=new JButton("Add/Withdraw Funds");
        b2=new JButton("Return");



        addmount=new JRadioButton("Add Amount");
        wi_amount=new JRadioButton("Withdraw Funds");

        addmount.setForeground(Color.WHITE);
        wi_amount.setForeground(Color.WHITE);

        ButtonGroup bg=new ButtonGroup();
        bg.add(addmount);
        bg.add(wi_amount);

        Dimension sizeofadd=addmount.getPreferredSize();
        addmount.setForeground(Color.white);
        addmount.setBackground(Color.black);
        addmount.setBounds(210,200,sizeofadd.width,sizeofadd.height);

        Dimension sizeofwith=wi_amount.getPreferredSize();
        wi_amount.setForeground(Color.white);
        wi_amount.setBackground(Color.black);
        wi_amount.setBounds(310,200,sizeofwith.width,sizeofwith.height);

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

        b1.setBackground(Color.white);
        b2.setBackground(Color.white);
        Dimension addamount_bu=b1.getPreferredSize();
        Dimension return_bu=b2.getPreferredSize();

        b1.setBounds(190,330,addamount_bu.width,addamount_bu.height);
        b2.setBounds(360,330,return_bu.width,return_bu.height);
        tx1=new JTextField(null,20);
        Dimension size1= tx1.getPreferredSize();
        tx1.setBounds(240,250,size1.width,size1.height);
        tx1.addKeyListener(new KeyListener() {
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

        l3=new JLabel("Enter Source/Purpose : ");
        Dimension sizeosource=l3.getPreferredSize();
        l3.setBounds(100,290,sizeosource.width,sizeosource.height);

        tx2=new JTextField(null,20);
        Dimension size2= tx2.getPreferredSize();
        tx2.setBounds(240,290,size2.width,size2.height);


        addwith_action m=new addwith_action();
        b1.addActionListener(m);
        b2.addActionListener(m);
        b1.setBackground(Color.white);
        b2.setBackground(Color.white);
        add(l1);
        add(l2);
        add(l3);
        add(tx2);
        add(tx1);
        add(wi_amount);
        add(addmount);
        add(tx1);
        add(b1);
        add(b2);
        setVisible(true);
    }

    public class addwith_action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="Add/Withdraw Funds"){
                if(addmount.isSelected()|| wi_amount.isSelected()){
                    if(tx1.getText().length()>0 ){
                        if (tx2.getText().length()>0){
                            if(addmount.isSelected()){
                                try {
                                    String trn_det="PKR "+tx1.getText()+" has added to account";
                                    Transaction trnx=new Transaction("Add Funds","Credit",user_Acc,trn_det);
                                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                                    PreparedStatement pstmt=con.prepareStatement("Insert into ADD_FUNDS values (?,?,?)");
                                    pstmt.setInt(1,trnx.getTransaction_id());
                                    pstmt.setInt(2,Integer.parseInt(tx1.getText()));
                                    pstmt.setString(3,tx2.getText());
                                    pstmt.execute();

                                    Connection con3 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                                    PreparedStatement get_bal=con3.prepareStatement("select account_balance from account where account_id=?");
                                    get_bal.setInt(1,user_Acc);
                                    ResultSet set_fund=get_bal.executeQuery();
                                    set_fund.next();
                                    int c_balance=set_fund.getInt(1);
                                    c_balance=c_balance+Integer.parseInt(tx1.getText());

                                    PreparedStatement add_fd=con.prepareStatement("UPDATE account SET account_balance = ? WHERE account_id=?");
                                    add_fd.setInt(1,c_balance);
                                    add_fd.setInt(2,user_Acc);
                                    add_fd.execute();
                                    // status to update account_level
                                    String status="";
                                    if (c_balance>=20000){
                                        status="Premium";
                                    }
                                    else{
                                        status="Normal";
                                    }
                                    PreparedStatement set_level=con.prepareStatement("UPDATE account SET account_level = ? WHERE account_id=?");
                                    set_level.setString(1,status);
                                    set_level.setInt(2,user_Acc);
                                    set_level.execute();

                                    String toshow="Trxn Id #"+trnx.getTransaction_id()+"- PKR "+tx1.getText()+"Has been added to account on "+trnx.getDatentime()+" New balance: "+c_balance;
                                    JOptionPane.showMessageDialog(null, toshow);
                                    tx1.setText("");
                                    tx2.setText("");




                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }


                            }

                            else if(wi_amount.isSelected()){
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
                                        String trn_det="PKR "+tx1.getText()+" has withdrawn from account";
                                        Transaction trnx1=new Transaction("Withdraw","Debit",user_Acc,trn_det);
                                        PreparedStatement pst=con2.prepareStatement("Insert into WITHDRAW values (?,?,?)");
                                        pst.setInt(1,trnx1.getTransaction_id());
                                        pst.setInt(2,Integer.parseInt(tx1.getText()));
                                        pst.setString(3,tx2.getText());
                                        pst.execute();



                                        PreparedStatement wid_fd=con2.prepareStatement("UPDATE account SET account_balance = ? WHERE account_id=?");
                                       int d_balance=current_balance-Integer.parseInt(tx1.getText());
                                        wid_fd.setInt(1,d_balance);
                                        wid_fd.setInt(2,user_Acc);
                                        wid_fd.execute();


                                        String status="";
                                        if (d_balance>20000){
                                            status="Premium";
                                        }
                                        else{
                                            status="Normal";
                                        }
                                        PreparedStatement set_level=con2.prepareStatement("UPDATE account SET account_level = ? WHERE account_id=?");
                                        set_level.setString(1,status);
                                        set_level.setInt(2,user_Acc);
                                        set_level.execute();
                                        String toshow="Trxn Id #"+trnx1.getTransaction_id()+"- PKR "+tx1.getText()+"Has been withdrawn fro account on "+trnx1.getDatentime()+" New balance: "+d_balance;
                                        JOptionPane.showMessageDialog(null, toshow);
                                        tx1.setText("");
                                        tx2.setText("");

                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }

                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Please Provide Source/Purpose");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Enter Amount first");
                    }
                }
                else{JOptionPane.showMessageDialog(null,"Select Type Add or Withdraw");}
            }
            if(e.getActionCommand()=="Return"){
                dispose();
                Transaction_GUI m=new Transaction_GUI();
            }
        }}
}
