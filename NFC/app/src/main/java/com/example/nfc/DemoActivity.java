package com.example.nfc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DemoActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Map<String, String>> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        listView = (ListView)findViewById(R.id.demo_list_view);

        createData();
        BaseDetailAdapter adapter = new BaseDetailAdapter(this, dataList, this);

        listView.setAdapter(adapter);

    }



    private void createData() {
        Map <String, String> map;
        map = new HashMap<>();
        map.put("name", "uhf__3020__uhf.pgs");
        dataList.add(map);

        map = new HashMap<>();
        map.put("name", "uhf__3020__uhf3020.pgs");
        dataList.add(map);

        map = new HashMap<>();
        map.put("name", "test3");
        dataList.add(map);

        map = new HashMap<>();
        map.put("name", "test4");
        dataList.add(map);

        map = new HashMap<>();
        map.put("name", "test5");
        dataList.add(map);
    }
}
