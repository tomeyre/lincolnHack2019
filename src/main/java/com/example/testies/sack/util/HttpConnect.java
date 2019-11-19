package com.example.testies.sack.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnect {

    static String json = "";

    public String getJSONFromUrl(String url, String body) {

        try {
            URL u = new URL(url);
            HttpURLConnection restConnection = (HttpURLConnection) u.openConnection();
            restConnection.setRequestMethod("POST");
            restConnection.setRequestProperty("Content-Type", "application/json; utf-8");
            restConnection.setRequestProperty("Content-length", "0");
            restConnection.setUseCaches(false);
            restConnection.setDoOutput(true);
            restConnection.setAllowUserInteraction(false);
            restConnection.setConnectTimeout(10000);
            restConnection.setReadTimeout(10000);
            OutputStreamWriter wr = new OutputStreamWriter(restConnection.getOutputStream());
            wr.write(body);
            wr.flush();
            int status = restConnection.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(restConnection.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line;

                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    try {
                        json = sb.toString();
                    } catch (Exception e) {

                    }
                    return json;
            }
        } catch (MalformedURLException ex) {

        } catch (IOException ex) {

        }
        return null;
    }
}