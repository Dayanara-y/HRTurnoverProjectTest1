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

    //helper method
    private String safe(String[] data, int index){
        return data.length > index ? data[index] : null;
    }
    private String nullIfEmpty(String value){
        if(value == null || value.trim().isEmpty()){
            return null;
        }
        return value;
    }

    public void updateEmployeeFromForm() {

        try {

            String[] data = FormReader.getLatestResponse();

            // test
            for (int i = 0; i < data.length; i++) {
                System.out.println(i + " -> " + data[i]);
            }

//            if(data[1] == null || data[1].isEmpty()){
//                System.out.println("Employee ID is missing");
//                return;
//            }

            //connect to column
            int employeeID = Integer.parseInt(data[1].trim());
            //personal info updates
            String firstName = nullIfEmpty(safe(data, 3));
            String lastName = nullIfEmpty(safe(data, 4));
            String phoneNum = nullIfEmpty(safe(data, 5));
            String address = nullIfEmpty(safe(data, 6));
            String email = nullIfEmpty(safe(data, 7));

            // job info updates
            String jobTitle = nullIfEmpty(safe(data, 9));
            String jobDept = nullIfEmpty(safe(data, 11));
            //String status = safe(data, 13);
        //    double salary = Double.parseDouble(data[14]);

            Double salary = null;
            if(data.length > 14 && data[14] != null && !data[14].isEmpty()){
                salary = Double.parseDouble(data[14]);
            }

            Connection conn = ConnToDB.getConnection();

            //this will find positionId
            Integer positionID = null;
            if(jobTitle != null && !jobTitle.isEmpty()){
                String sql = "SELECT PositionID FROM JobPosition WHERE Title = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, jobTitle);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    positionID = rs.getInt("PositionID");
                }
            }

            //this finds DeptId
            Integer DeptID = null;
            if(jobDept != null && !jobDept.isEmpty()){
                String sql = "SELECT DeptID FROM Department WHERE DeptName = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, jobDept);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    DeptID = rs.getInt("DeptID");
                }
            }

            // use COALESCE TO make sure update happen even if theres a null
            String sql = """
                    UPDATE Employee 
                    SET FirstName = COALESCE(?, FirstName),
                    LastName = COALESCE(?, LastName),
                    PhoneNum = COALESCE(?, PhoneNum),
                    Address = COALESCE(?, Address),
                    Email = COALESCE(?, Email),
                    Salary = COALESCE(?, Salary),
                    PositionID = COALESCE(?,PositionID),
                    DeptID = COALESCE(?,DeptID)
                    WHERE EmployeeID = ?""";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, phoneNum);
            ps.setString(4, address);
            ps.setString(5, email);
           //ps.setDouble(6, salary);   this isnt working due to it being declared null
            if(salary != null){
                ps.setDouble(6, salary);
            } else{
                ps.setNull(6, java.sql.Types.DOUBLE);
            }

            if(positionID != null){
                ps.setInt(7, positionID);
            } else{
                ps.setNull(7, java.sql.Types.INTEGER);
            }

            if(DeptID != null){
                ps.setInt(8, DeptID);
            }else{
                ps.setNull(8, java.sql.Types.INTEGER);
            }

            ps.setInt(9, employeeID);

            ps.executeUpdate();

            System.out.println("Employee Updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
