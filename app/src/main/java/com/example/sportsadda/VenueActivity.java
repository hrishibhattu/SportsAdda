package com.example.sportsadda;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportsadda.models.Booking;
import com.example.sportsadda.models.Venue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VenueActivity extends AppCompatActivity  {

    private DatabaseReference myVenueRef;
    ListView listViewVenue;

    List<Venue> venueList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);

        venueList = new ArrayList<>();
        myVenueRef = FirebaseDatabase.getInstance().getReference("venues");
        listViewVenue = (ListView) findViewById(R.id.listViewVenue);

    }

    @Override
    protected void onStart() {
        super.onStart();
        myVenueRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                venueList.clear();

                for(DataSnapshot venueSnapshot:dataSnapshot.getChildren()){
                    Venue venue = venueSnapshot.getValue(Venue.class);

                    venueList.add(venue);
                }

                VenueList adapter = new VenueList(VenueActivity.this,venueList);
                listViewVenue.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
