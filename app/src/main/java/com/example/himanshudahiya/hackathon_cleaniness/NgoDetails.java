package com.example.himanshudahiya.hackathon_cleaniness;

/**
 * Created by frien_000 on 3/24/2018.
 */

public class NgoDetails {
    private String pk ;
    private String ngo_name ;
    private String latitude ;
    private String longitude ;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getNgo_name() {
        return ngo_name;
    }

    public void setNgo_name(String ngo_name) {
        this.ngo_name = ngo_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public NgoDetails(String pk, String ngo_name, String latitude, String longitude) {
        this.pk = pk;
        this.ngo_name = ngo_name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
