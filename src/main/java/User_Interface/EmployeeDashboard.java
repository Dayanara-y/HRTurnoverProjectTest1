package User_Interface;

//import
import javax.swing.*;
import Model.Employee;

public class EmployeeDashboard {
    JFrame frame = new JFrame("Employee Dashboard");

    //store the logged in employee
    Employee employee;

    JLabel welcomeLabel;

    JButton updateEmployeeInfoBtn = new JButton("Update Profile");
    JButton goalsBtn = new JButton("Track Goals");
    JButton meetingRequestBtn = new JButton("Request Meeting With Manager");
    JButton viewPerformanceBtn = new JButton("View Performance");
    JButton resignationBtn = new JButton("Request Resignation");

    //constructor
    public EmployeeDashboard(Employee employee) {
        this.employee = employee;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 900);
        frame.setLayout(null);

        //New label for welcoming employee
        welcomeLabel = new JLabel("Welcome " + employee.getFirstName() + " " + employee.getLastName());

        welcomeLabel.setBounds(50,0,400,30);
        frame.add(welcomeLabel);

        updateEmployeeInfoBtn.setBounds(50, 10, 400, 100);
        goalsBtn.setBounds(50, 120, 400, 100);
        meetingRequestBtn.setBounds(50, 240, 400, 100);
        viewPerformanceBtn.setBounds(50, 360, 400, 100);
        resignationBtn.setBounds(50, 480, 400, 100);

        frame.add(updateEmployeeInfoBtn);
        frame.add(goalsBtn);
        frame.add(meetingRequestBtn);
        frame.add(viewPerformanceBtn);
        frame.add(resignationBtn);

        frame.setVisible(true);
    }

}
