package com.example.sportsadda;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportsadda.models.Booking;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    ListView listViewBooking;
    private DatabaseReference myBooking;
    private FirebaseAuth mAuth;
    private DatabaseReference ownerEntry;

    List<Booking> bookingList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        bookingList = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        myBooking = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getUid()).child("booking");
        listViewBooking = (ListView) findViewById(R.id.listViewBooking);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myBooking.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookingList.clear();

                for(DataSnapshot bookingSnapshot:dataSnapshot.getChildren()){
                    Booking booking = bookingSnapshot.getValue(Booking.class);

                    bookingList.add(booking);
                }

                BookingList adapter = new BookingList(BookingActivity.this,bookingList);
                listViewBooking.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
