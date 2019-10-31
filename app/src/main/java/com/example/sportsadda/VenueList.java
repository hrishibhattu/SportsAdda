package com.example.sportsadda;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sportsadda.models.Booking;
import com.example.sportsadda.models.Venue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;

public class VenueList extends ArrayAdapter<Venue> {
     private Activity context;
     private List<Venue> venueList;
     private DatabaseReference mUser;
     private FirebaseAuth mAuth;
     private DatabaseReference mVenueOwner;
     private int mYear,mMonth,mDay;
     private Spinner time;


    public VenueList(Activity context,List<Venue> venueList){
         super(context,R.layout.list_layout,venueList);
         this.context = context;
         this.venueList = venueList;
     }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        final View listViewItem = inflater.inflate(R.layout.list_layout,null,false);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewSports = (TextView) listViewItem.findViewById(R.id.textViewSports);
        TextView textViewLocation = (TextView) listViewItem.findViewById(R.id.textViewLocation);
        TextView textViewCost = (TextView) listViewItem.findViewById(R.id.textViewCost);
        final TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textViewDate);
        Button book = (Button) listViewItem.findViewById(R.id.book);
        Button btn_date = (Button) listViewItem.findViewById(R.id.btn_date);
        Button get_location = (Button)listViewItem.findViewById(R.id.bookDate);

//        final String selected_time = time.getSelectedItem().toString();
//        Toast.makeText(context, "Time is " + selected_time, Toast.LENGTH_SHORT).show();

        final Venue venue = venueList.get(position);
        textViewName.setText("Venue :" + venue.getvName());
        textViewSports.setText("Sport :" + venue.getvSport());
        textViewCost.setText("Cost/hr :" + venue.getvCost());
        textViewLocation.setText("Location :"+venue.getvLocation());


        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(listViewItem.getContext(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        textViewDate.setText(dayOfMonth + "-" + (monthOfYear +1 ) + "-" + year);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latitude = "latitude",longitude="longitude";
                Intent intent = new Intent(getContext(),MapActivity.class);
                intent.putExtra(latitude,venue.getvLatitute());
                intent.putExtra(longitude,venue.getvLongitude());
                context.startActivity(intent);
            }
        });


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uId = user.getUid();
                mUser = FirebaseDatabase.getInstance().getReference("users");

                time = (Spinner)listViewItem.findViewById(R.id.spinnerTime);
                String selected_time = time.getSelectedItem().toString();

                mVenueOwner = FirebaseDatabase.getInstance().getReference("users").child(venueList.get(position).getUserId()).child("booking");
                String key = mUser.push().getKey().toString();  // this new generated key would act as bookingId
                Booking entry = new Booking(key,venueList.get(position).getvName(),venueList.get(position).getvSport(),textViewDate.getText().toString(),selected_time);

                mVenueOwner.child(key).setValue(entry);
                mUser.child(mAuth.getUid()).child("booked").child(key).setValue(entry);
                Toast.makeText(getContext(),"Booked",Toast.LENGTH_LONG).show();
                Toast.makeText(context, "Time is " + selected_time, Toast.LENGTH_SHORT).show();

            }
        });

        return listViewItem;
    }

}
