package com.example.jvlaranamma.service;

import android.util.Log;

import com.example.jvlaranamma.async.SongsLoaderAsyncTask;
import com.example.jvlaranamma.models.Song;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Set;
import java.util.regex.Pattern;

import static com.example.jvlaranamma.Constants.WEBURLTOANTLIGENMANDAG;

/**
 * Created by johanr on 2014-01-03.
 */
public class ArchiveSongs extends SerializableService<Set<Song>>{

    private final static String songFileName = "songs";
    private static ArchiveSongs instance;

    @Override
    public String getFilename() {
        return songFileName;
    }

    public static ArchiveSongs getInstance() {
        if (instance == null) {
            instance = new ArchiveSongs();
        }
        return instance;
    }
    public Song[] getSongs(final SongsLoaderAsyncTask.UpdateCallback updateCallback, final boolean fullRefresh) {
        String webContent = getWebContent(WEBURLTOANTLIGENMANDAG);
        if(webContent.length()!=0){


        }

        return null;

    }
    private String getWebContent(String URL){
        String resString;
        boolean startAdding = false;
        try {
            HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client
            HttpGet httpget = new HttpGet(URL); // Set the action you want to do
            HttpResponse response = httpclient.execute(httpget); // Executeit
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent(); // Create an InputStream with the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){ // Read line by line
                line = line.trim();
                if(line.compareToIgnoreCase("var mediaPlaylist = new Playlist(\"1\", [")==0)   {
                    startAdding = true;
                }else{
                    if(startAdding){
                        if(line.compareToIgnoreCase("], {")==0){
                            startAdding = false;
                            break;
                        }else{
                            sb.append(line + "\n");
                        }

                    }
                }
            }

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

}
