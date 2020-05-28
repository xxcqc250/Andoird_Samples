package com.example.downloadfilefromhost;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class HttpService extends AsyncTask<Void, Integer, Void> {

    @SuppressLint("StaticFieldLeak")
    private Activity mActivity;
    @SuppressLint("StaticFieldLeak")
    private ProgressBar mProgressBar;
    @SuppressLint("StaticFieldLeak")
    private TextView progressText;
    private AlertDialog alertDialog;

    public AsyncResponse ResponseListener;



    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public interface AsyncResponse {
        void getAsyncResponseData();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        alertDialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.i("values", "" + values[0]);
        mProgressBar.setProgress(values[0]);
        progressText.setText(values[1] + " / " + values[2]);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressBar = new ProgressBar(mActivity.getApplicationContext(),null, android.R.attr.progressBarStyleHorizontal);
//        mProgressBar.setIndeterminate(true); // 設定要不要跑進度條
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);
        mProgressBar.setVisibility(View.VISIBLE);
//        mProgressBar.setPadding(100,5,5,100);

        progressText = new TextView(mActivity);
        progressText.setText("TEST");
//        progressText.setPadding(100,5,5,100);
        LinearLayout linearLayout = new LinearLayout(mActivity);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(mProgressBar);
        linearLayout.addView(progressText);
        linearLayout.setPadding(100,5,100,5);




        alertDialog = new AlertDialog.Builder(mActivity).create();
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Download Data");
//        alertDialog.setView(mProgressBar);
        alertDialog.setView(linearLayout);
        alertDialog.show();




    }

    @Override
    protected Void doInBackground(Void... aVoid) {
        InputStream input = null;
        String fileUrl = "https://www.w3schools.com/html/mov_bbb.mp4";
//        String fileUrl = "https://www.w3schools.com/html/horse.ogg";
//        String fileUrl = "https://drive.google.com/file/d/10x1LiPrBc2a0-xZ9CCf1eKZenTxtnkVO/view?usp=drivesdk";
//        String fileUrl = "http://172.16.61.120:8000/1001-Technical%20Support-20200324-2.mp4";
        try {
            int totalLength = 1;
            URL url = new URL(fileUrl);

            //Open a connection to that URL.
            URLConnection ucon = url.openConnection();
            totalLength = ucon.getContentLength();

            if (totalLength == -1)
                return null;

            Log.i("LENGTH", "" + ucon.getContentLength());

            File file = new File(mActivity.getApplicationContext().getExternalFilesDir("video"), "test2.mp4");

            //Define InputStreams to read from the URLConnection.
            // uses 3KB download buffer
            InputStream is = ucon.getInputStream();
            BufferedInputStream inStream = new BufferedInputStream(is, 1024 * 5);
            FileOutputStream outStream = new FileOutputStream(file);
            byte[] buff = new byte[5 * 1024];

            //Read bytes (and store them) until there is nothing more to read(-1)
            int len, accumulatedLength = 0;
            while ((len = inStream.read(buff)) != -1)
            {
                Log.i("LEN", "" + len);
                accumulatedLength += len;
                outStream.write(buff,0,len);
                publishProgress((int)(accumulatedLength*100/totalLength), accumulatedLength, totalLength);
//                TimeUnit.SECONDS.sleep(1);
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


//        int count = 0;
//        while(true){
//            if (count<100){
//
//                count += 1;
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            else
//                break;
//        }

        Log.i("CSL", "COMPLETE");
        return null;
    }
}
