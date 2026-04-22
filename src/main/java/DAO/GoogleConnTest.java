package DAO;

//Imports
import DAO.FormReader;

public class GoogleConnTest {

    public static void main(String[] args) {

        String[] data = FormReader.getLatestResponse();

        System.out.println("Employee ID: " + data[1]);
        System.out.println("First Name: " + data[6]);
        System.out.println("Last Name: " + data[7]);
        System.out.println("Email: " + data[11]);
    }
}
