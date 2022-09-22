package vaccine;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Mainframe extends JFrame {
    String username;
    int MainframeDone = 0;

//    public static void main(String[] args) {
//        new Mainframe(username).setVisible(true);
//    }

//    public void implement(String username) {
//        //super("VACCINE BOOKING SYSTEM");
//        setTitle("VACCINE BOOKING SYSTEM");
//        this.username = username;
//
//        JButton vaccine = new JButton("BOOK VACCINE");
//        vaccine.setBounds(1000, 560, 150, 30);
//        vaccine.setBackground(Color.BLACK);
//        vaccine.setForeground(Color.WHITE);
//        add(vaccine);
//
//        getContentPane().setForeground(Color.CYAN);
//        setLayout(null);
//
//        JLabel NewLabel = new JLabel("");
//        NewLabel.setIcon(new ImageIcon(ClassLoader.getSystemResource("vaccine/Icon/Vaccine2.png")));
//        NewLabel.setBounds(0, 0, 1282, 712);
//        add(NewLabel);
//
//        vaccine.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ae) {
//                try {
//                    new Add_Recipient(username);
//                    MainframeDone = 1;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        setSize(1282, 712);
//        setVisible(true);
//    }

    public Mainframe(String username) {
        super("VACCINE BOOKING SYSTEM");
        this.username = username;

        JButton vaccine = new JButton("BOOK VACCINE");
        vaccine.setBounds(1000, 560, 150, 30);
        vaccine.setBackground(Color.BLACK);
        vaccine.setForeground(Color.WHITE);
        add(vaccine);

        getContentPane().setForeground(Color.CYAN);
        setLayout(null);

        JLabel NewLabel = new JLabel("");
        NewLabel.setIcon(new ImageIcon(ClassLoader.getSystemResource("vaccine/Icon/Vaccine2.png")));
        NewLabel.setBounds(0, 0, 1282, 712);
        add(NewLabel);

        vaccine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new Add_Recipient(username);
                    MainframeDone = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        setSize(1282, 712);
        setVisible(true);
    }
}
