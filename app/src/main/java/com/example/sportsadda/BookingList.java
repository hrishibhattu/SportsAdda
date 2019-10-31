package com.example.sportsadda;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sportsadda.models.Booking;

import java.util.List;

public class BookingList extends ArrayAdapter<Booking>{

    private Activity context;
    private List<Booking> bookingList;

    public BookingList(Activity context,List<Booking> bookingList){
        super(context,R.layout.list_booking,bookingList);
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewBooking = inflater.inflate(R.layout.list_booking,null,true);

        TextView bId = (TextView)listViewBooking.findViewById(R.id.bId);
        TextView bookingSport = (TextView)listViewBooking.findViewById(R.id.bookingSport);
        TextView bookingVenue = (TextView)listViewBooking.findViewById(R.id.bookingVenue);
        TextView bookingDate = (TextView)listViewBooking.findViewById(R.id.bookingDate);
        TextView bookingTime = (TextView)listViewBooking.findViewById(R.id.bookingTime);

        Booking booking = bookingList.get(position);

        bId.setText("BId:" + booking.getBookingId());
        bookingSport.setText("Sport:" + booking.getSport());
        bookingVenue.setText("Venue:"+booking.getVenue());
        bookingDate.setText("Date:"+ booking.getBooking_date());
        bookingTime.setText("Time:"+booking.getBooking_time());

        return listViewBooking;
    }
}