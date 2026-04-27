package User_Interface;

//import
import javax.swing.*;

import DAO.*;
import Model.Employee;

public class ManagerDashboard {

    JFrame frame = new JFrame("Manager Dashboard");
    JButton conductExitBtn = new JButton("Conduct Exit Interview");
    JButton offboardingBtn = new JButton("Process Offboarding");
    JButton employeeSepBtn = new JButton("Manage Employee Separation");
    JButton turnoverReportBtn = new JButton("Generate Turnover Report");
    JButton EmployeeRecordsBtn = new JButton("Update Employee Records");
    JButton loadResignationsBtn = new JButton("Load Resignations");

    private Employee employee;


    public ManagerDashboard(Employee employee) {
        this.employee = employee;

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        //make centered
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(600, 500);

        conductExitBtn.setBounds(10, 10, 200, 100);
        offboardingBtn.setBounds(300, 10, 200, 100);
        employeeSepBtn.setBounds(140, 400, 250, 100);
        turnoverReportBtn.setBounds(300, 200, 200, 100);
        EmployeeRecordsBtn.setBounds(10, 200, 200, 100);
    //    loadResignationsBtn.setBounds(300, 320, 200, 100);


        panel.add(conductExitBtn);
        panel.add(offboardingBtn);
        panel.add(employeeSepBtn);
        panel.add(turnoverReportBtn);
        panel.add(EmployeeRecordsBtn);
        panel.add(loadResignationsBtn);

        //add in panels
        frame.add(panel);
        frame.setVisible(true);

        //center login stuff
        SwingUtilities.invokeLater(() -> {
            panel.setLocation((frame.getWidth() - panel.getWidth()) / 2, (frame.getHeight() - panel.getHeight()) / 2);

        });

        // Add checklist to conduct exit Btn
        conductExitBtn.addActionListener(e -> {
            frame.setVisible(false);
            new ExitInterviewChecklist(employee);
        });

        //Add employeeUpdate to button
        EmployeeRecordsBtn.addActionListener(e -> {
//            EmployeeDAO employeeDAO = new EmployeeDAO();
//            employeeDAO.updateEmployeeFromForm();
//
//            //shows that record was updated
//            JOptionPane.showMessageDialog(frame, "Employee Records Updated");
            showEmployeeRecordsMenu();
        });


        // makes it so checklisy appears in the offboarding button
        offboardingBtn.addActionListener(e -> {
            frame.setVisible(false);
            new OffboardingChecklist(employee);
        });

        //makes connection to data excel sheet
        turnoverReportBtn.addActionListener(e -> {
            frame.setVisible(false);
            new TurnoverReportViewer(employee);
        });

        //connect resignation form to databse
        loadResignationsBtn.addActionListener(e -> {
            TurnoverEventDao dao = new TurnoverEventDao();
            dao.insertResignationsFromFrom();

            JOptionPane.showMessageDialog(frame, "Resignations Updated into database");
        });

        //this will connect to the employee separation
        employeeSepBtn.addActionListener(e -> {
            frame.setVisible(false);
            new EmployeeSeperattion(employee);
        });

        frame.setVisible(true);
    }

    // this is to add the button to see employee perfomance request and their meeting w/ manager request
    private void showEmployeeRecordsMenu() {
        JFrame d = new JFrame( "Employee Records");
        d.setExtendedState(JFrame.MAXIMIZED_BOTH);
        d.setLayout(null);

        //create the panel that holds buttons
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(600, 500);

        //updates the employees from form
        JButton updateEmployeeRecordsBtn = new JButton("Update Employee Records");
        updateEmployeeRecordsBtn.setBounds(80, 20, 320, 40);
        updateEmployeeRecordsBtn.addActionListener(e -> {
            new EmployeeDAO().updateEmployeeFromForm();
            JOptionPane.showMessageDialog(frame, "Employee Records Updated into database");

        });
        panel.add(updateEmployeeRecordsBtn);

        // add the view updaterecords request
        JButton updateEmployeeInfoBtn = new JButton("View Update Records Request");
        updateEmployeeInfoBtn.setBounds(80, 80, 320, 40);
        updateEmployeeInfoBtn.addActionListener(e -> {
            openGoogleSheet("https://docs.google.com/spreadsheets/d/1ZMIHPJb1-pTTez2X3xwKrUO82RJci7Nh2VMYdIndZnA/edit?usp=sharing");

        });
        panel.add(updateEmployeeInfoBtn);

        //add the employee performance request excel
        JButton viewPerformanceBtn = new JButton("View Employee Performance Request");
        viewPerformanceBtn.setBounds(80, 140, 320, 40);
        viewPerformanceBtn.addActionListener(e -> {
            openGoogleSheet("https://docs.google.com/spreadsheets/d/1SDvlFWh7ha6snAgGnHqy0k2wyLYeSR00nYmRvUKMod4/edit?usp=sharing");
        });
        panel.add(viewPerformanceBtn);

        // add employee meeting request
        JButton meetingRequestBtn = new JButton("View Employee Meeting Request with Manager");
        meetingRequestBtn.setBounds(80, 200, 320, 40);
        meetingRequestBtn.addActionListener(e -> {
            openGoogleSheet("https://docs.google.com/spreadsheets/d/1ZtieBCOE0YeNHysheZx5ZVx9j77_zCPw1iwP-6NzbBs/edit?usp=sharing");
        });
        panel.add(meetingRequestBtn);

        d.add(panel);
        d.setVisible(true);

        //center login stuff
        SwingUtilities.invokeLater(() -> {
            panel.setLocation((frame.getWidth() - panel.getWidth()) / 2, (frame.getHeight() - panel.getHeight()) / 2);

        });



    }

    // this method reads the googlesheet
    private void openGoogleSheet(String url) {
        try{
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
