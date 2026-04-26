package User_Interface;

//imports
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import DB.ConnToDB;
import Model.Employee;

public class EmployeeSeperattion {
    JFrame frame = new JFrame("Manage Employee Separation");

    JLabel labelID = new JLabel("Employee ID: ");
    JTextField textFieldID = new JTextField();

    JLabel labelDepartureType = new JLabel("Departure Type: ");
    String[] types = {"Quit", "Fired"};
    JComboBox<String> comboBoxDepartureType = new JComboBox<>(types);

    JLabel labelReason = new JLabel("Reason (If employee Quit): ");
    JComboBox<String> comboBoxReason = new JComboBox<>();

    JLabel labelDate = new JLabel("Departure Date (YYY-MM-DD): ");
    JTextField textFieldDate = new JTextField();

    JButton recordButton = new JButton("Record Separation");
    JButton backButton = new JButton("Back to dashboard");

    private Employee employee;

    public EmployeeSeperattion(Employee employee) {
        this.employee = employee;

        frame.setSize(600,600);
        frame.setLayout(null);

        labelID.setBounds(10, 10, 100, 20);
        textFieldID.setBounds(150, 10, 100, 20);

        labelDepartureType.setBounds(10, 50, 100, 20);
        comboBoxDepartureType.setBounds(150, 50, 100, 20);

        labelReason.setBounds(10, 70, 200, 20);
        comboBoxReason.setBounds(200, 70, 150, 20);

        labelDate.setBounds(10, 100, 200, 20);
        textFieldDate.setBounds(200, 100, 100, 20);

        recordButton.setBounds(10, 150, 150, 20);
        backButton.setBounds(10, 200, 150, 20);

        frame.add(labelID);
        frame.add(textFieldID);
        frame.add(labelDepartureType);
        frame.add(comboBoxDepartureType);
        frame.add(labelReason);
        frame.add(comboBoxReason);
        frame.add(labelDate);
        frame.add(textFieldDate);
        frame.add(recordButton);
        frame.add(backButton);

        loadReasons();

        recordButton.addActionListener(e -> recordSeparation());
        backButton.addActionListener(e -> {
            frame.dispose();
            new ManagerDashboard(employee);
        });
        frame.setVisible(true);
    }

    private void loadReasons() {
        try{
            Connection conn = ConnToDB.getConnection();
            String sql = "SELECT ReasonID, Description FROM TurnoverReason";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                comboBoxReason.addItem(resultSet.getInt("ReasonID") + " - " + resultSet.getString("Description"));

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void recordSeparation() {
        try{
            int EmployeeId = Integer.parseInt(textFieldID.getText());
            String DepartureType = comboBoxDepartureType.getSelectedItem().toString();
            String departureDate = textFieldDate.getText();

            //get the reasonId from resigned(1)
            String selectedReason = comboBoxReason.getSelectedItem().toString();
            int reasonID = Integer.parseInt(selectedReason.split(" - " )[0]);

            //calc the employment length
            Connection conn = ConnToDB.getConnection();
            String hiresql = "SELECT HireDate FROM Employee WHERE EmployeeID = ?";
            PreparedStatement hireps = conn.prepareStatement(hiresql);
            hireps.setInt(1, EmployeeId);
            ResultSet resultSet = hireps.executeQuery();

            int employmentLength = 0;

            if(resultSet.next()){
                String hireDate = resultSet.getString("HireDate");
                employmentLength = calculateYears(hireDate, departureDate);

            }
            String insertSql = """
                    INSERT INTO TurnoverEvent (ReasonID, EmployeeID, DepartureDate, DepartureType, EmployeeLength)
                    VALUES(?, ?, ?, ?, ?)""";
            PreparedStatement insert = conn.prepareStatement(insertSql);
            insert.setInt(1, reasonID);
            insert.setInt(2, EmployeeId);
            insert.setString(3, departureDate);
            insert.setString(4, DepartureType);
            insert.setInt(5, employmentLength);
            insert.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Recorded Separation");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private int calculateYears(String start, String end) {
        try{
            int startYear = Integer.parseInt(start.substring(0,4));
            int endYear = Integer.parseInt(end.substring(0,4));
            return endYear - startYear;
        }catch(Exception e){
            return 0;
        }
    }
//    private void archiveEmployee() {
//        try{
//            int id = Integer.parseInt(textFieldID.getText());
//            Connection conn = ConnToDB.getConnection();
//
//            String sql = "UPDATE Employee SET Status = 'Archived' WHERE EmployeeID = ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, id);
//            ps.executeUpdate();
//            JOptionPane.showMessageDialog(frame, "Archived Employee");
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(frame, "Error");
//        }
//    }


}
