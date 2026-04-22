package DAO;

//imports
import javax.swing.*;
import java.util.ArrayList;

public class TurnoverReportViewer {

    JFrame frame = new JFrame("Turnover Report");

    public TurnoverReportViewer() {
        frame.setSize(800, 600);
        frame.setLayout(null);

        ArrayList<String[]> data = TurnoverReportReader.getAllResponses();

        // calculate the turnover to read accurately from excel sheet
        int total = 0;
        int quit = 0;
        int fired = 0;

        //this part fxes the issue og not finding the correct column index
        String[] header = data.get(0);
        int reasonIndex = -1;

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
        report.setBounds(20, 20, 400, 300);
        frame.add(report);
        frame.setVisible(true);
    }
}
