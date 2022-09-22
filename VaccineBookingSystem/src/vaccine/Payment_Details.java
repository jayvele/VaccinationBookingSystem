package vaccine;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.security.auth.callback.NameCallback;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class Payment_Details extends JFrame implements ActionListener { //Fifth
    JTextField pField, CardField;
    JTable table;
    JLabel Name, LastName, AadharNo, PhoneNo, NameValue, LastNameValue, PhoneNoValue, AadharNoValue, GenderValue, DoseValue, VaccineValue, Cost, CostValue;
    JButton Verify, MakePayment;
    int size = 17, height = 25, width = 100;
    String username;
    int OTPverified = 0;
    String choice = Vaccine_Details.choice;


    public static void main(String[] args) {
        //String username = "JAY";
        new Payment_Details("JAY");
    }

    public Payment_Details(String username) {
//        setTitle("PAYMENT_DETAILS");
//        getContentPane().setBackground(Color.WHITE);
//        setSize(860, 486);
//        setLayout(null);
        Connect c = new Connect();

        this.username = username;

        MakePayment = new JButton("MAKE PAYMENT");
        MakePayment.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MakePayment.setBackground(Color.BLACK);
        MakePayment.setForeground(Color.WHITE);
        MakePayment.setBounds(80, 440, 180, 30);
        add(MakePayment);

        Verify = new JButton("VERIFY");
        Verify.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Verify.setBackground(Color.BLACK);
        Verify.setForeground(Color.WHITE);
        Verify.setBounds(350, 360, 100, height);
        add(Verify);

        Verify.addActionListener(this);
        MakePayment.addActionListener(this);

        table = new JTable();
        table.setBounds(80, 60, 500, 25);
        add(table);

        Name = new JLabel("NAME");
        Name.setFont(new Font("Tahoma", Font.PLAIN, size));
        Name.setBounds(80, 120, width, height);
        add(Name);

        try {
            String NameString = "SELECT NAME FROM USER WHERE USERNAME = '" + username + "'";
            ResultSet rs = c.s.executeQuery(NameString);
            while (rs.next()) {
                String n = rs.getString("NAME");
                NameValue = new JLabel(n);
                NameValue.setFont(new Font("Tahoma", Font.PLAIN, size));
                NameValue.setBounds(180, 120, width, height);
                add(NameValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LastName = new JLabel("LAST NAME");
        LastName.setFont(new Font("Tahoma", Font.PLAIN, size));
        LastName.setBounds(380, 120, width, height);
        add(LastName);

        try {
            String LastNameString = "SELECT LASTNAME FROM USER WHERE USERNAME = '" + username + "'";
            ResultSet rs = c.s.executeQuery(LastNameString);
            while (rs.next()) {
                String n = rs.getString("LASTNAME");
                LastNameValue = new JLabel(n);
                LastNameValue.setFont(new Font("Tahoma", Font.PLAIN, size));
                LastNameValue.setBounds(480, 120, width, height);
                add(LastNameValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        AadharNo = new JLabel("AADHAR");
        AadharNo.setFont(new Font("Tahoma", Font.PLAIN, size));
        AadharNo.setBounds(380, 160, width, height);
        add(AadharNo);

        try {
            String AadharNoString = "SELECT AADHAR_NO FROM USER WHERE USERNAME = '" + username + "'";
            ResultSet rs = c.s.executeQuery(AadharNoString);
            while (rs.next()) {
                String n = rs.getString("AADHAR_NO");
                AadharNoValue = new JLabel(n);
                AadharNoValue.setFont(new Font("Tahoma", Font.PLAIN, size));
                AadharNoValue.setBounds(480, 160, 150, height);
                add(AadharNoValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        PhoneNo = new JLabel("PHONE");
        PhoneNo.setFont(new Font("Tahoma", Font.PLAIN, size));
        PhoneNo.setBounds(80, 160, width, height);
        add(PhoneNo);

        try {
            String PhoneNoString = "SELECT PHONE_NO FROM USER WHERE USERNAME = '" + username + "'";
            ResultSet rs = c.s.executeQuery(PhoneNoString);
            while (rs.next()) {
                String n = rs.getString("PHONE_NO");
                PhoneNoValue = new JLabel(n);
                PhoneNoValue.setFont(new Font("Tahoma", Font.PLAIN, size));
                PhoneNoValue.setBounds(180, 160, width, height);
                add(PhoneNoValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        label = new JLabel("");
//        label.setIcon(new ImageIcon(ClassLoader.getSystemResource("vaccine/Icon/Vaccine2.png")));
//        label.setBounds(425, 15, 239, 272);
//        add(label);

        JLabel Gender = new JLabel("GENDER");
        Gender.setFont(new Font("Tahoma", Font.PLAIN, size));
        Gender.setBounds(80, 240, width, height);
        add(Gender);

        try {
            String GenderString = "SELECT GENDER FROM USER WHERE USERNAME = '" + username + "'";
            ResultSet rs = c.s.executeQuery(GenderString);
            while (rs.next()) {
                String n = rs.getString("GENDER");
                GenderValue = new JLabel(n);
                GenderValue.setFont(new Font("Tahoma", Font.PLAIN, size));
                GenderValue.setBounds(180, 240, width, height);
                add(GenderValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Cost = new JLabel("COST");
        Cost.setFont(new Font("Tahoma", Font.PLAIN, size));
        Cost.setBounds(380, 240, width, height);
        add(Cost);

        try {
            String CostString = "SELECT COST FROM DETAILS WHERE CODE = '" + choice + "'";
            ResultSet rs = c.s.executeQuery(CostString);
            while (rs.next()) {
                String n = rs.getString("COST");
                CostValue = new JLabel(n);
                CostValue.setFont(new Font("Tahoma", Font.PLAIN, size));
                CostValue.setBounds(480, 240, width, height);
                add(CostValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel Card = new JLabel("CARD NO");
        Card.setFont(new Font("Tahoma", Font.PLAIN, size));
        Card.setBounds(80, 320, width, height);
        add(Card);

        CardField = new JTextField();
        CardField.setBounds(200, 320, width, height);
        add(CardField);

        JLabel Password = new JLabel("OTP");
        Password.setFont(new Font("Tahoma", Font.PLAIN, size));
        Password.setBounds(80, 360, width, height);
        add(Password);

        pField = new JTextField();
        pField.setBounds(200, 360, width, height);
        add(pField);

        JLabel DoseNo = new JLabel("DOSE");
        DoseNo.setFont(new Font("Tahoma", Font.PLAIN, size));
        DoseNo.setBounds(80, 200, width, height);
        add(DoseNo);

        try {
            String DoseString = "SELECT DOSE FROM DETAILS WHERE CODE = '" + choice + "'";
            ResultSet rs = c.s.executeQuery(DoseString);
            while (rs.next()) {
                String n = rs.getString("DOSE");
                DoseValue = new JLabel(n);
                DoseValue.setFont(new Font("Tahoma", Font.PLAIN, size));
                DoseValue.setBounds(180, 200, width, height);
                add(DoseValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel VaccineType = new JLabel("VACCINE");
        VaccineType.setFont(new Font("Tahoma", Font.PLAIN, size));
        VaccineType.setBounds(380, 200, width, height);
        add(VaccineType);

        try {
            String VaccineString = "SELECT VACCINE_TYPE FROM DETAILS WHERE CODE = '" + choice + "'";
            ResultSet rs = c.s.executeQuery(VaccineString);
            while (rs.next()) {
                String n = rs.getString("VACCINE_TYPE");
                VaccineValue = new JLabel(n);
                VaccineValue.setFont(new Font("Tahoma", Font.PLAIN, size));
                VaccineValue.setBounds(480, 200, width, height);
                add(VaccineValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel G = new JLabel("");
        G.setFont(new Font("Tahoma", Font.PLAIN, size));
        G.setBounds(180, 200, width, height);
        add(G);

        setVisible(true);

        try {
            String str = "SELECT CENTRE_CODE, CENTRE_NAME, TIMINGS, DATE, COST FROM DETAILS WHERE CODE = '" + choice + "';";
            ResultSet rs = c.s.executeQuery(str);
            if (rs.next()) {
                rs.previous();
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(650, 520);
        setLocation(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        Connect c = new Connect();

        if (ae.getSource() == this.Verify) {
            try {
                String password = this.pField.getText();
                String query = "SELECT PASSWORD FROM USER WHERE USERNAME = '" + username + "'";
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()) {
                    String n = rs.getString("PASSWORD");
                    if (n.equals(password)) {
                        OTPverified = 1;
                        JOptionPane.showMessageDialog(null, "OTP VERIFIED!");
                    } else {
                        JOptionPane.showMessageDialog(null, "TRY AGAIN");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ae.getSource() == this.MakePayment) {
            if (OTPverified == 0) {
                JOptionPane.showMessageDialog(null, "VERIFY OTP FIRST");
            }
            if (OTPverified == 1) {
                try {
                    String query = "INSERT INTO appointment VALUES('" + username + "', " + choice + ")";
                    c.s.executeUpdate(query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "PAYMENT SUCCESSFUL");
            }
        }
    }
}