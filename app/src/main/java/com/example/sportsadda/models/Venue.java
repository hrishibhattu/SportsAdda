package com.example.sportsadda.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Venue {

    public String userId;
    public String venueId;
    public String vName;
    public String vSport;
    public String vLocation;
    public String vCost;
    public String vLatitute;
    public String vLongitude;

    public Venue(){

    }

    public Venue(String userId,String venueId,String vName, String vSport, String vLocation, String vCost,String vLatitute,String vLongitude) {
        this.venueId = venueId;
        this.vName = vName;
        this.vSport = vSport;
        this.vLocation = vLocation;
        this.vCost = vCost;
        this.userId = userId;
        this.vLatitute = vLatitute;
        this.vLongitude = vLongitude;
    }

    public String getvName() {
        return vName;
    }

    public String getvSport() {
        return vSport;
    }

    public String getvLocation() {
        return vLocation;
    }

    public String getvCost() {
        return vCost;
    }

    public String getVenueId(){ return venueId;}

    public String getUserId(){return  userId;}

    public String getvLatitute(){return vLatitute;}

    public String getvLongitude(){return vLongitude;}
}
