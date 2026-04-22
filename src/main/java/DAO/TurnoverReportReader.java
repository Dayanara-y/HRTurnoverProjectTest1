package DAO;

//imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class TurnoverReportReader {
    public static ArrayList<String[]> getAllResponses() {
        ArrayList<String[]> responses = new ArrayList<>();

        try{
            String csvURL = "https://docs.google.com/spreadsheets/d/1UcpCQflvwPIG0o09_T_BlnQn9A_f1zLiutnezffsiwM/export?format=csv";
            URL url = new URL(csvURL);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;


            while((line = br.readLine()) != null){
                responses.add(line.split(","));
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return responses;
    }

}
