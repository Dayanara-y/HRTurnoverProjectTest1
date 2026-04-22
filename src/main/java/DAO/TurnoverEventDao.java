//This java file will coonnect the resignation form to the database
package DAO;

//imports
import DB.ConnToDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class TurnoverEventDao {
    public void insertResignationsFromFrom(){
        try{
            List<String[]> responses = ResignationFormReader.getAllResponse();
            Connection conn = ConnToDB.getConnection();

            for (String[] data : responses) {
                int EmployeeID = Integer.parseInt(data[2]);
                String reason = data[5];

                String departureType = "Quit";

                if (reason != null && reason.toLowerCase().contains("fired")) {
                    departureType = "Fired";
                }

                String sql = """
                        INSERT INTO TurnoverEvent(EmployeeID, DepartureDate, DepartureType)
                        VALUES(?, CURDATE(), ?)""";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1,EmployeeID);
                preparedStatement.setString(2,departureType);

                preparedStatement.executeUpdate();
            }
            System.out.println("Successfully Inserted Resignations");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
