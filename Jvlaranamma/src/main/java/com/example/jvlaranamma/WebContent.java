package com.example.jvlaranamma;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by johanr on 2014-01-02.
 */
public class WebContent extends AsyncTask<String, Void, String> {
    private MainActivity mainActivity;

    public WebContent(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public String getWebContent(String URL){
        String resString;
        try {
            HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client
            HttpGet httpget = new HttpGet(URL); // Set the action you want to do
            HttpResponse response = httpclient.execute(httpget); // Executeit
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent(); // Create an InputStream with the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) // Read line by line
                sb.append(line + "\n");

            resString = sb.toString(); // Result is here
            Log.i("string letta", resString);

            is.close(); // Close the stream
        }
        catch (Exception e) {
            Log.i("Risultato eccezione","nn va");
            //e.printStackTrace();
            resString = "";
        }
        return resString;
    }

    @Override
    protected String doInBackground(String... strings) {

        return getWebContent(strings[0]);
        //return null;
    }

    @Override
    protected void onPostExecute(String response) {
        Log.i("Hej", "HTTP RESPONSE" + response);
        //textViewConsole.setText(response);
       // mainActivity.setWebcontent(response);

    }

    /*private class HttpTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... urls) {
            // TODO Auto-generated method stub
            String response = getWebContent(urls[0]);
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            Log.i(LOG_THREAD_ACTIVITY, "HTTP RESPONSE" + response);
            textViewConsole.setText(response);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

    }*/


}


