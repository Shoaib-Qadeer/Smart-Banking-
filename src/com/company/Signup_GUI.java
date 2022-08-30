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

public class Signup_GUI extends JFrame {
    JLabel balance,username,password,secretkey,customername,dayobirth,dateformatlabel,homeno,streetno,cityname,contactno,email;
    JTextField inbalance,inuser,inpass,insecret,incustomername,inhomeno,instreetno,incityname,incontactno,inemail;
    JButton b1,b2;
    JComboBox inday, inmonth, inyear;
    static int cust_index;

    Signup_GUI(){
        setSize(500,750);
        setUndecorated(true);
        setLayout(null);
        setLocationRelativeTo(null);

        setResizable(false);
        //
        setContentPane(new JLabel(new ImageIcon("src/SIGNUP.GIF")));

        username=new JLabel("Enter User Name:");
        Dimension lab_user=username.getPreferredSize();
        username.setBounds(80,120,lab_user.width,lab_user.height);
        inuser=new JTextField(null,15);
        Dimension dim_inuser=inuser.getPreferredSize();
        inuser.setBounds(230,120,dim_inuser.width,dim_inuser.height);


        password=new JLabel("Enter Password:");
        Dimension lab_pass=password.getPreferredSize();
        password.setBounds(80,160,lab_pass.width,lab_pass.height);
        inpass=new JTextField(null,15);
        Dimension lab_inpass=inpass.getPreferredSize();
        inpass.setBounds(230,160,lab_inpass.width,lab_inpass.height);


        secretkey=new JLabel("Enter Secret Key:");
        Dimension lab_sec=secretkey.getPreferredSize();
        secretkey.setBounds(80,200,lab_sec.width,lab_sec.height);
        insecret=new JTextField(null,15);
        Dimension lab_insec=insecret.getPreferredSize();
        insecret.setBounds(230,200,lab_insec.width,lab_insec.height);




        customername=new JLabel("Enter your Name: ");
        Dimension lab_cust_name=customername.getPreferredSize();
        customername.setBounds(80,240,lab_cust_name.width,lab_cust_name.height);
        incustomername=new JTextField(null,15);
        Dimension dim_incustname=incustomername.getPreferredSize();
        incustomername.setBounds(230,240,dim_incustname.width,dim_incustname.height);




        balance=new JLabel("Enter Balance: ");
        Dimension lab_balance=balance.getPreferredSize();
        balance.setBounds(80,280,lab_balance.width,lab_balance.height);
        inbalance=new JTextField(null,15);
        inbalance.addKeyListener(new KeyListener() {
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
        Dimension lab_inbal=inbalance.getPreferredSize();
        inbalance.setBounds(230,280,lab_inbal.width,lab_inbal.height);


        dayobirth=new JLabel("Enter Date of Birth: ");
        Dimension lab_day_of=dayobirth.getPreferredSize();
        dayobirth.setBounds(80,320,lab_day_of.width,lab_day_of.height);

        dateformatlabel=new JLabel("Date Format: DD/MM/YYYY");
        Dimension lab_dateform=dateformatlabel.getPreferredSize();
        dateformatlabel.setBounds(80,355,lab_dateform.width,lab_dateform.height);

        String daystring[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
        inday=new JComboBox(daystring);
        inday.setBounds(230, 320,45,20);

        String monthstring[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
        inmonth=new JComboBox(monthstring);
        inmonth.setBounds(280,320,45,20);

        String yearsstring[]=new String [104];
        int j=0;
        for (int i=2003; i>=1900;i--){
            yearsstring[j]=String.valueOf(i);
            j+=1;
        }
        inyear =new JComboBox(yearsstring);
        inyear.setBounds(330,320,70,20);

        homeno=new JLabel("Enter Home No:");
        Dimension lab_homeno=homeno.getPreferredSize();
        homeno.setBounds(80,385,lab_homeno.width,lab_homeno.height);
        inhomeno=new JTextField(null,15);
        inhomeno.addKeyListener(new KeyListener() {
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
        Dimension dim_inhome=inhomeno.getPreferredSize();
        inhomeno.setBounds(230,385,dim_inhome.width,dim_inhome.height);





        streetno=new JLabel("Enter Street No:");
        Dimension lab_str=streetno.getPreferredSize();
        streetno.setBounds(80,425,lab_str.width,lab_str.height);
        instreetno=new JTextField(null,15);
        instreetno.addKeyListener(new KeyListener() {
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
        Dimension dim_instreet=instreetno.getPreferredSize();
        instreetno.setBounds(230,425,dim_instreet.width,dim_instreet.height);


        cityname=new JLabel("Enter City Name:");
        Dimension lab_cityname=cityname.getPreferredSize();
        cityname.setBounds(80,465,lab_cityname.width,lab_cityname.height);
        incityname=new JTextField(null,15);
        Dimension lab_incity=incityname.getPreferredSize();
        incityname.setBounds(230,465,lab_incity.width,lab_incity.height);


        contactno=new JLabel("Enter Contact No: ");
        Dimension lab_contact=contactno.getPreferredSize();
        contactno.setBounds(80,505,lab_contact.width,lab_contact.height);
        incontactno=new JTextField(null,15);
        incontactno.addKeyListener(new KeyListener() {
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
        Dimension dim_incontact=incontactno.getPreferredSize();
        incontactno.setBounds(230,505,dim_incontact.width,dim_incontact.height);




        email=new JLabel("Enter Email: ");
        Dimension lab_email=email.getPreferredSize();
        email.setBounds(80,545,lab_email.width,lab_email.height);
        inemail=new JTextField(null,15);
        Dimension dim_inemail=inemail.getPreferredSize();
        inemail.setBounds(230,545,dim_inemail.width,dim_inemail.height);






        JPanel buttonpanel=new JPanel();
        b1=new JButton("Signup");
        Mylistener ac=new Mylistener();
        b1.addActionListener(ac);
        Dimension dim_sub=b1.getPreferredSize();
        b1.setBounds(170,595,dim_sub.width,dim_sub.height);
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



        ImageIcon ico=new ImageIcon(new ImageIcon("src/icons8-return-50.PNG").getImage().getScaledInstance(30, 15, Image.SCALE_DEFAULT));
        b2=new JButton(ico);
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                try {
                    Main_Menu_loginbased n=new Main_Menu_loginbased();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        Dimension dim_ret=b1.getPreferredSize();
        b2.setBounds(260,595,dim_ret.width,dim_ret.height);
        b1.setBackground(Color.white);
        add(b1);
        add(b2);
        add(username);
        add(password);
        add(secretkey);
        add(dayobirth);
add(dateformatlabel);
        add(contactno);
        add(email);
        add(customername);
        add(balance);
        add(inuser);
        add(homeno);
        add(streetno);
        add(cityname);
        add(inuser);
        add(inpass);
        add(insecret);
       add(inmonth);
       add(inday);
       add(inyear);
        add(incustomername);

        add(inhomeno);
        add(instreetno);
        add(incityname);
        add(inemail);
        add(inbalance);
        add(incontactno);

        setVisible(true);




    }

    public class Mylistener  implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="Signup"){

                if(inuser.getText().length()>0 && inbalance.getText().length()>0 && incustomername.getText().length()>0 && inpass.getText().length()>0 && insecret.getText().length()>0 && incontactno.getText().length()>0 && inemail.getText().length()>0  && inhomeno.getText().length()>=1 && instreetno.getText().length()>0 && incityname.getText().length()>0){

                    boolean found=true;

                    Connection   con = null;
                    try {
                        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                        Statement stmt = con.createStatement();
                        PreparedStatement pst = con.prepareStatement("select name, username, password from customer where username=?");
                        pst.setString(1,inuser.getText());
                        ResultSet res=pst.executeQuery();
                        if(!res.next()){
                           found=false;
                        }
                        else{
                           found=true;
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    if(found==false){
                        if(inemail.getText().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$") && (incontactno.getText().matches("\\d{11}"))  && (Integer.parseInt(inhomeno.getText())>0 && (Integer.parseInt(instreetno.getText())>0))){
                            int f_day = Integer.parseInt ((String)inday.getItemAt(inday.getSelectedIndex()));
                            int f_month=Integer.parseInt((String) inmonth.getItemAt(inmonth.getSelectedIndex()));
                            int f_year=Integer.parseInt((String) inyear.getItemAt(inyear.getSelectedIndex()));
                            Date d=new Date(f_day,f_month,f_year);
                            Address ad=new Address(Integer.parseInt(inhomeno.getText()),Integer.parseInt(instreetno.getText()),incityname.getText());

                            if(Integer.parseInt(inbalance.getText())>20000){

                                try {

                                    Account   n = new Account(inuser.getText(),inpass.getText(),insecret.getText(),incustomername.getText(),ad,incontactno.getText(),inemail.getText(),d,Integer.parseInt(inbalance.getText()),"Premium","Active");
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }

                            }
                            else{

                                try {
                                    Account n = new Account(inuser.getText(),inpass.getText(),insecret.getText(),incustomername.getText(),ad,incontactno.getText(),inemail.getText(),d,Integer.parseInt(inbalance.getText()),"Normal","Active");

                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }

                            }

                            inuser.setText("");inemail.setText("");inpass.setText("");insecret.setText("");incontactno.setText("");incustomername.setText("");
                            inhomeno.setText("");instreetno.setText("");incityname.setText("");
                          inbalance.setText("");

                            JOptionPane.showMessageDialog(null,"Account Created - Please Login Now");
                            dispose();
                            try {
                                Main_Menu_loginbased m=new Main_Menu_loginbased();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Invalid Data - Enter Again ");
                            inuser.setText("");inemail.setText("");inpass.setText("");insecret.setText("");incontactno.setText("");incustomername.setText("");
                            inhomeno.setText("");instreetno.setText("");incityname.setText("");
                           inbalance.setText("");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Username Already Exist-Try Another");
                        inuser.setText("");
                    }





                }
                else{
                    JOptionPane.showMessageDialog(null,"Enter All Required Data");
                }


            }



        }
    }

    public boolean user_found(String unam) throws SQLException {
        boolean rec_found=false;
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
        Statement stt =  con.createStatement();
        PreparedStatement pstmt=con.prepareStatement("Select  CUSTOMER_ID  from customer where username=? ");
        pstmt.setString(1,unam);

        ResultSet rs =pstmt.executeQuery();
        if(!rs.next()) {
            rec_found=true;
        }



        return rec_found;
    }
}
