package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lostandfoundapp.data.DatabaseHelper;
import com.example.lostandfoundapp.model.Advert;

public class NewAdActivity extends AppCompatActivity {

    RadioGroup radioButtonGroup;
    RadioButton lostRadioButton;
    RadioButton foundRadioButton;
    EditText adNameEditText;
    EditText adPhoneEditText;
    EditText adDescriptionEditText;
    EditText adDateEditText;
    EditText adLocationEditText;
    Button saveButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ad);

        radioButtonGroup = findViewById(R.id.radioButtonGroup);
        lostRadioButton = findViewById(R.id.lostRadioButton);
        foundRadioButton = findViewById(R.id.foundRadioButton);
        adNameEditText = findViewById(R.id.nameEditText);
        adPhoneEditText = findViewById(R.id.phoneEditText);
        adDescriptionEditText = findViewById(R.id.descriptionEditText);
        adDateEditText = findViewById(R.id.dateEditText);
        adLocationEditText = findViewById(R.id.locationEditText);
        saveButton = findViewById(R.id.saveButton);
        db = new DatabaseHelper(getApplicationContext());
        lostRadioButton.toggle();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String adName;

                if (lostRadioButton.isChecked() == true)
                {
                    adName = "Lost: " + adNameEditText.getText().toString();
                }
                else
                {
                    adName = "Found: " + adNameEditText.getText().toString();
                }


                String adPhone = adPhoneEditText.getText().toString();
                String adDescription = adDescriptionEditText.getText().toString();
                String adDate = adDateEditText.getText().toString();
                String adLocation = adLocationEditText.getText().toString();



                if (!TextUtils.isEmpty(adNameEditText.getText()))
                {
                    long result = db.insertAd(new Advert(adName, adPhone, adDescription, adDate, adLocation));
                    finish();
                }
                else
                {
                    Toast.makeText(NewAdActivity.this, "Ad must have an item name.", Toast.LENGTH_SHORT).show();
                }
            }
        });










    }


}