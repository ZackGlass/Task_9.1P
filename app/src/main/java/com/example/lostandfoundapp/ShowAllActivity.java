package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lostandfoundapp.data.DatabaseHelper;
import com.example.lostandfoundapp.model.Advert;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    ListView adListView;
    ArrayList<String> adArrayList;
    ArrayList<String> adIDArrayList;
    ArrayAdapter<String> adapter;
    List<Advert> adList;

    private AdapterView.OnItemClickListener itemClickedHandler = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getApplicationContext(), ShowAdvertActivity.class);
            intent.putExtra("selection_id", (Parcelable) adList.get(i));
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        adListView = findViewById(R.id.adListView);

    }

    @Override
    protected void onResume() {

        super.onResume();

        adArrayList = new ArrayList<>();
        adIDArrayList = new ArrayList<>();

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        adList = db.fetchAllAds();

        for (Advert advert :adList)
        {
            adArrayList.add(advert.getAdName());
            adIDArrayList.add(Integer.toString(advert.getAd_id()));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adArrayList);
        adListView.setAdapter(adapter);



        adListView.setOnItemClickListener(itemClickedHandler);
    }

}

