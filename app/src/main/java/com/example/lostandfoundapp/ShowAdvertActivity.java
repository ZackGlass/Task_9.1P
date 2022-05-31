package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lostandfoundapp.data.DatabaseHelper;
import com.example.lostandfoundapp.model.Advert;

public class ShowAdvertActivity extends AppCompatActivity {

    TextView itemNameTextView;
    TextView itemPhoneTextView;
    TextView itemDescriptionTextView;
    TextView itemDateTextView;
    TextView itemLocationTextView;
    Button removeButton;
    DatabaseHelper db;
    Advert selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_advert);

        Intent intent = getIntent();
        selection = intent.getParcelableExtra("selection_id");

        itemNameTextView = findViewById(R.id.itemNameTextView);
        itemPhoneTextView = findViewById(R.id.itemPhoneTextView);
        itemDescriptionTextView = findViewById(R.id.itemDescriptionTextView);
        itemDateTextView = findViewById(R.id.itemDateTextView);
        itemLocationTextView = findViewById(R.id.itemLocationTextView);
        removeButton = findViewById(R.id.removeButton);


        db = new DatabaseHelper(getApplicationContext());

        itemNameTextView.setText(selection.getAdName());
        itemPhoneTextView.setText("Contact Number: " + selection.getPhone());
        itemDescriptionTextView.setText(selection.getDescription());
        itemDateTextView.setText("Date Found: " + selection.getDate());
        itemLocationTextView.setText("Location Found: " + selection.getLocationName());

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteAd(selection);
                finish();
            }
        });

    }
}