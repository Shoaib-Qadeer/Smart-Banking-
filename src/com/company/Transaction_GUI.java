package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction_GUI extends JFrame {
    JLabel label_send_money,label_topup,label_billpayment,label_donation,label_order_card,label_return,label_add_with;
    JButton add_with_money,send_money,mobile_topup,billpayment,donations,order_card,back_menu;
    static int cust_Account;

    Transaction_GUI(){
        setSize(800,600);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);


        setContentPane(new JLabel(new ImageIcon("src/transaction_GUI.GIF")));


        ImageIcon icon_add_with = new ImageIcon(new ImageIcon("src/transaction_add_with.PNG").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        add_with_money=new JButton(icon_add_with);
        Dimension dim_addwith=add_with_money.getPreferredSize();
        add_with_money.setBounds(120,200,dim_addwith.width,dim_addwith.height);
        label_add_with=new JLabel("Add/Withdraw Funds");
        label_add_with.setForeground(Color.WHITE);
        Dimension dim_label_add=label_add_with.getPreferredSize();
        label_add_with.setBounds(115,280,dim_label_add.width,dim_label_add.height);
        add_with_money.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
        AddorWithdraw.user_Acc=cust_Account;
   AddorWithdraw m=new AddorWithdraw();
                dispose();

            }
        });





        ImageIcon icon_send = new ImageIcon(new ImageIcon("src/transaction_send_money.PNG").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        send_money=new JButton(icon_send);
        Dimension dim_sendmoney=send_money.getPreferredSize();
        send_money.setBounds(250,200,dim_sendmoney.width,dim_sendmoney.height);
        label_send_money=new JLabel("Transfer Money");
        label_send_money.setForeground(Color.WHITE);
        Dimension dim_label_send=label_send_money.getPreferredSize();
        label_send_money.setBounds(260,280,dim_label_send.width,dim_label_send.height);

        send_money.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            Transaction_Send_Money.user_Acc=cust_Account;
                Transaction_Send_Money m=new Transaction_Send_Money();
             dispose();

            }
        });

        ImageIcon icon_topup=new ImageIcon(new ImageIcon("src/transaction_topup_money.PNG").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        mobile_topup=new JButton(icon_topup);
        Dimension dim_topup=mobile_topup.getPreferredSize();
        mobile_topup.setBounds(380,200,dim_topup.width,dim_topup.height);
        label_topup=new JLabel("Mobile Topup");
        Dimension dim_label_topup=label_topup.getPreferredSize();
        label_topup.setBounds(390,280,dim_label_topup.width,dim_label_topup.height);
        label_topup.setForeground(Color.white);
        mobile_topup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                Transaction_Topup.user_Acc=cust_Account;
                Transaction_Topup n=new Transaction_Topup();


            }
        });

        ImageIcon icon_bill=new ImageIcon(new ImageIcon("src/transaction_utilitybill.PNG").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        billpayment=new JButton(icon_bill);
        Dimension dim_bill_pay=billpayment.getPreferredSize();
        billpayment.setBounds(510,200,dim_bill_pay.width,dim_bill_pay.height);
        label_billpayment=new JLabel("Bill Payment");
        Dimension dim_label_bill=label_billpayment.getPreferredSize();
        label_billpayment.setBounds(520,250,dim_bill_pay.width,dim_bill_pay.height);
        label_billpayment.setForeground(Color.white);
        billpayment.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Transaction_bill_payment.user_Acc=cust_Account;
                Transaction_bill_payment m=new Transaction_bill_payment();
                dispose();

            }
        });

        ImageIcon icon_donation=new ImageIcon(new ImageIcon("src/transaction_donation_money.PNG").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        donations=new JButton(icon_donation);
        Dimension dim_donation=donations.getPreferredSize();
        donations.setBounds(180,350,dim_donation.width,dim_donation.height);
        label_donation=new JLabel("Donate Money");
        Dimension dim_label_donate=label_donation.getPreferredSize();
        label_donation.setBounds(190,425,dim_label_donate.width,dim_label_donate.height);
        label_donation.setForeground(Color.white);
        donations.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                Transaction_Donation.user_Acc=cust_Account;
                Transaction_Donation m=new Transaction_Donation();


            }
        });



        ImageIcon icon_order_credit=new ImageIcon(new ImageIcon("src/transaction_credit_card.PNG").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        order_card=new JButton(icon_order_credit);
        Dimension dim_order_cred=order_card.getPreferredSize();
        order_card.setBounds(335,350,dim_order_cred.width,dim_order_cred.height);
        label_order_card =new JLabel("Virtual Credit Card");
        Dimension dim_lab_cred=label_order_card.getPreferredSize();
        label_order_card.setBounds(345,425,dim_lab_cred.width,dim_lab_cred.height);
        label_order_card.setForeground(Color.WHITE);
        order_card.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                Transaction_Order_Card.user_Acc=cust_Account;
          Transaction_Order_Card m=new Transaction_Order_Card();


            }
        });


        ImageIcon icon_return=new ImageIcon(new ImageIcon("src/transaction_return.PNG").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        back_menu=new JButton(icon_return);
        Dimension dim_return=back_menu.getPreferredSize();
        back_menu.setBounds(500,350,dim_return.width,dim_return.height);
        label_return=new JLabel("Return");
        Dimension dim_lab_ret=label_return.getPreferredSize();
        label_return.setBounds(510,425,dim_lab_ret.width,dim_lab_ret.height);
        label_return.setForeground(Color.WHITE);
        back_menu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                UserMainFrame n=new UserMainFrame();


            }
        });




        add(add_with_money);
        add(label_add_with);
        add(label_send_money);
        add(send_money);
        add(label_topup);
        add(mobile_topup);
        add(billpayment);
        add(label_billpayment);
        add(label_donation);
        add(donations);
        add(order_card);
        add(label_order_card);
        add(label_return);
        add(back_menu);
        setVisible(true);

    }

}
