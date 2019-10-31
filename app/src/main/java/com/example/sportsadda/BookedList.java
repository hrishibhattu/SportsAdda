package com.example.sportsadda;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sportsadda.models.Booking;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

public class BookedList extends ArrayAdapter<Booking> {
    private Activity context;
    private List<Booking> bookingList;
    private Button qrGenerator;
    public BookedList(Activity context, List<Booking> bookingList){
        super(context,R.layout.booked_layout,bookingList);
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewBooked = inflater.inflate(R.layout.booked_layout,null,false);

//        View listViewBooked = inflater.inflate(R.layout)
        TextView bookingId = (TextView)listViewBooked.findViewById(R.id.bookingId);
        TextView sportName = (TextView) listViewBooked.findViewById(R.id.sportName);
        TextView sportVenue = (TextView) listViewBooked.findViewById(R.id.sportVenue);
        TextView sportDate = (TextView)listViewBooked.findViewById(R.id.sportDate);
        TextView sportTime = (TextView)listViewBooked.findViewById(R.id.sportTime);

        final Booking booking = bookingList.get(position);
        LinearLayout l=(LinearLayout)listViewBooked.findViewById(R.id.list_click);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,booking.getBookingId(),Toast.LENGTH_SHORT).show();
            }
        });
        qrGenerator = (Button)listViewBooked.findViewById(R.id.qrGenerator);

        bookingId.setText("BId:" + booking.getBookingId());
        sportName.setText("Sport:"  +  booking.getSport());
        sportVenue.setText("Venue:" + booking.getVenue());
        sportDate.setText("Date:" + booking.getBooking_date());
        sportTime.setText("Time:" + booking.getBooking_time());


        qrGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookId ="bookId";
                Intent intent = new Intent(getContext(),QrClass.class);
                intent.putExtra(bookId,booking.getBookingId());
                context.startActivity(intent);
            }
        });

        return listViewBooked;
    }


}
