package DAO;

//import
import User_Interface.EmployeeDashboard;
import javax.swing.*;
import java.awt.*;
import Model.Employee;

public class GoalTracker {
    JFrame frame = new JFrame("Write Goals");

    JTextArea goalTextArea = new JTextArea(8,40);
    JButton savebtn = new JButton("Save");
    JButton backbtn = new JButton("Back to Dashboard");

    private Employee employee;

    //to save and display goals previous;y added
    private DefaultListModel<String> goalList = new DefaultListModel<>();
    private JList<String> goalLists = new JList<>(goalList);

    public GoalTracker(Employee employee, DefaultListModel<String> goalList) {

        this.employee = employee;
        this.goalList = goalList;
        this.goalLists.setModel(goalList);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout(10,10));

        JLabel goalLabel = new JLabel("State Goals Below:");
        goalLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        frame.add(goalLabel, BorderLayout.NORTH);

        //will add the center panel
        goalTextArea.setLineWrap(true);
        goalTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(goalTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        frame.add(scrollPane, BorderLayout.CENTER);

        //input the saved goal list
        JScrollPane listScroll = new JScrollPane(goalLists);
        listScroll.setBorder(BorderFactory.createTitledBorder("Saved Goals: "));
        listScroll.setPreferredSize(new Dimension(250, 0));
        frame.add(listScroll, BorderLayout.EAST);

        //buttons view
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        savebtn.setPreferredSize(new Dimension(150,40));
        backbtn.setPreferredSize(new Dimension(150,40));

        buttonPanel.add(savebtn);
        buttonPanel.add(backbtn);


        frame.add(buttonPanel, BorderLayout.SOUTH);


        savebtn.addActionListener(e->{
            String goal = goalTextArea.getText().trim();

            if (goal.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Please enter a Goal");

            } else {
                goalList.addElement(goal);  //Dont forget this lol this is what actually saves it
                JOptionPane.showMessageDialog(frame, "Goal Saved Successfully");
                goalTextArea.setText("");
            }
        });

        backbtn.addActionListener(e->{
            frame.dispose();
            new EmployeeDashboard(employee, goalList);
        });
        frame.setVisible(true);


    }
}
