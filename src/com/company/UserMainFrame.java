package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;

public class UserMainFrame extends JFrame{
    JButton view_account,edit_account,view_tran_hist,perform_trans,logout;
    JLabel lab_view,lab_edit,lab_trans_hist,lab_perfrom_trans,lg_ab;
    JLabel title;
    static int customer_index;
    static  int acc_no;
    static String name;

    UserMainFrame(){
        setResizable(false);
        setLayout(null);
        setSize(600,700);
        setLocationRelativeTo(null);

        title=new JLabel("Welcome to our Bank");
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("src/userframeimg.GIF")));

        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
        title.setForeground(Color.WHITE);
        Dimension tit_dim=title.getPreferredSize();
        title.setBounds(175,60,tit_dim.width,tit_dim.height);

        ImageIcon icon_add_with = new ImageIcon(new ImageIcon("src/user_frame_transaction.PNG").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        perform_trans=new JButton(icon_add_with);
        Dimension dim_per_tr=perform_trans.getPreferredSize();
        perform_trans.setBounds(120,180,dim_per_tr.width,dim_per_tr.height);
        lab_perfrom_trans=new JLabel("Perform Transaction");
        lab_perfrom_trans.setForeground(Color.WHITE);
        Dimension dim_lab_perf=lab_perfrom_trans.getPreferredSize();
        lab_perfrom_trans.setBounds(115,260,dim_lab_perf.width,dim_lab_perf.height);

        ImageIcon icon_view_account = new ImageIcon(new ImageIcon("src/user_Accout_details.PNG").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        view_account=new JButton(icon_view_account);
        Dimension dim_view_acc=view_account.getPreferredSize();
        view_account.setBounds(340,180,dim_view_acc.width,dim_view_acc.height);
        lab_view=new JLabel("View Account Details ");
        lab_view.setForeground(Color.WHITE);
        Dimension dim_view=lab_view.getPreferredSize();
        lab_view.setBounds(335,260,dim_view.width,dim_view.height);

        ImageIcon icon_edit_account = new ImageIcon(new ImageIcon("src/user_edit_account.PNG").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        edit_account=new JButton(icon_edit_account);
        Dimension dim_edit_acc=edit_account.getPreferredSize();
        edit_account.setBounds(120,350,dim_edit_acc.width,dim_edit_acc.height);
        lab_edit=new JLabel("Update Details");
        lab_edit.setForeground(Color.WHITE);
        Dimension dim_edit_lab=lab_edit.getPreferredSize();
        lab_edit.setBounds(125,430,dim_edit_lab.width,dim_edit_lab.height);


        ImageIcon icon_trans_hist = new ImageIcon(new ImageIcon("src/user_frame_trans_history.PNG").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        view_tran_hist=new JButton(icon_trans_hist);
        Dimension dim_view_lab=view_tran_hist.getPreferredSize();
        view_tran_hist.setBounds(340,350,dim_view_lab.width,dim_view_lab.height);
        lab_trans_hist=new JLabel("Transaction History");
        lab_trans_hist.setForeground(Color.WHITE);
        Dimension dim_lab_trans_hi=lab_trans_hist.getPreferredSize();
        lab_trans_hist.setBounds(335,430,dim_lab_trans_hi.width,dim_lab_trans_hi.height);

        ImageIcon icon_logout = new ImageIcon(new ImageIcon("src/user_logout.PNG").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        logout=new JButton(icon_logout);
        Dimension dim_logout=logout.getPreferredSize();
        logout.setBounds(225,485,dim_logout.width,dim_logout.height);
        lg_ab=new JLabel("LOGOUT");
        Dimension lg_lb=lg_ab.getPreferredSize();
        lg_ab.setForeground(Color.WHITE);
        lg_ab.setBounds(245,560,lg_lb.width,lg_lb.height);




        usermenu_action m=new usermenu_action();
        view_account.addActionListener(m);
        logout.addActionListener(m);
        edit_account.addActionListener(m);
        perform_trans.addActionListener(m);
        view_tran_hist.addActionListener(m);

        add(title);
        add(view_account);
        add(lab_view);
        add(view_tran_hist);
        add(lab_trans_hist);
        add(edit_account);
        add(lab_edit);
        add(perform_trans);
        add(lab_perfrom_trans);
        add(logout);
        add(lg_ab);

        setVisible(true);

    }

    public class usermenu_action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==perform_trans){
                Transaction_GUI.cust_Account=acc_no;
                dispose();
                Transaction_GUI ab=new Transaction_GUI();

            }

            if(e.getSource()==view_account){
                Connection con = null;
                ResultSet res=null;
                try {
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                    Statement stmt = con.createStatement();
                    PreparedStatement pst = con.prepareStatement("select customer_id,name, phone_no,email,address,dob ,account_id, account_status, account_level, account_balance, username, password, secret_key from account, customer where account.customer_customer_id=customer.customer_id and customer.customer_id=?");
                    pst.setString(1, String.valueOf(customer_index));
                    res=pst.executeQuery();
                    res.next();
                    String account_details="Personal Details: \n Customer Id: "+res.getInt(1)+"\n Name: "+res.getString(2)+"\n Phone No: "+res.getString(3)+"\n Email: "+res.getString(4)+"\n Address: "+res.getString(5)+"\n DOB: "+res.getString(6)+" \n \n ---Account Details--- \n Account Id: "+res.getInt(7)+"\n Account Status : "+res.getString(8)+"\n Account Level: "+res.getString(9) +"\n Account Balance "+res.getInt(10)+"\n --Login Details-- \n Username: "+res.getString(11)+"\n Password: "+res.getString(12) +"\n Secret Key: "+res.getString(13);
                    JOptionPane.showMessageDialog(null,account_details);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(e.getSource()==edit_account){
                dispose();
                CustomerEntryForm.cust_index=acc_no;
               CustomerEntryForm m=new CustomerEntryForm();
            }

            if(e.getSource()==view_tran_hist){
                    User_Transaction_Record.account_id=acc_no;
                try {
                    User_Transaction_Record mj=new User_Transaction_Record();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(e.getSource()==logout){
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
