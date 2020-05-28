package com.example.nfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    private Button  sendFileBtn;
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendFileBtn = (Button)findViewById(R.id.button);
        sendFileBtn.setOnClickListener(this::sendFile);

        PackageManager pm = this.getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_NFC)){
            Toast.makeText(this, "the device does not has NFC hardware",
                    Toast.LENGTH_SHORT).show();
        }
        else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN){
            Toast.makeText(this, "Android beam is not supported",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void sendFile(View v) {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (!nfcAdapter.isEnabled()){
            Toast.makeText(this, "Please Enable NFC",
                    Toast.LENGTH_SHORT).show();

            startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
        }
        else if(!nfcAdapter.isNdefPushEnabled()){
            Toast.makeText(this, "Please Enable Android Beam",
                    Toast.LENGTH_SHORT).show();

            startActivity(new Intent(Settings.ACTION_NFCSHARING_SETTINGS));
        }
        else{
            String fileName = "IMG_20200512_112420.jpg";

            File fileDirectory = Environment
                    .getExternalStoragePublicDirectory("beam");

            File fileToTransfer = new File(fileDirectory, fileName);
            fileToTransfer.setReadable(true,false);

            // 傳送file
//            nfcAdapter.setBeamPushUris(new Uri[]{Uri.fromFile(fileToTransfer),Uri.fromFile(fileToTransfer)}, this);
//            nfcAdapter.setBeamPushUris(new Uri[]{Uri.fromFile(fileToTransfer),Uri.fromFile(fileToTransfer)}, this);


            NdefRecord mimeRecord = NdefRecord.createMime("application/vnd.com.example.android.beam",
                    "Beam me up, Android".getBytes(Charset.forName("US-ASCII")));

            NdefMessage ndefMessage = new NdefMessage(mimeRecord);

            nfcAdapter.setNdefPushMessage(ndefMessage, this);

//            Log.i("CONSOLE", ""+ Uri.fromFile(fileToTransfer));



        }
    }
}
