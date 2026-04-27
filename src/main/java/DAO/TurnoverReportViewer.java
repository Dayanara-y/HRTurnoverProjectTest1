package DAO;

//imports
import Model.Employee;
import User_Interface.ManagerDashboard;

import javax.swing.*;
import java.util.ArrayList;

public class TurnoverReportViewer {

    private Employee employee;
    JFrame frame = new JFrame("Turnover Report");

    public TurnoverReportViewer(Employee employee) {
        this.employee = employee;
        frame.setSize(900, 900);
        frame.setLayout(null);

        // turnover report is too large so add scrollpane
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new java.awt.Dimension(900, 1400));

        ArrayList<String[]> data = TurnoverReportReader.getAllResponses();

        // makes the arrayList into a 2d array
        String[] columnNames = data.get(0);

        // had a crash if this is not added since it wasn't able to read index columns correctly
        int columnCount = columnNames.length;

        String[][] tableData = new String[data.size()-1][columnCount];
        for(int i=1; i<data.size()-1; i++) {
            String[] row = data.get(i);
            String[] fixedRow = new String[columnCount];
            for(int j=0; j< row.length && j < columnCount; j++) {
                fixedRow[j] = row[j];
            }

            //if missing values add empty space
            for(int j = row.length; j < columnCount; j++) {
                fixedRow[j] = "";
            }
            tableData[i-1] = fixedRow;
        }

        JTable table = new JTable(tableData, columnNames);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 20, 800, 500);
        panel.add(pane);

        // calculate the turnover to read accurately from excel sheet
        int total = 0;
        int quit = 0;
        int fired = 0;
        int reasonIndex = -1;

        //this part fixes the issue of not finding the correct column index
        String[] header = data.get(0);


        for (int i = 0; i < header.length; i++) {
            if(header[i].toLowerCase().contains("reason")){
                reasonIndex = i;
                break;
            }
        }

        for(int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);

            if(reasonIndex == -1 || row.length <= reasonIndex) continue;

            String reason = row[reasonIndex];

            if(reason == null) continue;
            reason = reason.trim().toLowerCase();

            if(reason.isEmpty()) continue;
            total++;

            if(reason.contains("quit")) quit++;
            else if (reason.contains("fired")) fired++;

        }

        int left = total;

        double turnover = 0;
        if(total>0){
            turnover = ((double) left/50) * 100;
        }

        JTextArea report = new JTextArea(
                "Total Employees: 50" + "\nEmployees Left: " + left + "\nTurnover Rate: " + String.format("%.2f", turnover) + "%" + "\nQuit percentage:" + quit + "\nFired percentage: " + fired + "%");
        report.setBounds(20, 540, 800, 120);
        report.setEditable(false);
        panel.add(report);


        // this adds the back to dashboard button
        JButton back = new JButton("Back to Dashboard");
        back.setBounds(20,680,200,30);
        back.addActionListener(e ->{
            frame.dispose();
            new ManagerDashboard(employee);
        });
        panel.add(back);

        //scroll sliders
        JScrollPane pane1 = new JScrollPane(panel);
        pane1.setBounds(0,0,880,880);
        frame.add(pane1);
        frame.setVisible(true);
    }


}
