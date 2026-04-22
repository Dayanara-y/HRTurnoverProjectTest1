package User_Interface;

// imports
import DAO.EmployeeDAO;
import Model.Employee;

import javax.swing.*;

public class LoginUI {
    JFrame frame = new JFrame("HR System Login");

    //labels
    JLabel usernamelabel = new JLabel("Enter Username:");
    JLabel passwordlabel = new JLabel("Enter Password:");

    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();

    JButton login = new JButton("Login");

    public LoginUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setSize(300, 200);
        frame.setLayout(null);

        usernamelabel.setBounds(20, 20, 100, 25);
        username.setBounds(130, 20, 120, 25);

        passwordlabel.setBounds(20, 60, 100, 25);
        password.setBounds(130, 60, 120, 25);

        login.setBounds(100, 110, 100, 30);

        frame.add(usernamelabel);
        frame.add(passwordlabel);
        frame.add(username);
        frame.add(password);
        frame.add(login);

        frame.setVisible(true);

        login.addActionListener(e -> {
            String user = username.getText();

            if(user.equals("Manager")) {
                new ManagerDashboard(null);
            }
            else {
                EmployeeDAO DAO = new EmployeeDAO();
                Employee employee = DAO.getEmployee(1);

                new EmployeeDashboard(employee);
            }
            frame.dispose();
        });

    }


}
