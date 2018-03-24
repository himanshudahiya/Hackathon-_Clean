package com.example.himanshudahiya.hackathon_cleaniness;


public class DetailsUser {
    private String id ;
    private String name ;
    private int mobile ;
    private String email ;
    private String location ;
    private boolean isCleaner ;

    public DetailsUser(String id,String name, int mobile, String email, String location, boolean Cleaner) {
        this.id = id ;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.location = location;
        this.isCleaner = Cleaner ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
