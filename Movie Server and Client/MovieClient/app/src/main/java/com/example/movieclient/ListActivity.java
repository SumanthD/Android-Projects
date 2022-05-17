package com.example.movieclient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class ListActivity extends AppCompatActivity {
    private ListView list;

    public ArrayList<String> mlist;
    public ArrayList<String> mdirectors;
    public ArrayList<String> murls;
    private movieInfo info;
    ArrayList<HashMap<String, String>> data;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        data = new ArrayList<HashMap<String, String>>();
        list = (ListView) findViewById(R.id.listView);
        mlist= getIntent().getStringArrayListExtra("m");
        mdirectors= getIntent().getStringArrayListExtra("d");
        murls= getIntent().getStringArrayListExtra("u");

        for(int i=0;i<mlist.size();i++){
            HashMap<String,String> datum = new HashMap<String, String>();
            datum.put("Movie Title", mlist.get(i));
            datum.put("Directors", mdirectors.get(i));
            data.add(datum);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2, new String[] {"Movie Title", "Directors"}, new int[] {android.R.id.text1, android.R.id.text2});
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ListActivity.this,WebActivity.class);
                intent.putExtra("u",i);
                intent.putStringArrayListExtra("ur", murls);
                startActivity(intent);
            }
        });

    }

}