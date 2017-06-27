package com.example.android.savecomments;

/**
 * Created by Apoorva on 6/27/2017.
 */

public class Comment {

    //This class Contains data we save in the db
    private long  id;
    private String comment;

    public long getId(){
        return  id;
    }
    public String getComment(){
        return comment;
    }
    public void storeId(long id){
        this.id = id;
    }
    public void storeComment(String comment){
        this.comment = comment;
    }

    @Override public String toString(){
        return comment;
    }
}
