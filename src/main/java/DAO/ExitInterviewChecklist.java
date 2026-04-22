package DAO;

//import
import Model.Employee;
import User_Interface.ManagerDashboard;
import javax.swing.*;

public class ExitInterviewChecklist {

    JFrame frame = new JFrame("Exit Interview Checklist");

    JCheckBox check1 = new JCheckBox("Notify Employee");
    JCheckBox check2 = new JCheckBox("Schedule Interview");
    JCheckBox check3 = new JCheckBox("Gather Paperwork");
    JCheckBox check4 = new JCheckBox("Prepare Exit Interview Questions");
    JCheckBox check5 = new JCheckBox("Update Employee Records");
    JCheckBox check6 = new JCheckBox("Terminate Employee After Interview Has Been Conducted");

    JButton submit = new JButton("Submit Checklist");
    JButton backBtn = new JButton("Back to dashboard");

    //declare variable name for class (forgot this last time lol)
    private Employee employee;

    public ExitInterviewChecklist(Employee employee) {
        this.employee = employee;

        frame.setSize(800, 600);
        frame.setLayout(null);

        check1.setBounds(10, 10, 500, 50);
        check2.setBounds(10, 60, 500, 50);
        check3.setBounds(10, 90, 500, 50);
        check4.setBounds(10, 120, 500, 50);
        check5.setBounds(10, 150, 500, 50);
        check6.setBounds(10, 180, 500, 50);

        submit.setBounds(10, 250, 150, 20);
        backBtn.setBounds(10, 300, 150, 20);

        frame.add(check1);
        frame.add(check2);
        frame.add(check3);
        frame.add(check4);
        frame.add(check5);
        frame.add(check6);
        frame.add(submit);
        frame.add(backBtn);

        submit.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Checklist Submited");
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            new ManagerDashboard(employee);
        });

        frame.setVisible(true);
    }
}
