package PingSoftware.PingSoftware;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author pc
 */
public class PingImplementation implements PingService {

    private final int requestConnectionTimeout = 6000;
    private final int requestReadTimeout = 5000;
    long pingTime = -1;

    @Override
    public PingResult ping(String ipOrHostname) {
        PingResult pingResult = new PingResult();
        try {
            if (!ipOrHostname.contains("https")) {
                ipOrHostname = "https://" + ipOrHostname;
            }
            URL url = new URL(ipOrHostname);
            final long startTime = System.currentTimeMillis();
            URLConnection urlConnection = url.openConnection();

            urlConnection.setConnectTimeout(requestConnectionTimeout);
            urlConnection.setReadTimeout(requestReadTimeout);

            int responseCode = -1;
            try {
                if (ipOrHostname.startsWith("https")) {
                    responseCode = ((HttpsURLConnection) urlConnection).getResponseCode();

                } else {
                    responseCode = ((HttpURLConnection) urlConnection).getResponseCode();
                }
            } catch (Exception ex) {
                pingResult.setMessage("connection timed out");
              
            }
            final long endTime = System.currentTimeMillis();
            pingResult.setResponseCode(responseCode);

            if (responseCode == 200) {
                 pingResult.setMessage(getResponseMessage(responseCode));
                pingTime = endTime - startTime;
                pingResult.setPingTime(pingTime);
                pingResult.setIsOK(true);
            } else {
                    pingResult.setMessage(getResponseMessage(responseCode));
                pingTime = -1;
                pingResult.setPingTime(pingTime);
                pingResult.setIsOK(false);
            }

        } catch (Exception ex) {
         	pingResult.setResponseCode(-1);
            pingResult.setMessage("connection timed out");
           
        }

        return pingResult;
    }

    @Override
    public List ping(List ipOrHostnameList) {

        List returnResult = new ArrayList<PingResult>();

        for (int i = 0; i < ipOrHostnameList.size(); i++) {
            PingResult pingResult = new PingResult();
            String ipOrHostname = ipOrHostnameList.get(i).toString();
            if (!ipOrHostname.contains("https")) {
                ipOrHostname = "https://" + ipOrHostname;
            }
            try {
                URL url = new URL(ipOrHostname);
                final long startTime = System.currentTimeMillis();
                URLConnection urlConnection = url.openConnection();

                urlConnection.setConnectTimeout(requestConnectionTimeout);
                urlConnection.setReadTimeout(requestReadTimeout);

                int responseCode = -1;
                try {
                    if (ipOrHostname.startsWith("https")) {
                        responseCode = ((HttpsURLConnection) urlConnection).getResponseCode();

                    } else {
                        responseCode = ((HttpURLConnection) urlConnection).getResponseCode();
                    }
                } catch (Exception ex) {
                    pingResult.setMessage("connection timed out");
                    
                }
                final long endTime = System.currentTimeMillis();
                pingResult.setResponseCode(responseCode);

                if (responseCode == 200) {
                    pingResult.setMessage("Ping OK...");
                    pingTime = endTime - startTime;
                    pingResult.setPingTime(pingTime);
                    pingResult.setIsOK(true);
                } else {
               
                        pingResult.setMessage(getResponseMessage(responseCode));
                   
                    pingTime = -1;
                    pingResult.setPingTime(pingTime);
                    pingResult.setIsOK(false);
                }

            } catch (Exception ex) {
            	pingResult.setResponseCode(-1);
                pingResult.setMessage("connection timed out");
         
            }
            returnResult.add(pingResult);

        }

        return returnResult;
    }

    public String getResponseMessage(int responseCode) {
        String retString = "";
        switch (responseCode) {
            case 200:
                retString = "OK";
                break;
            case 403:
                retString = "Forbidden";
                break;
            case 404:
                retString = "Not Found";
                break;
            case 400:
                retString = "Bad Request";
                break;
            case 401:
                retString = "Unauthorized";
                break;

        }
        return retString;
    }

}
