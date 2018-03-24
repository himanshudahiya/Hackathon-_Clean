package com.example.himanshudahiya.hackathon_cleaniness;

/**
 * Created by frien_000 on 3/24/2018.
 */

public class LikedByTable {
    private String pk ;
    private String msgPk ;
    private String userPk ;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getMsgPk() {
        return msgPk;
    }

    public void setMsgPk(String msgPk) {
        this.msgPk = msgPk;
    }

    public String getUserPk() {
        return userPk;
    }

    public void setUserPk(String userPk) {
        this.userPk = userPk;
    }

    public LikedByTable(String pk, String msgPk, String userPk) {
        this.pk = pk;
        this.msgPk = msgPk;
        this.userPk = userPk;
    }
}
