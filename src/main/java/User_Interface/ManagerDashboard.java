package User_Interface;

//import
import javax.swing.*;
import DAO.EmployeeDAO;
import DAO.ExitInterviewChecklist;
import DAO.OffboardingChecklist;
import DAO.TurnoverReportViewer;
import Model.Employee;

public class ManagerDashboard {

    JFrame frame = new JFrame("Manager Dashboard");
    JButton conductExitBtn = new JButton("Conduct Exit Interview");
    JButton offboardingBtn = new JButton("Process Offboarding");
    JButton employeeSepBtn = new JButton("Manage Employee Separation");
    JButton turnoverReportBtn = new JButton("Generate Turnover Report");
    JButton EmployeeRecordsBtn = new JButton("Update Employee Records");

    private Employee employee;


    public ManagerDashboard(Employee employee) {
        this.employee = employee;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);

        conductExitBtn.setBounds(10, 10, 200, 100);
        offboardingBtn.setBounds(300, 10, 200, 100);
        employeeSepBtn.setBounds(140, 400, 250, 100);
        turnoverReportBtn.setBounds(300, 200, 200, 100);
        EmployeeRecordsBtn.setBounds(10, 200, 200, 100);

        frame.add(conductExitBtn);
        frame.add(offboardingBtn);
        frame.add(employeeSepBtn);
        frame.add(turnoverReportBtn);
        frame.add(EmployeeRecordsBtn);

        // Add checklist to conduct exit Btn
        conductExitBtn.addActionListener(e -> {
            frame.setVisible(false);
            new ExitInterviewChecklist(employee);
        });

        //Add employeeUpdate to button
        EmployeeRecordsBtn.addActionListener(e -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            employeeDAO.updateEmployeeFromForm();

            //shows that record was updated
            JOptionPane.showMessageDialog(frame, "Employee Records Updated");
        });

        // makes it so checklisy appears in the offboarding button
        offboardingBtn.addActionListener(e -> {
            frame.setVisible(false);
            new OffboardingChecklist(employee);
        });

        //makes connection to data excel sheet
        turnoverReportBtn.addActionListener(e -> {
            frame.setVisible(false);
            new TurnoverReportViewer();
        });

        frame.setVisible(true);
    }

}
