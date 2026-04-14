package Model;

public class Manager extends Employee {

    private String managerLevel;
    private String startDate;
    private String managerQualifications;
    private String managementStyle;

    //get and sets
    //managerLevel
    public String getManagerLevel() {
        return managerLevel;
    }
    public void setManagerLevel(String managerLevel) {
        this.managerLevel = managerLevel;
    }

    //start date
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    //managerQualifications
    public String getManagerQualifications() {
        return managerQualifications;
    }
    public void setManagerQualifications(String managerQualifications) {
        this.managerQualifications = managerQualifications;
    }

    // managementStyle
    public String getManagementStyle() {
        return managementStyle;
    }
    public void setManagementStyle(String managementStyle) {
        this.managementStyle = managementStyle;
    }


}
