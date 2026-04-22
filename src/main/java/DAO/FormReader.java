package DAO;

//Imports
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class FormReader {

    public static String[] getLatestResponse(){

        try {
            String csvUrl = "https://docs.google.com/spreadsheets/d/1ZMIHPJb1-pTTez2X3xwKrUO82RJci7Nh2VMYdIndZnA/export?format=csv";

            URL url = new URL(csvUrl);

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            String lastLine = "";

            while((line = bufferedReader.readLine()) !=null){
                lastLine = line;
            }

            bufferedReader.close();

            return lastLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
