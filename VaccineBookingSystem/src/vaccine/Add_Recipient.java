package vaccine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Add_Recipient extends JFrame {
    JTextField Aadhar_Field, Address_field, Phone_Field, Name_Field, LastName_Field;

    public Add_Recipient(String username) {

        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD RECIPIENT DETAILS");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(778, 486);
        getContentPane().setLayout(null);

        JButton Save = new JButton("SAVE");
        Save.setBounds(200, 360, 150, 30);
        Save.setBackground(Color.BLACK);
        Save.setForeground(Color.WHITE);
        add(Save);

        JLabel Name = new JLabel("NAME");
        Name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Name.setBounds(40, 80, 150, 27);
        add(Name);

        Name_Field = new JTextField();
        Name_Field.setBounds(200, 80, 150, 27);
        add(Name_Field);

        JLabel LastName = new JLabel("LAST NAME");
        LastName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        LastName.setBounds(40, 120, 150, 27);
        add(LastName);

        LastName_Field = new JTextField();
        LastName_Field.setBounds(200, 120, 150, 27);
        add(LastName_Field);

        JLabel Phone = new JLabel("PHONE NUMBER");
        Phone.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Phone.setBounds(40, 160, 150, 27);
        add(Phone);

        Phone_Field = new JTextField();
        Phone_Field.setBounds(200, 160, 150, 27);
        add(Phone_Field);

        JLabel Aadhar_no = new JLabel("AADHAR NUMBER");
        Aadhar_no.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Aadhar_no.setBounds(40, 200, 150, 27);
        add(Aadhar_no);

        Aadhar_Field = new JTextField();
        Aadhar_Field.setBounds(200, 200, 150, 27);
        add(Aadhar_Field);

        JLabel Address = new JLabel("ADDRESS");
        Address.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Address.setBounds(40, 240, 150, 27);
        add(Address);

        Address_field = new JTextField();
        Address_field.setBounds(200, 240, 150, 27);
        add(Address_field);

        JLabel Gender = new JLabel("GENDER");
        Gender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Gender.setBounds(40, 280, 150, 27);
        add(Gender);

        JRadioButton Male = new JRadioButton("MALE");
        Male.setBackground(Color.WHITE);
        Male.setBounds(200, 280, 60, 27);
        add(Male);

        JRadioButton Female = new JRadioButton("FEMALE");
        Female.setBackground(Color.WHITE);
        Female.setBounds(280, 280, 90, 27);
        add(Female);

        setVisible(true);

        JLabel AddRecipients = new JLabel("RECIPIENT DETAILS");
        AddRecipients.setForeground(Color.BLUE);
        AddRecipients.setFont(new Font("Tahoma", Font.PLAIN, 31));
        AddRecipients.setBounds(50, 24, 442, 35);
        add(AddRecipients);

        Save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String Name = Name_Field.getText();
                String LastName = LastName_Field.getText();
                String Aadhar_No = Aadhar_Field.getText();
                String Address = Address_field.getText();
                String Gender = null;
                String Phone_No = Phone_Field.getText();

                if (Male.isSelected()) {
                    Gender = "MALE";
                } else if (Female.isSelected()) {
                    Gender = "FEMALE";
                }

                try {
                    Connect c = new Connect();
                    String str = "UPDATE USER SET NAME = '" + Name + "', LASTNAME = '" + LastName + "', PHONE_NO = '" + Phone_No + "', AADHAR_NO = '" + Aadhar_No + "', ADDRESS = '" + Address + "', GENDER = '" + Gender + "' WHERE USERNAME = '" + username + "';";
                    c.s.executeUpdate(str);
                    new Vaccine_Details(username);
                    setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        setSize(400, 480);
        setVisible(true);
        setLocation(400, 200);
    }

    public static void main(String[] args) {
        new Add_Recipient("JAY");
    }
}