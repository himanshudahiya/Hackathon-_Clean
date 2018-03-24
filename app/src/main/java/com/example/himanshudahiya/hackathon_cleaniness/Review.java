package com.example.himanshudahiya.hackathon_cleaniness;

/**
 * Created by sachin on 24/03/18.
 */

public class Review {
    String areaPk ;
    String userPk ;
    String reviewPK ;
    String Review;
    boolean liked;
    int no_of_likes;

    int get_no_of_likes(){
        if(liked)
            return no_of_likes+1;
        else
            return no_of_likes;
    }
}
