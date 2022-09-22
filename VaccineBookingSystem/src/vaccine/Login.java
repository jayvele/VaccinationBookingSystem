package vaccine;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class Login extends JFrame {
    JTextField uField;
    JPasswordField pField;
    JLabel usernameL, password;
    JButton reset, submit, close, register;
    Font font;
    static int LoginDone = 0;
    static String username;

    public Login() {
        super("LOGIN");
        this.font = new Font("TimesRoman", Font.BOLD, 20);

        reset = new JButton("RESET");
        reset.setBounds(40, 220, 90, 30);
        reset.setBackground(Color.BLACK);
        reset.setForeground(Color.WHITE);
        add(reset);

        register = new JButton("REGISTER");
        register.setBounds(140, 220, 100, 30);
        register.setBackground(Color.BLACK);
        register.setForeground(Color.WHITE);
        add(register);

        submit = new JButton("SUBMIT");
        submit.setBounds(250, 220, 90, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        add(submit);

        close = new JButton("CLOSE");
        close.setBounds(350, 220, 90, 30);
        close.setBackground(Color.BLACK);
        close.setForeground(Color.WHITE);
        add(close);

        getContentPane().setForeground(Color.CYAN);
        setLayout(null);

        usernameL = new JLabel("USERNAME");
        usernameL.setFont(font);
        usernameL.setBounds(40, 80, 150, 27);
        add(usernameL);

        uField = new JTextField();
        uField.setBounds(200, 80, 150, 27);
        add(uField);

        password = new JLabel("PASSWORD");
        password.setFont(font);
        password.setBounds(40, 120, 150, 27);
        add(password);

        pField = new JPasswordField();
        pField.setBounds(200, 120, 150, 27);
        add(pField);

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uField.setText("");
                pField.setText("");
            }
        });
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    Connect c1 = new Connect();
                    username = uField.getText();
                    String password = pField.getText();
                    String query = "SELECT * FROM USER WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'";
                    ResultSet rs = c1.s.executeQuery(query);
                    if (rs.next()) {
                        LoginDone = 1;
                        new Mainframe(username);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Login");
                        setVisible(false);
                        System.exit(0);
                    }
                } catch (Exception var7) {
                }
            }
        });
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    Connect c1 = new Connect();
                    username = uField.getText();
                    String password = pField.getText();
                    String query = "INSERT INTO USER(USERNAME, PASSWORD) VALUES('" + username + "','" + password + "')";
                    c1.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Registered Successfully!");
                    uField.setText("");
                    pField.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        setSize(500, 400);
        setLocation(400, 200);
        setVisible(true);
    }
    /*{
        super("Login");
        this.f1 = new Font("TimesRoman", Font.BOLD, 20);
        this.f2 = new Font("TimesRoman", Font.BOLD, 15);

//        username = new JLabel("Username");
//        username.setFont(f1);
//
//        password = new JLabel("Password");
//        password.setFont(f1);
//
//        uField = new JTextField(15);
//        pField = new JPasswordField(15);

//        reset = new JButton("Reset");
//        reset.setFont(f2);
//
//        submit = new JButton("Submit");
//        submit.setFont(f2);
//
//        close = new JButton("Close");
//        close.setFont(f2);
//
//        register = new JButton("Register");
//        register.setFont(f2);

        username = new JLabel("USERNAME");
        username.setFont(f2);
        username.setBounds(60, 100, 80, 30);
        add(this.username);

        uField = new JTextField();
        uField.setBounds(100, 100, 80, 30);
        this.add(this.uField);

        password = new JLabel("PASSWORD");
        password.setFont(f2);
        password.setBounds(60, 140, 80, 30);
        add(this.password);

        pField = new JPasswordField();
        pField.setBounds(100, 140, 80, 30);
        this.add(this.pField);

//        JButton reset = new JButton("RESET");
//        reset.setBounds(20, 60, 50, 30);
////        reset.setBackground(Color.BLACK);
////        reset.setForeground(Color.WHITE);
//        add(reset);
//
//        JButton submit = new JButton("SUBMIT");
//        submit.setBounds(260, 360, 50, 30);
////        submit.setBackground(Color.BLACK);
////        submit.setForeground(Color.WHITE);
//        add(submit);
//
//        JButton close = new JButton("CLOSE");
//        close.setBounds(320, 360, 50, 30);
////        close.setBackground(Color.BLACK);
////        close.setForeground(Color.WHITE);
//        add(close);
//
//        JButton register = new JButton("RESET");
//        register.setBounds(380, 360, 50, 30);
////        register.setBackground(Color.BLACK);
////        register.setForeground(Color.WHITE);
//        add(register);
//
//        reset.addActionListener(this);
//        submit.addActionListener(this);
//        close.addActionListener(this);
//        register.addActionListener(this);
        this.setVisible(true);

        this.setSize(500, 350);
        this.setLocation(400, 200);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.reset) {
            this.uField.setText("");
            this.pField.setText("");
        }

        if (ae.getSource() == this.close) {
            System.exit(0);
        }

        if (ae.getSource() == this.submit) {
            try {
                Connect c1 = new Connect();
                String username = this.uField.getText();
                String password = this.pField.getText();
                String query = "SELECT * FROM USER WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'";
                ResultSet rs = c1.s.executeQuery(query);
                if (rs.next()) {
                    new Mainframe(username);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    this.setVisible(false);
                    System.exit(0);
                }
            } catch (Exception var7) {
            }
        }
        if (ae.getSource() == this.register) {
            try {
                Connect c1 = new Connect();
                String username = this.uField.getText();
                String password = this.pField.getText();
                String query = "INSERT INTO USER(USERNAME, PASSWORD) VALUES('" + username + "','" + password + "')";
                c1.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Registered Successfully!");
                this.uField.setText("");
                this.pField.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    public static void main(String[] args) {
        new Login();
    }
}
