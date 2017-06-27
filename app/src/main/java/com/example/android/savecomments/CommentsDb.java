package com.example.android.savecomments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apoorva on 6/27/2017.
 */

public class CommentsDb {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] cols = {
            MySQLiteHelper.COLUMN_COMMENT,
            MySQLiteHelper.COLUMN_ID
    };

    public CommentsDb(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void getdb() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    public Comment createComment(String comment){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        long insertID= database.insert(MySQLiteHelper.TABLE_NAME, null, values);
        Cursor cursor = database.query(
                MySQLiteHelper.TABLE_NAME,
                cols,
                MySQLiteHelper.COLUMN_ID + " = "+ insertID,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        Comment makeComment = cursorToComment(cursor);
        cursor.close();
        return  makeComment;
    }
    public void deleteComment(Comment comment){
        long id = comment.getId();
        Log.v(" CommentsDb.java "," Comment with id: "+ id + " deleted");
        database.delete(MySQLiteHelper.TABLE_NAME,
                MySQLiteHelper.COLUMN_ID + " = " + id, null);  }

    public List<Comment> getAllComments(){
        List<Comment> comments = new ArrayList<Comment>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
                cols,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
    cursor.close();
    return comments;}
    private Comment cursorToComment(Cursor cursor){
        Comment comment = new Comment();
        comment.storeId(cursor.getLong(0));
        comment.storeComment(cursor.getString(1));
    return comment;}
}
