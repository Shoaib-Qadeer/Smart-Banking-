package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.Timer;

public class Intro extends JFrame {
    Timer timer;
    Intro() {
        setSize(650,600);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setContentPane(new JLabel(new ImageIcon("src/introgif.gif")));
        setVisible(true);
        timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                timer.stop();
                dispose();
                try {
                    new  Main_Menu_loginbased ();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        timer.start();

    }
}
