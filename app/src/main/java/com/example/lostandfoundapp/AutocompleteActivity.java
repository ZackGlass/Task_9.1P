package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;

public class AutocompleteActivity extends AppCompatActivity {

    public static final String TAG = "Running";
    public static final int AUTOCOMPLETE_REQUEST_CODE = 1;
    public static final int RESULT_ERROR = 0;
    Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK)
            {
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getLatLng());
                finishActivity(AUTOCOMPLETE_REQUEST_CODE);
            }
            else if (resultCode == RESULT_ERROR)
            {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
                finishActivity(AUTOCOMPLETE_REQUEST_CODE);
            }
            else if (resultCode == RESULT_CANCELED)
            {
                // The user canceled the operation.
                finishActivity(AUTOCOMPLETE_REQUEST_CODE);
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}