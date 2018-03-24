
package com.example.himanshudahiya.hackathon_cleaniness;



/**
 * Created by frien_000 on 3/24/2018.
 */

public class AreaModel {
    private String pk ;
    private String longitude ;
    private String latitude ;

    public AreaModel(String pk, String longitude, String latitude) {
        this.pk = pk;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}

