package vaccine;

import javax.swing.*;
import java.util.*;

public class Test extends JFrame {
    public static void main(String[] args) {
        Connect c = new Connect();
        Login L = new Login();
        String username = L.username;

        List<Object> list = new LinkedList<>();
        list.add(c);
        list.add(L);
        int login = L.LoginDone;

    }
}
