package DB;

// IMPORTS
import java.sql.DriverManager;
import java.sql.Connection;

public class ConnToDB {
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/HR_TURNOVER_SYSTEM",
                    "root", "12102003Dy*");

            System.out.println("Connected to the database successfully");
            return connection;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
