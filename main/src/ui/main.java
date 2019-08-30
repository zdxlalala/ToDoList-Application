package ui;

import com.oracle.javafx.jmx.json.JSONException;
import model.RunToDoList;
import model.parser.LibraryParser;
import org.json.JSONObject;

import java.io.*;
import java.text.ParseException;
import java.net.URL;


public class main{

    public   main(String[] args) throws ParseException, IOException {
        // write your code here
        BufferedReader br = null;

        try {
            String apikey = "aabf20d12f6f6a0423025a69010973d4"; //fill this in with the API key they email you
            String vancouverweatherquery = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=";
            String theURL=vancouverweatherquery+apikey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            LibraryParser libParser = new LibraryParser();

            String line;
            String fileName = "library.json";

            StringBuilder sb = new StringBuilder();

            PrintWriter writer = new PrintWriter(fileName,"UTF-8");

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append("\n");
                writer.println(line);
                writer.close();

            }

            libParser.parseInfo1(new JSONObject(sb.toString()));
        } catch (org.json.JSONException e) {
            System.out.println("Error parsing JSON data");
            e.printStackTrace();
        } finally {

            if (br != null) {
                br.close();
            }
        }


        RunToDoList RunToDoList = new RunToDoList();
        RunToDoList.runApp();

    }


}