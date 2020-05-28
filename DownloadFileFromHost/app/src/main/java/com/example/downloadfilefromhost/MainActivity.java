package com.example.downloadfilefromhost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.io.File;

public class MainActivity extends AppCompatActivity implements HttpService.AsyncResponse {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
//        File SDCardRoot = Environment.getDownloadCacheDirectory(); // location where you want to store
        File SDCardRoot = this.getExternalFilesDir("video");

        File file[] = SDCardRoot.listFiles();
        for (File value : file) {
            Log.i("CSL", "" + value);
        }

        button.setOnClickListener(this::click);
    }

    private void click(View view) {
        HttpService http_server = new HttpService();
        http_server.setActivity(this);
        http_server.ResponseListener = this; // 設定回傳的interface
        http_server.execute();
    }

    @Override
    public void getAsyncResponseData() {

    }
}
