package model.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LibraryParser {

    public void parseInfo1(JSONObject info) throws JSONException {
        JSONArray wt = info.getJSONArray("weather");
        JSONObject description = wt.getJSONObject(0);
        String weather = description.getString("description");

        JSONObject main = info.getJSONObject("main");
        Integer temp = main.getInt("temp");

        String city = info.getString("name");

        JSONObject sys = info.getJSONObject("sys");
        String country = sys.getString("country");

        System.out.println("Weather: " + weather);
        System.out.println("Temperature: " + temp);
        System.out.println("City: " + city);
        System.out.println("Country: " + country);
        System.out.println();
    }

    public String parseInfo2(JSONObject info) throws JSONException {
        JSONArray wt = info.getJSONArray("weather");
        JSONObject description = wt.getJSONObject(0);
        String weather = description.getString("description");

        JSONObject main = info.getJSONObject("main");
        Integer temp = main.getInt("temp");

        String city = info.getString("name");

        JSONObject sys = info.getJSONObject("sys");
        String country = sys.getString("country");

        return ("Weather: " + weather +"; "+
                "Temperature: " + temp +"; "+
                "City: " + city +"; "+
                "Country: " + country);

    }
}
