package com.example.sportsadda.models;

public class Booking {
    public String bookingId;
    public String venue;
    public String sport;
    public String booking_date;
    public String booking_time;

    public Booking(){

    }


    public Booking(String bookingId,String venue, String sport,String booking_date,String booking_time) {
        this.bookingId = bookingId;
        this.venue = venue;
        this.sport = sport;
        this.booking_date = booking_date;
        this.booking_time = booking_time;
    }

    public String getVenue() {
        return venue;
    }

    public String getSport() {
        return sport;
    }

    public String getBookingId(){
        return bookingId;
    }

    public String getBooking_date(){return booking_date;}

    public String getBooking_time() {return booking_time;}
}
