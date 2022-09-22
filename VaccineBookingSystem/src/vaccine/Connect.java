package vaccine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {
    Connection c;
    Statement s;

    public Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.c = DriverManager.getConnection("jdbc:mysql://localhost:4306/VACCINE", "root", "");
            this.s = this.c.createStatement();
        } catch (Exception var2) {
            System.out.println(var2);
        }
    }
}