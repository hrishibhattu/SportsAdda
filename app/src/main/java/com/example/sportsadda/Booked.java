package com.example.sportsadda;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportsadda.models.Booking;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Booked extends AppCompatActivity {

    ListView listViewBooked;
    private DatabaseReference myBooking;
    private FirebaseAuth mAuth;
    private DatabaseReference ownerEntry;

    List<Booking> bookedList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);

        bookedList = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        myBooking = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getUid()).child("booked");
        listViewBooked = (ListView) findViewById(R.id.listViewBooked);


    }


    @Override
    protected void onStart() {
        super.onStart();
        myBooking.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookedList.clear();

                for(DataSnapshot bookingSnapshot:dataSnapshot.getChildren()){
                    Booking booking = bookingSnapshot.getValue(Booking.class);

                    bookedList.add(booking);
                }

                BookedList adapter = new BookedList(Booked.this,bookedList);
                listViewBooked.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
