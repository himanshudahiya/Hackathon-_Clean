package com.example.himanshudahiya.hackathon_cleaniness;

/**
 * Created by frien_000 on 3/24/2018.
 */

public class LookupModel {
    private String pk ;
    private String reviewMsg ;
    private String userPk ;
    private String areaPk ;
    private String rating ;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getReviewMsg() {
        return reviewMsg;
    }

    public void setReviewMsg(String reviewMsg) {
        this.reviewMsg = reviewMsg;
    }

    public String getUserPk() {
        return userPk;
    }

    public void setUserPk(String userPk) {
        this.userPk = userPk;
    }

    public String getAreaPk() {
        return areaPk;
    }

    public void setAreaPk(String areaPk) {
        this.areaPk = areaPk;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public LookupModel(String pk, String reviewMsg, String userPk, String areaPk, String rating) {
        this.pk = pk;
        this.reviewMsg = reviewMsg;
        this.userPk = userPk;
        this.areaPk = areaPk;
        this.rating = rating;
    }
}
