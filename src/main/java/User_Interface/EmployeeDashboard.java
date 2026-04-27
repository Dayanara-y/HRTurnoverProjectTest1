package User_Interface;

//import
import javax.swing.*;
import DAO.GoalTracker;

import DAO.ExitInterviewChecklist;
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

    //Private to add goal lists
    private DefaultListModel<String> goalList = new DefaultListModel<>();

    public EmployeeDashboard(Employee employee) {

        this(employee, new DefaultListModel<>());
    }

    //constructor
    public EmployeeDashboard(Employee employee, DefaultListModel<String> goalList) {

        this.employee = employee;
        this.goalList = goalList;

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(null);

        //label to hold buttons
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(500, 400);

        //New label for welcoming employee
        welcomeLabel = new JLabel("Welcome, " + employee.getFirstName() + " " + employee.getLastName());

        welcomeLabel.setBounds(50,0,400,30);
        frame.add(welcomeLabel);

        updateEmployeeInfoBtn.setBounds(50, 50, 400, 50);
        goalsBtn.setBounds(50, 110, 400, 50);
        meetingRequestBtn.setBounds(50, 170, 400, 50);
        viewPerformanceBtn.setBounds(50, 230, 400, 50);
        resignationBtn.setBounds(50, 290, 400, 50);

        panel.add(updateEmployeeInfoBtn);
        panel.add(goalsBtn);
        panel.add(meetingRequestBtn);
        panel.add(viewPerformanceBtn);
        panel.add(resignationBtn);

        frame.add(panel);
        frame.setVisible(true);

        //center panel
        //center login stuff
        SwingUtilities.invokeLater(() -> {
            panel.setLocation((frame.getWidth() - panel.getWidth()) / 2, (frame.getHeight() - panel.getHeight()) / 2);

        });

        //connect a gaol tracker to button
        goalsBtn.addActionListener(e ->{
            frame.setVisible(false);
            new GoalTracker(employee, goalList);
        });

        //Update the employee info from the google form
        updateEmployeeInfoBtn.addActionListener(e -> {
            try{
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://docs.google.com/forms/d/e/1FAIpQLSdftc-BGL-f7YycV6g8ShgWwsEodOhxoAnhERDloqjR5skORw/viewform?usp=sharing&ouid=116154935130672454109"));

            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        //Create the meeting from the google form
        meetingRequestBtn.addActionListener(e -> {
            try{
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://docs.google.com/forms/d/e/1FAIpQLSd-Xb7HKgWnnAKNMyJI8MFR1g_erzclOeda61_UvQUPo1CRAA/viewform?usp=sharing&ouid=116154935130672454109"));

            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        //Create connection from reqeuest resignation button to google form
        resignationBtn.addActionListener(e -> {
            try{
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://docs.google.com/forms/d/e/1FAIpQLSf9STvt0w4MaFxy8hTFdZcKXH4yT_FWITS0IMHUQibDU_KXoQ/viewform?usp=sharing&ouid=116154935130672454109"));

            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        //request to view performance
        viewPerformanceBtn.addActionListener(e->{
            try{
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://docs.google.com/forms/d/e/1FAIpQLSeEJOnHiIgP0iHzxrofkEBOAVhU8H2d5QXXvI-JGjzTqNWMWQ/viewform?usp=sharing&ouid=116154935130672454109"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setVisible(true);
    }

}
