package DAO;

//Imports
import javax.swing.*;
import java.awt.*;
import Model.Employee;
import User_Interface.ManagerDashboard;

public class OffboardingChecklist {

    // title for checklist
    JFrame frame = new JFrame("Offboarding Checklist");

    //Checkboxes
    JCheckBox checkbox1 = new JCheckBox("Remove employee from the system");
    JCheckBox checkbox2 = new JCheckBox("Collect all company technology from employee");
    JCheckBox checkbox3 = new JCheckBox("Conduct exit Interview");

    // exit interview checklis button
    JButton conductExitBtn = new JButton("Exit Interview Checklist");
    //back to menu button
    JButton backBtn = new JButton("Back to Dashboard");

    //button to view resignation request
    JButton viewResignationBtn = new JButton("View Resignation Request");

    private Employee employee;

    public OffboardingChecklist(Employee employee) {
        this.employee = employee;

        frame.setSize(600, 600);
        frame.setLayout(null);

        //checkboxes sizes and frames
        checkbox1.setBounds(20, 20, 400, 30);
        checkbox2.setBounds(20, 60, 400, 30);
        checkbox3.setBounds(20, 100, 400, 30);

        viewResignationBtn.setBounds(20, 160, 250, 40);
        conductExitBtn.setBounds(20, 225, 250, 40);
        backBtn.setBounds(20, 290, 250, 40);


        frame.add(checkbox1);
        frame.add(checkbox2);
        frame.add(checkbox3);
        frame.add(conductExitBtn);
        frame.add(backBtn);
        frame.add(viewResignationBtn);

        conductExitBtn.addActionListener(e -> {
            frame.dispose();
            new ExitInterviewChecklist(employee);
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            new ManagerDashboard(employee);
        });

        //Constructor to view resignation excel file
        viewResignationBtn.addActionListener(e -> {
            java.util.List<String[]> responses = new DAO.ResignationFormReader().getAllResponse();

            StringBuilder message = new StringBuilder();
            for (String[] row : responses) {
                message.append("Full Name: ").append(row[1]).append("\n");
                message.append("Employee ID: ").append(row[2]).append("\n");
                message.append("Last Working Day: ").append(row[3]).append("\n");
                message.append("Reason for leaving: ").append(row[4]).append("\n");
                message.append("Preferred time: ").append(row[5]).append("\n");
                message.append("Time constraints or preferred times: ").append(row[6]).append("\n");
                message.append("Confirm information is accurate: ").append(row[7]).append("\n");
                message.append("----------------------------------------------------\n");

                JTextArea messageArea = new JTextArea(message.toString());
                messageArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(messageArea);
                scrollPane.setPreferredSize(new Dimension(600, 600));

                JOptionPane.showMessageDialog(frame, scrollPane, "Resignation Request", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}
