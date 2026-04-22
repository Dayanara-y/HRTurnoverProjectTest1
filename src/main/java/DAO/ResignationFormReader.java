package DAO;

//imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ResignationFormReader {

    public static List<String[]> getAllResponse(){
        List<String[]> responses = new ArrayList<>();

        try{
            String csvUrl = "https://docs.google.com/spreadsheets/d/1bT1uakGpJ659PFe7L4mVHAIqRazUmGHcjlVssFQ35bU/export?format=csv";

            URL url = new URL(csvUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            br.readLine();

            while ((line = br.readLine()) != null){
                responses.add(line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responses;
    }
}
