package vaccine;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class Vaccine_Details extends JFrame implements ActionListener {
    JTable[] table = new JTable[20];
    JTable TableDefault;
    JLabel VaccinationDetails, CentreCode, CentreName, Timings, Date, Cost, DoseNo, DoseType, Available_Vaccine, Code, CodeInput, Filter;
    JButton Show, Book;
    JComboBox DoseList, VaccineList, FilterList;
    JTextField CodeField;
    Font font = new Font("TimesRoman", Font.BOLD, 13);
    public static String choice;
    String username;
    Connect c = new Connect();
    List<Integer> SortCost;
    List<Integer> AvailableVaccineSort = new LinkedList<>();

    int ycolumns = 220, ylabels = 100;

    public static void main(String[] args) {
        new Vaccine_Details("JAY");
    }

    public Vaccine_Details(String username) {
        this.username = username;

        setTitle("VACCINATION DETAILS");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        DoseNo = new JLabel("DOSE");
        DoseNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        DoseNo.setBounds(60, ylabels, 150, 30);
        add(DoseNo);

        DoseType = new JLabel("VACCINE TYPE");
        DoseType.setFont(new Font("Tahoma", Font.PLAIN, 20));
        DoseType.setBounds(280, ylabels, 150, 30);
        add(DoseType);

        String[] items1 = {"DOSE 1", "DOSE 2"};
        DoseList = new JComboBox(items1);
        DoseList.setBounds(140, ylabels, 100, 30);
        add(DoseList);

        String[] items2 = {"COVAXIN", "COVISHIELD"};
        VaccineList = new JComboBox(items2);
        VaccineList.setBounds(420, ylabels, 120, 30);
        add(VaccineList);

        Filter = new JLabel("FILTER");
        Filter.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Filter.setBounds(60, 150, 150, 30);
        add(Filter);

        String[] items3 = {"COST", "AVAILABLE VACCINES", "DEFAULT"};
        FilterList = new JComboBox(items3);
        FilterList.setBounds(140, 150, 120, 30);
        add(FilterList);

        SortCost = new LinkedList<>();

        for (int i = 0; i < 20; i++) {
            try {
                String query = "SELECT COST FROM DETAILS WHERE CODE = '" + i + "'";
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()) {
                    int n = rs.getInt("COST");
                    SortCost.add(n);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Collections.sort(SortCost);

        for (int i = 0; i < 20; i++) {
            try {
                String query = "SELECT AVAILABLE_VACCINES FROM DETAILS WHERE CODE = '" + i + "'";
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()) {
                    int k = rs.getInt("AVAILABLE_VACCINES");
                    AvailableVaccineSort.add(k);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(AvailableVaccineSort);

        Show = new JButton("SHOW");
        Show.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Show.setBackground(Color.BLACK);
        Show.setForeground(Color.WHITE);
        Show.setBounds(570, ylabels, 100, 30);
        add(Show);

        Book = new JButton("BOOK");
        Book.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Book.setBackground(Color.BLACK);
        Book.setForeground(Color.WHITE);
        Book.setBounds(500, 480, 100, 23);
        add(Book);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        VaccinationDetails = new JLabel("VACCINATION DETAILS");
        VaccinationDetails.setForeground(Color.BLUE);
        VaccinationDetails.setFont(new Font("Tahoma", Font.PLAIN, 31));
        VaccinationDetails.setBounds(180, 27, 360, 31);
        add(VaccinationDetails);

        Code = new JLabel("CODE");
        Code.setFont(font);
        Code.setBounds(20, ycolumns, 100, 20);
        add(Code);

        CentreCode = new JLabel("CENTRE CODE");
        CentreCode.setFont(font);
        CentreCode.setBounds(100, ycolumns, 100, 20);
        add(CentreCode);

        CentreName = new JLabel("CENTRE NAME");
        CentreName.setFont(font);
        CentreName.setBounds(210, ycolumns, 100, 20);
        add(CentreName);

        Timings = new JLabel("TIMINGS");
        Timings.setFont(font);
        Timings.setBounds(320, ycolumns, 100, 20);
        add(Timings);

        Date = new JLabel("DATE");
        Date.setFont(font);
        Date.setBounds(420, ycolumns, 100, 20);
        add(Date);

        Cost = new JLabel("COST");
        Cost.setFont(font);
        Cost.setBounds(515, ycolumns, 80, 20);
        add(Cost);

        Available_Vaccine = new JLabel("AVAILABLE VACCINES");
        Available_Vaccine.setFont(font);
        Available_Vaccine.setBounds(580, ycolumns, 150, 20);
        add(Available_Vaccine);


        this.Show.addActionListener(this);
        this.Book.addActionListener(this);

        CodeInput = new JLabel("CODE");
        CodeInput.setFont(new Font("Tahoma", Font.BOLD, 20));
        CodeInput.setBounds(240, 480, 80, 23);
        add(CodeInput);

        CodeField = new JTextField();
        CodeField.setBounds(320, 480, 150, 23);
        add(CodeField);

        setSize(740, 600);
        setLocation(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.Show) {
            try {
                int Dose = 0;
                String DoseString = (String) DoseList.getSelectedItem();
                if (DoseString.equalsIgnoreCase("DOSE 1")) {
                    Dose = 1;
                }
                if (DoseString.equalsIgnoreCase("DOSE 2")) {
                    Dose = 2;
                }
                String Vaccine = (String) VaccineList.getSelectedItem();
                String Filter = (String) FilterList.getSelectedItem();
                int y = 250;
                if (Filter.equalsIgnoreCase("COST")) {
                    for (int i = 0; i < SortCost.size(); i++) {
                        String str = "SELECT CODE, CENTRE_CODE, CENTRE_NAME, TIMINGS, DATE, COST, AVAILABLE_VACCINES FROM DETAILS WHERE DOSE = " + Dose + " AND VACCINE_TYPE = '" + Vaccine + "' AND COST = " + SortCost.get(i) + ";";
                        ResultSet rs = c.s.executeQuery(str);

                        if (rs.next()) {
                            rs.previous();
                            table[i] = new JTable();
                            table[i].setBounds(20, y, 700, 20);
                            add(table[i]);
                            table[i].setModel(DbUtils.resultSetToTableModel(rs));
                            y = y + 25;
                        }
                    }
                }

                if (Filter.equalsIgnoreCase("AVAILABLE VACCINES")) {
                    for (int i = 0; i < AvailableVaccineSort.size(); i++) {
                        String str = "SELECT CODE, CENTRE_CODE, CENTRE_NAME, TIMINGS, DATE, COST, AVAILABLE_VACCINES FROM DETAILS WHERE DOSE = " + Dose + " AND VACCINE_TYPE = '" + Vaccine + "' AND AVAILABLE_VACCINES =  " + AvailableVaccineSort.get(i) + ";";
                        ResultSet rs = c.s.executeQuery(str);

                        if (rs.next()) {
                            rs.previous();
                            table[i] = new JTable();
                            table[i].setBounds(20, y, 700, 20);
                            add(table[i]);
                            table[i].setModel(DbUtils.resultSetToTableModel(rs));
                            y = y + 25;
                        }
                    }
                }

                if (Filter.equalsIgnoreCase("DEFAULT")) {
                    String str = "SELECT CODE, CENTRE_CODE, CENTRE_NAME, TIMINGS, DATE, COST, AVAILABLE_VACCINES FROM DETAILS WHERE DOSE = '" + Dose + "' AND VACCINE_TYPE='" + Vaccine + "';";
                    ResultSet rs = c.s.executeQuery(str);
                    if (rs.next()) {
                        rs.previous();
                        TableDefault = new JTable();
                        TableDefault.setBounds(20, 250, 700, 320);
                        add(TableDefault);
                        TableDefault.setModel(DbUtils.resultSetToTableModel(rs));
                    } else {
                        JOptionPane.showMessageDialog(null, "No Vaccines Available");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        if (ae.getSource() == this.Show) {
//            try {
//                int Dose = 0;
//                String DoseString = (String) DoseList.getSelectedItem();
//                if (DoseString.equalsIgnoreCase("DOSE 1")) {
//                    Dose = 1;
//                }
//                if (DoseString.equalsIgnoreCase("DOSE 2")) {
//                    Dose = 2;
//                }
//                String Vaccine = (String) VaccineList.getSelectedItem();
//                Connect c = new Connect();
//
//                String str = "SELECT CODE, CENTRE_CODE, CENTRE_NAME, TIMINGS, DATE, COST, AVAILABLE_VACCINES FROM DETAILS WHERE DOSE = '" + Dose + "' AND VACCINE_TYPE='" + Vaccine + "';";
//                ResultSet rs = c.s.executeQuery(str);
//
//                if (rs.next()) {
//                    rs.previous();
//                    table.setModel(DbUtils.resultSetToTableModel(rs));
//                } else {
//                    JOptionPane.showMessageDialog(null, "No Vaccines Available");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        if (ae.getSource() == this.Book) {
            choice = this.CodeField.getText();
            new Payment_Details(username);
            setVisible(false);
        }
    }

    public static void iterate(LinkedList<Integer> SortCost) {
        for (int i = 0; i < SortCost.size(); i++) {

        }
    }
}