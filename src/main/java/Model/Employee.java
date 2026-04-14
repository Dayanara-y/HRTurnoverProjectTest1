package Model;

public class Employee {
    //Private classes
    private int EmployeeID;
    private String FirstName;
    private String LastName;
    private String DOB;
    private String Address;
    private String PhoneNum;
    private String Email;
    private String HireDate;
    private String Salary;

    //Get & Sets -------------------------------------------
    //EmployeeId
    public int getEmployeeID() {
        return EmployeeID;
    }
    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    //FirstName
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    //LastName
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String LastName) {
        this.LastName = LastName;

    }

    //DOB
    public String getDOB() {
        return DOB;
    }
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    //Address
    public String getAddress() {
        return Address;

    }
    public void setAddress(String Address) {
        this.Address = Address;
    }

    // PhoneNum
    public String getPhoneNum() {
        return PhoneNum;
    }
    public void setPhoneNum(String PhoneNum) {
        this.PhoneNum = PhoneNum;
    }

    // Email
    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }

    // HireDate
    public String getHireDate() {
        return HireDate;
    }
    public void setHireDate(String HireDate) {
        this.HireDate = HireDate;
    }

    // Salary
    public String getSalary() {
        return Salary;
    }
    public void setSalary(String Salary) {
        this.Salary = Salary;
    }

}
