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

public class CustomerEntryForm extends JFrame {
    JLabel dateformatlabel,password,secretkey,customername,dayobirth,homeno,streetno,cityname,contactno,email;
    JTextField inpass,insecret,incustomername,inhomeno,instreetno,incityname,incontactno,inemail;
    JButton b1,b2;
    JComboBox inday, inmonth, inyear;
    static int cust_index;

    CustomerEntryForm(){

        setSize(500,750);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("src/update_Record.GIF")));


        password=new JLabel("Enter New Password:");
        Dimension lab_pass=password.getPreferredSize();
        password.setBounds(80,160,lab_pass.width,lab_pass.height);
        inpass=new JTextField(null,15);
        Dimension lab_inpass=inpass.getPreferredSize();
        inpass.setBounds(230,160,lab_inpass.width,lab_inpass.height);


        secretkey=new JLabel("Enter New Secret Key:");
        Dimension lab_sec=secretkey.getPreferredSize();
        secretkey.setBounds(80,200,lab_sec.width,lab_sec.height);
        insecret=new JTextField(null,15);
        Dimension lab_insec=insecret.getPreferredSize();
        insecret.setBounds(230,200,lab_insec.width,lab_insec.height);




        customername=new JLabel("Enter your New Name: ");
        Dimension lab_cust_name=customername.getPreferredSize();
        customername.setBounds(80,240,lab_cust_name.width,lab_cust_name.height);
        incustomername=new JTextField(null,15);
        Dimension dim_incustname=incustomername.getPreferredSize();
        incustomername.setBounds(230,240,dim_incustname.width,dim_incustname.height);






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


        homeno=new JLabel("Enter New Home No:");
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





        streetno=new JLabel("Enter New Street No:");
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


        cityname=new JLabel("Enter New City Name:");
        Dimension lab_cityname=cityname.getPreferredSize();
        cityname.setBounds(80,465,lab_cityname.width,lab_cityname.height);
        incityname=new JTextField(null,15);
        Dimension lab_incity=incityname.getPreferredSize();
        incityname.setBounds(230,465,lab_incity.width,lab_incity.height);


        contactno=new JLabel("Enter New Contact No: ");
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




        email=new JLabel("Enter New Email: ");
        Dimension lab_email=email.getPreferredSize();
        email.setBounds(80,545,lab_email.width,lab_email.height);
        inemail=new JTextField(null,15);
        Dimension dim_inemail=inemail.getPreferredSize();
        inemail.setBounds(230,545,dim_inemail.width,dim_inemail.height);

        b1=new JButton("Submit");
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
                UserMainFrame m=new UserMainFrame();
            }
        });
        Dimension dim_ret=b1.getPreferredSize();
        b2.setBounds(260,595,dim_ret.width,dim_ret.height);
        b1.setBackground(Color.white);
        password.setForeground(Color.WHITE);
        secretkey.setForeground(Color.WHITE);
        dayobirth.setForeground(Color.WHITE);

        contactno.setForeground(Color.WHITE);
        email.setForeground(Color.WHITE);
        homeno.setForeground(Color.WHITE);
        streetno.setForeground(Color.WHITE);
        cityname.setForeground(Color.WHITE);
        customername.setForeground(Color.WHITE);
        add(b1);
        add(b2);
        add(password);
        add(secretkey);
        add(dayobirth);

        add(contactno);
        add(email);
        add(customername);

        add(homeno);
        add(streetno);
        add(cityname);

        add(inpass);
        add(insecret);
        add(incustomername);

        add(inday);
        add(inmonth);
        add(inyear);
        add(inhomeno);
        add(instreetno);
        add(incityname);
        add(inemail);

        add(incontactno);


        setVisible(true);
    }

    public class Mylistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="Submit"){
                if(incustomername.getText().length()>0 && inpass.getText().length()>0 && insecret.getText().length()>0 && incontactno.getText().length()>0 && inemail.getText().length()>0  && inhomeno.getText().length()>=1 && instreetno.getText().length()>0 && incityname.getText().length()>0 ){

                    if(inemail.getText().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$") && incontactno.getText().matches("\\d{11}") &&(Integer.parseInt(inhomeno.getText())>0) && Integer.parseInt(instreetno.getText())>0){
                        try {
                            Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bankapp", "05822");
                            PreparedStatement update_rec=con2.prepareStatement("select CUSTOMER_CUSTOMER_ID from account where account_id=?");
                            update_rec.setInt(1,cust_index);
                            ResultSet res=update_rec.executeQuery();
                            res.next();
                            int customer_id_to=res.getInt(1);

                            //update customer set name=? , phone_no=? , email=?, password=?, secret_key=?,  address=?, dob=? where customer_id=?

                            update_rec=con2.prepareStatement("update customer set name=? , phone_no=? , email=?, password=?, secret_key=?,  address=?, dob=? where customer_id=?");
                            update_rec.setString(1, incustomername.getText());
                            update_rec.setString(2,incontactno.getText());
                            update_rec.setString(3,inemail.getText());
                            update_rec.setString(4,inpass.getText());
                            update_rec.setString(5,insecret.getText());
                            Address ad=new Address(Integer.parseInt(inhomeno.getText()),Integer.parseInt(instreetno.getText()),incityname.getText());
                            int f_day = Integer.parseInt ((String)inday.getItemAt(inday.getSelectedIndex()));
                            int f_month=Integer.parseInt((String) inmonth.getItemAt(inmonth.getSelectedIndex()));
                            int f_year=Integer.parseInt((String) inyear.getItemAt(inyear.getSelectedIndex()));
                            Date d=new Date(f_day,f_month,f_year);
                            update_rec.setString(6,ad.toString());
                            update_rec.setString(7,d.toString());
                            update_rec.setInt(8,customer_id_to);

                            update_rec.execute();
                            JOptionPane.showMessageDialog(null,"Record Updated ");


                            inemail.setText("");inpass.setText("");insecret.setText("");incontactno.setText("");incustomername.setText("");
                            inhomeno.setText("");instreetno.setText("");incityname.setText("");

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }
                    else{

                        JOptionPane.showMessageDialog(null,"Invalid Data -Follow Valid Data Format- Enter Again ");
                        inemail.setText("");incontactno.setText("");
                        inhomeno.setText("");instreetno.setText("");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Enter All Required Data and then submit");
                }

            }


        }
    }
}
