package com.example.android.savecomments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Apoorva on 6/27/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Comments.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "COMMENTS";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "COLUMN";
    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_DB = "CREATE TABLE "
            + TABLE_NAME + "( " +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COMMENT + " TEXT NOT NULL);";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),"UPGRADING DATABASE TO VERSION "+ newVersion);
        db.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
