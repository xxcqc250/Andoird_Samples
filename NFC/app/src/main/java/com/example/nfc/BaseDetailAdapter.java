package com.example.nfc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

public class BaseDetailAdapter extends BaseAdapter {

    Context m_context;
    ArrayList<Map<String, String>> dataList;
    ListView listView;
    private NfcAdapter nfcAdapter;
    Activity activity;


    public BaseDetailAdapter(Context context, ArrayList<Map<String, String>> dataList, Activity activity){
        this.m_context = context;
        this.dataList = dataList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View v, ViewGroup viewGroup) {
        View view = v;
        Holder holder;

        view = LayoutInflater.from(m_context).inflate(R.layout.adapter_base_detail, null);

        holder = new Holder();
        holder.button = (Button) view.findViewById(R.id.button);
        holder.textView = (TextView) view.findViewById(R.id.textview);

        view.setTag(holder);

        Log.d("CONSOLE", "baseAdapter " + dataList.get(position));

        holder.textView.setText(dataList.get(position).get("name"));
        setLayoutHeight((View)holder.textView);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nfcAdapter = NfcAdapter.getDefaultAdapter(m_context);
                NdefRecord mimeRecord = NdefRecord.createMime("application/vnd.com.example.android.beam",
                        dataList.get(position).get("name").getBytes(Charset.forName("US-ASCII")));

                NdefMessage ndefMessage = new NdefMessage(mimeRecord);
                nfcAdapter.setNdefPushMessage(ndefMessage, activity);
            }
        });

        if (position % 2 == 0){
            view.setBackgroundColor(m_context.getColor(R.color.colorAdapterDark));
        }
        else{
            view.setBackgroundColor(m_context.getColor(R.color.colorAdapterBright));
        }
        return view;
    }

    private void nfcTransfer(View view) {
        nfcAdapter = NfcAdapter.getDefaultAdapter(m_context);

        if (!nfcAdapter.isEnabled()){
            Toast.makeText(m_context, "Please Enable NFC",
                    Toast.LENGTH_SHORT).show();


        }
        else if(!nfcAdapter.isNdefPushEnabled()){
            Toast.makeText(m_context, "Please Enable Android Beam",
                    Toast.LENGTH_SHORT).show();


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

            nfcAdapter.setNdefPushMessage(ndefMessage, null);

//            Log.i("CONSOLE", ""+ Uri.fromFile(fileToTransfer));



        }
    }

    public void setLayoutHeight(View widget) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) widget.getLayoutParams();
        lp.height = 400;
        widget.setLayoutParams(lp);
    }


    class Holder{
        Button button;
        TextView textView;
    }
}
