package com.example.himanshudahiya.hackathon_cleaniness;



public class EventPk {
    private String pk ;
    private String eventName ;
    private String timestamp ;
    private String ngo_pk ;
    private String perks ;
    private String place ;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNgo_pk() {
        return ngo_pk;
    }

    public void setNgo_pk(String ngo_pk) {
        this.ngo_pk = ngo_pk;
    }

    public String getPerks() {
        return perks;
    }

    public void setPerks(String perks) {
        this.perks = perks;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public EventPk(String pk, String eventName, String timestamp, String ngo_pk, String perks, String place) {
        this.pk = pk;
        this.eventName = eventName;
        this.timestamp = timestamp;
        this.ngo_pk = ngo_pk;
        this.perks = perks;
        this.place = place;
    }
}
