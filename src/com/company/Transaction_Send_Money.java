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

public class Transaction_Send_Money extends JFrame {
    JLabel l1,l2;
    JTextField tx1,tx2;
    JButton b1,b2;
    static int user_Acc;

    Transaction_Send_Money(){
        setSize(600,600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("src/fundstransfer.GIF")));

        l1=new JLabel("Enter Receiver Account No: ");
        l2=new JLabel("Enter Amount: ");
        l1.setBackground(Color.white);
        l2.setBackground(Color.white);
        Dimension sizeofamount=l2.getPreferredSize();
        Dimension sizeofreciever=l1.getPreferredSize();

        l1.setBounds(100,250,sizeofreciever.width,sizeofreciever.height);
        l2.setBounds(100,310,sizeofamount.width,sizeofamount.height);

        b1=new JButton("Send Money");
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
        tx1.setBounds(300,250,size1.width,size1.height);
        tx2.setBounds(300,310,size2.width,size2.height);

        add(b1);
        add(b2);
        add(l1);
        add(l2);
        add(tx2);
        add(tx1);

        action_sendmoney m=new action_sendmoney();
        b1.addActionListener(m);
        b2.addActionListener(m);

        setVisible(true);
    }

    public class action_sendmoney implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="Send Money"){
                if (tx1.getText().length()>0 && tx2.getText().length()>0){

                    if(Integer.parseInt(tx1.getText())!=user_Acc){
                        boolean found=false;
                        ResultSet rct=null;
                        try {
                            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                            PreparedStatement pst = con.prepareStatement("select account_id from account where account_id=?");
                            pst.setInt(1,Integer.parseInt(tx1.getText()));
                             rct=pst.executeQuery();
                            if(!rct.next()){
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
                                int rcv_id=rct.getInt(1);
                                Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                                PreparedStatement get_wit=con2.prepareStatement("select account_balance from account where account_id=?");
                                get_wit.setInt(1,user_Acc);
                                ResultSet set_fund=get_wit.executeQuery();
                                set_fund.next();
                                int current_balance=set_fund.getInt(1);

                                if(Integer.parseInt(tx2.getText())>current_balance){
                                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                                    tx1.setText("");
                                    tx2.setText("");
                                }
                                else{

                                    PreparedStatement addtorcv=con2.prepareStatement("select account_balance from account where account_id=?");
                                    addtorcv.setInt(1,Integer.parseInt(tx1.getText()));
                                    ResultSet send_mon=addtorcv.executeQuery();
                                   send_mon.next();
                                    int rcv_cur_bal=send_mon.getInt(1);
                                    rcv_cur_bal=rcv_cur_bal+Integer.parseInt(tx2.getText());
                                    addtorcv=con2.prepareStatement("UPDATE account SET account_balance = ? WHERE account_id=?");
                                    addtorcv.setInt(1,rcv_cur_bal);
                                    addtorcv.setInt(2,Integer.parseInt(tx1.getText()));
                                    addtorcv.execute();

                                    String status="";
                                    if (rcv_cur_bal>=20000){
                                        status="Premium";
                                    }
                                    else{
                                        status="Normal";
                                    }
                                    PreparedStatement set_level=con2.prepareStatement("UPDATE account SET account_level = ? WHERE account_id=?");
                                    set_level.setString(1,status);
                                    set_level.setInt(2,Integer.parseInt(tx1.getText()));
                                    set_level.execute();

                                    PreparedStatement dedfromsend=con2.prepareStatement("select account_balance from account where account_id=?");
                                    dedfromsend.setInt(1,user_Acc);
                                    ResultSet ded_send_mon=dedfromsend.executeQuery();
                                    ded_send_mon.next();
                                    int send_cur_bal=ded_send_mon.getInt(1);
                                    send_cur_bal=send_cur_bal-Integer.parseInt(tx2.getText());
                                    dedfromsend=con2.prepareStatement("UPDATE account SET account_balance = ? WHERE account_id=?");
                                    dedfromsend.setInt(1,send_cur_bal);
                                    dedfromsend.setInt(2,user_Acc);
                                    dedfromsend.execute();

                                    status="";
                                    if (send_cur_bal>=20000){
                                        status="Premium";
                                    }
                                    else{
                                        status="Normal";
                                    }
                                    PreparedStatement send_level=con2.prepareStatement("UPDATE account SET account_level = ? WHERE account_id=?");
                                    send_level.setString(1,status);
                                    send_level.setInt(2,user_Acc);
                                    send_level.execute();

                                    Transaction sendmoneytrxn=new Transaction("Funds Transfer","Debit",user_Acc,"PKR"+tx2.getText()+" send to account "+tx1.getText());

                                    get_wit=con2.prepareStatement("Insert into funds_transfer values (?,?,?)");
                                    get_wit.setInt(1,sendmoneytrxn.getTransaction_id());
                                    get_wit.setInt(2,Integer.parseInt(tx1.getText()));
                                    get_wit.setInt(3,Integer.parseInt(tx2.getText()));
                                    get_wit.execute();
                                    String to_show="Txn#"+sendmoneytrxn.getTransaction_id()+" PkR "+tx2.getText()+" has been transferred to "+tx1.getText()+" at "+sendmoneytrxn.getDatentime()+" Remaining balance is PKR"+send_cur_bal;
tx1.setText("");
tx2.setText("");
                                    JOptionPane.showMessageDialog(null,to_show);

                                }





                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                                JOptionPane.showMessageDialog(null,throwables.toString());
                            }
                        }
                        else{

                            JOptionPane.showMessageDialog(null,"No Account with this Id found");
                            tx1.setText("");
                            tx2.setText("");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"You can't send money to your own account");
                        tx1.setText("");
                        tx2.setText("");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"First Enter Required Deals ");
                }

            }
            if(e.getActionCommand()=="Return"){
                dispose();
                Transaction_GUI m=new Transaction_GUI();
            }
        }
    }
}
