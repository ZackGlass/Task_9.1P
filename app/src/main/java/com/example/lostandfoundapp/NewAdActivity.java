package com.example.lostandfoundapp;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.Arrays;
import java.util.List;

public class NewAdActivity extends AppCompatActivity {

    RadioGroup radioButtonGroup;
    RadioButton lostRadioButton;
    RadioButton foundRadioButton;
    EditText adNameEditText;
    EditText adPhoneEditText;
    EditText adDescriptionEditText;
    EditText adDateEditText;
    EditText adLocationEditText;
    Button currentLocationButton;
    Button saveButton;
    DatabaseHelper db;
    Place place;
    String placeName;
    LatLng placeLongLat;
    String currentLat;
    String currentLng;
    boolean autoLocation;

    LocationManager locationManager;
    LocationListener locationListener;
    Location currentLocation;


    public static final String TAG = "Running";
    public static final int AUTOCOMPLETE_REQUEST_CODE = 1;
    private static final int RESULT_ERROR = 0;
    private static final String API_KEY = "";

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        currentLocationButton = findViewById(R.id.currentLocationButton);
        saveButton = findViewById(R.id.saveButton);
        db = new DatabaseHelper(getApplicationContext());
        lostRadioButton.toggle();
        autoLocation = false;

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                currentLocation = location;
                currentLat = Double.toString(location.getLatitude());
                currentLng = Double.toString(location.getLongitude());
            }
        };

        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

        Places.initialize(getApplicationContext(), API_KEY);
        PlacesClient placesClient = Places.createClient(getApplicationContext());


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
                String adLat = "";
                String adLng = "";
                String adLocationName = "";


                if (autoLocation == true)
                {
                    adLat = Double.toString(placeLongLat.latitude);
                    adLng = Double.toString(placeLongLat.longitude);
                    adLocationName = placeName;
                }
                else if (autoLocation == false)
                {
                    if (TextUtils.isEmpty(adLocationEditText.getText()))
                    {
                        Toast.makeText(getApplicationContext(), "Advert Must Have a Location.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        adLat = currentLat;
                        adLng = currentLng;
                        adLocationName = currentLat + ", " + currentLng;
                    }
                }


                if (!TextUtils.isEmpty(adNameEditText.getText()) && !adLat.equals("") && !adLng.equals("") && !adLocationName.equals(""))
                {
                    long result = db.insertAd(new Advert(adName, adPhone, adDescription, adDate, adLocationName, adLat, adLng));
                    finish();
                }
                else
                {

                    Toast.makeText(getApplicationContext(), "Ad must have an item name and location.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        currentLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentLat != null && currentLng != null) {

                    adLocationEditText.setText("Current Location");
                    autoLocation = false;
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Current Location not found.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        adLocationEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Place.Field> fields = Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG);

                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(getApplicationContext());

                autoLocation = true;
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK)
            {
                place = Autocomplete.getPlaceFromIntent(data);
                placeName = place.getName();
                placeLongLat = place.getLatLng();
                adLocationEditText.setText(placeName);
                autoLocation = true;

            }
            else if (resultCode == RESULT_ERROR)
            {
                Toast.makeText(getApplicationContext(), "Selection cancelled", Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(), "Selection cancelled", Toast.LENGTH_SHORT).show();
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}