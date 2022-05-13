package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button newAdButton;
    Button showAllButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newAdButton = findViewById(R.id.newAdButton);
        newAdButton.setBackgroundResource(R.drawable.navbutton);

        showAllButton = findViewById(R.id.showAllButton);



        newAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewAdActivity.class);
                startActivity(intent);
            }
        });

        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

    }
}