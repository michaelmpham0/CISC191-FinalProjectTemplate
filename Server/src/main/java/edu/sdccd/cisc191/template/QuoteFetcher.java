package edu.sdccd.cisc191.template;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class QuoteFetcher {

    public static String fetchGameQuote() {
        String urlString = "https://www.ultima.rest/api/quote/game?title=elden+ring";
        StringBuffer responseContent = new StringBuffer();
        try {
            // opening connection to the url api
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // this just checks if there is a valid connection with the URL
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                // reading the response
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                }
            } else {
                System.out.println("Failed to fetch quote. HTTP error code: " + connection.getResponseCode());
                return "Failed to fetch quote.";
            }
            //parsing the text from the url to an JSON object
            JSONObject json = new JSONObject(responseContent.toString());
            // gets the string based on the key string "quote," as it is seen in the API
            String quote = json.getString("quote");

            return "\"" +quote+ "\"";

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch quote.";
        }
    }
}

