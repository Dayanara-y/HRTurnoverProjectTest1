package DAO;

//imports to other packages to set connection
import DB.ConnToDB;
import Model.Employee;

//imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {
    public Employee getEmployee(int id) {
        Employee employee = null;

        try {
            Connection conn = ConnToDB.getConnection();

            String sql = "SELECT * FROM Employee WHERE EmployeeID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                employee = new Employee();

                employee.setEmployeeID(rs.getInt("EmployeeID"));
                employee.setFirstName(rs.getString("FirstName"));
                employee.setLastName(rs.getString("lastName"));
                employee.setDOB(rs.getString("DOB"));
                employee.setAddress(rs.getString("Address"));
                employee.setPhoneNum(rs.getString("PhoneNum"));
                employee.setEmail(rs.getString("Email"));
                employee.setHireDate(rs.getString("HireDate"));
                employee.setSalary(rs.getString("Salary"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return employee;
    }
}
