package User_Interface;

//import
import javax.swing.*;

public class ManagerDashboard {

    JFrame frame = new JFrame("Manager Dashboard");
    JButton conductExitBtn = new JButton("Conduct Exit Interview");
    JButton offboardingBtn = new JButton("Process Offboarding");
    JButton employeeSepBtn = new JButton("Manage Employee Separation");
    JButton turnoverReportBtn = new JButton("Generate Turnover Report");
    JButton EmployeeRecordsBtn = new JButton("Update Employee Records");

    public ManagerDashboard() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 900);
        frame.setLayout(null);

        conductExitBtn.setBounds(10, 10, 200, 100);
        offboardingBtn.setBounds(300, 10, 200, 100);
        employeeSepBtn.setBounds(10, 200, 200, 100);
        turnoverReportBtn.setBounds(300, 200, 200, 100);
        EmployeeRecordsBtn.setBounds(10, 400, 200, 100);

        frame.add(conductExitBtn);
        frame.add(offboardingBtn);
        frame.add(employeeSepBtn);
        frame.add(turnoverReportBtn);
        frame.add(EmployeeRecordsBtn);

        frame.setVisible(true);
    }

}
