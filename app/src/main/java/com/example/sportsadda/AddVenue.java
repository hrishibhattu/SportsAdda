package com.example.sportsadda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportsadda.models.Venue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddVenue extends AppCompatActivity implements View.OnClickListener{

    private EditText vName,vLocation,vCost,vLatitude,vLongitude;
    private Spinner spinnerSports;
    private DatabaseReference mVenueRef;
    private DatabaseReference mUserRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvenue);

        mAuth = FirebaseAuth.getInstance();
        mVenueRef = FirebaseDatabase.getInstance().getReference();
        mUserRef = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getUid()).child("venue");
        //Views and Spinner
        vName = (EditText)findViewById(R.id.venueName);
        vLocation = (EditText)findViewById(R.id.venueLocation);
        vCost = (EditText)findViewById(R.id.venueCost);
        spinnerSports = (Spinner)findViewById(R.id.spinnerSports);
        vLatitude = (EditText)findViewById(R.id.latitute);
        vLongitude =(EditText)findViewById(R.id.longitute);


        //buttons
        findViewById(R.id.buttonVenue).setOnClickListener(this);
    }

    private void writeVenue(){
        String name = vName.getText().toString();
        String sports = spinnerSports.getSelectedItem().toString();
        String location = vLocation.getText().toString();
        String cost  = vCost.getText().toString();
        String id = mVenueRef.push().getKey();
        String latitude = vLatitude.getText().toString();
        String longitude = vLongitude.getText().toString();

        Venue venue = new Venue(mAuth.getUid(),id,name,sports,location,cost,latitude,longitude);
        mVenueRef.child("venues").child(id).setValue(venue);
        mUserRef.child(id).setValue(venue);

        Toast.makeText(AddVenue.this,"Venue Added",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if(i == R.id.buttonVenue){
            writeVenue();
        }
    }
}
