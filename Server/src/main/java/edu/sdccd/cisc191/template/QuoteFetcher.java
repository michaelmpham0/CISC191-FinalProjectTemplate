package edu.sdccd.cisc191.template;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class QuoteFetcher {

    public static String fetchGameQuote() {
        String urlString = "https://ultima.rest/api/random";
        StringBuffer responseContent = new StringBuffer();
        try {
            // opening connection to the url api
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // reading the response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
            }

            //parsing the text from the url to an JSON object
            JSONObject json = new JSONObject(responseContent.toString());
            String quote = json.getString("quote");

            System.out.println(quote);
            return quote;

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch quote.";
        }
    }
}

