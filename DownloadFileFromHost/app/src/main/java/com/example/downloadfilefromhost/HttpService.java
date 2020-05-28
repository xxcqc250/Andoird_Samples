package com.example.downloadfilefromhost;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpService extends AsyncTask<Void, Void, Void> {

    private Activity activity;
    public AsyncResponse ResponseListener;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public interface AsyncResponse {
        void getAsyncResponseData();
    }

    @Override
    protected Void doInBackground(Void... aVoid) {
        InputStream input = null;
//        String fileUrl = "https://www.w3schools.com/html/mov_bbb.mp4";
        String fileUrl = "http://172.16.61.120:8000/1001-Technical%20Support-20200324-2.mp4";
        try {

            URL url = new URL(fileUrl);
            long startTime = System.currentTimeMillis();

            //Open a connection to that URL.
            URLConnection ucon = url.openConnection();

            File file = new File(activity.getApplicationContext().getExternalFilesDir("video"), "test2.mp4");

            //Define InputStreams to read from the URLConnection.
            // uses 3KB download buffer
            InputStream is = ucon.getInputStream();
            BufferedInputStream inStream = new BufferedInputStream(is, 1024 * 5);
            FileOutputStream outStream = new FileOutputStream(file);
            byte[] buff = new byte[5 * 1024];

            //Read bytes (and store them) until there is nothing more to read(-1)
            int len;
            while ((len = inStream.read(buff)) != -1)
            {
                outStream.write(buff,0,len);
            }

            //clean up
            outStream.flush();
            outStream.close();
            inStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("CSL", "COMPLETE");
        return null;
    }
}
