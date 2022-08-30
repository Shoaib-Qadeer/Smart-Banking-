package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Admin_transaction_record_type extends JFrame {

    JButton all_trx,b_addfund, b_withdraw, b_paybill, b_vcc, b_fundstrans,b_donation, b_topup, return_button;


    Admin_transaction_record_type(){
        setSize(650,600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("src/transaction_re_tp.gif")));


        return_button=new JButton("Return");
        Dimension dim_retr= return_button.getPreferredSize();
        return_button.setForeground(Color.black);
        return_button.setBounds(550,10,dim_retr.width, dim_retr.height);
        return_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                return_button.setForeground(Color.WHITE);
                return_button.setBackground(Color.ORANGE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                return_button.setBackground(UIManager.getColor("control"));
                return_button.setForeground(Color.black);
            }
        });

        b_addfund =new JButton("All Add Funds");



        b_fundstrans=new JButton("All Funds Transfers");
        Dimension dim_fundstrns= b_fundstrans.getPreferredSize();
        b_fundstrans.setForeground(Color.black);
        b_fundstrans.setBounds(380,250,dim_fundstrns.width, dim_fundstrns.height);
        b_fundstrans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_fundstrans.setForeground(Color.WHITE);
                b_fundstrans.setBackground(Color.DARK_GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_fundstrans.setBackground(UIManager.getColor("control"));
                b_fundstrans.setForeground(Color.black);
            }
        });



        Dimension dim_addfunds=b_fundstrans.getPreferredSize();
        b_addfund.setForeground(Color.black);
        b_addfund.setBounds(150,150,dim_addfunds.width,dim_addfunds.height);
        b_addfund.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_addfund.setForeground(Color.WHITE);
                b_addfund.setBackground(Color.DARK_GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_addfund.setBackground(UIManager.getColor("control"));
                b_addfund.setForeground(Color.black);
            }
        });


        all_trx=new JButton("All Transactions");
        Dimension dim_alltrx= all_trx.getPreferredSize();
        all_trx.setForeground(Color.black);
        all_trx.setBounds(280,450,dim_alltrx.width,dim_alltrx.height);
        all_trx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                all_trx.setForeground(Color.WHITE);
                all_trx.setBackground(Color.DARK_GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                all_trx.setBackground(UIManager.getColor("control"));
                all_trx.setForeground(Color.black);
            }
        });




        b_withdraw=new JButton("All Withdraws");
        Dimension dim_with=b_fundstrans.getPreferredSize();
        b_withdraw.setForeground(Color.black);
        b_withdraw.setBounds(150,250, dim_with.width, dim_with.height);
        b_withdraw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_withdraw.setForeground(Color.WHITE);
                b_withdraw.setBackground(Color.DARK_GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_withdraw.setBackground(UIManager.getColor("control"));
                b_withdraw.setForeground(Color.black);
            }
        });


        b_paybill=new JButton("All Bill Payments");
        Dimension dim_bill=b_fundstrans.getPreferredSize();
        b_paybill.setBounds(150,350,dim_bill.width, dim_bill.height);
        b_paybill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_paybill.setForeground(Color.WHITE);
                b_paybill.setBackground(Color.DARK_GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_paybill.setBackground(UIManager.getColor("control"));
                b_paybill.setForeground(Color.black);
            }
        });

        b_vcc=new JButton("All VCC's");
        Dimension dim_vcc= b_fundstrans.getPreferredSize();
        b_vcc.setForeground(Color.black);
        b_vcc.setBounds(380,150,dim_vcc.width, dim_vcc.height);
        b_vcc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_vcc.setForeground(Color.WHITE);
                b_vcc.setBackground(Color.DARK_GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_vcc.setBackground(UIManager.getColor("control"));
                b_vcc.setForeground(Color.black);
            }
        });



        b_donation=new JButton("All Donations");
        Dimension dim_donation= b_fundstrans.getPreferredSize();
        b_donation.setForeground(Color.black);
        b_donation.setBounds(380,350,dim_donation.width, dim_donation.height);
        b_donation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_donation.setForeground(Color.WHITE);
                b_donation.setBackground(Color.DARK_GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_donation.setBackground(UIManager.getColor("control"));
                b_donation.setForeground(Color.black);
            }
        });


        b_topup=new JButton("All Topup");
        Dimension dim_topup=b_fundstrans.getPreferredSize();
        b_topup.setForeground(Color.black);
        b_topup.setBounds(280,80,dim_topup.width, dim_topup.height);
        b_topup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_topup.setForeground(Color.WHITE);
                b_topup.setBackground(Color.DARK_GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_topup.setBackground(UIManager.getColor("control"));
                b_topup.setForeground(Color.black);
            }
        });

        myActionListener mj=new myActionListener();
        b_addfund.addActionListener(mj);
        b_withdraw.addActionListener(mj);
        b_fundstrans.addActionListener(mj);
        b_topup.addActionListener(mj);
        b_vcc.addActionListener(mj);
        b_paybill.addActionListener(mj);
        b_donation.addActionListener(mj);
        all_trx.addActionListener(mj);
        return_button.addActionListener(mj);
        add(b_addfund);
        add(b_donation);
        add(b_fundstrans);
        add(b_topup);
        add(b_withdraw);
        add(b_vcc);
        add(return_button);
        add(b_paybill);
        add(all_trx);
        setVisible(true);




    }
    public class myActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="All Add Funds"){
                try {
                    t_addfunds m=new t_addfunds();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }


            if(e.getActionCommand()=="All Transactions"){
                try {
                    Admin_Transaction_Record mj=new Admin_Transaction_Record();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            //

            if(e.getActionCommand()=="All Withdraws"){
                try {
                    t_withdraw m=new t_withdraw();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

            if(e.getActionCommand()=="All Bill Payments"){
                try {
                    t_bill_payment m=new t_bill_payment();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

            if(e.getActionCommand()=="All VCC's"){
                try {
                    t_vcc m=new t_vcc();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(e.getActionCommand()=="All Funds Transfers"){
                try {
                    t_fundstransfer m=new   t_fundstransfer();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            //

            if(e.getActionCommand()=="All Donations"){
                try {
                    t_donation m=new t_donation();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

            if(e.getActionCommand()=="All Topup"){
                try {
                    t_topup m=new t_topup();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(e.getActionCommand()=="Return"){
                     dispose();
                    Admin m=new Admin();

            }
            //


        }
    }

}
