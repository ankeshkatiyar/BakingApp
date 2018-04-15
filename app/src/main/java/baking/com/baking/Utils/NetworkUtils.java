package baking.com.baking.Utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;


public class NetworkUtils {

    private final static String  BAKING_RECIPES_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private static URL buildURL(){
        Uri uri = Uri.parse(BAKING_RECIPES_URL).buildUpon().build();
        try {
            return  new URL(uri.toString());
        } catch (MalformedURLException e) {

            e.printStackTrace();
            return null;
        }
    }

    public static String getResponseFromHttp() throws  IOException{

        URL url =  buildURL();
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

        try {

            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner sc = new Scanner(inputStream);
            sc.useDelimiter("//A");
            if (sc.hasNext()) {
                return sc.next();
            } else {

                return null;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            httpURLConnection.disconnect();
        }

        return null;
    }
}

