package com.example.vinvin.memoapp;


import android.content.Context;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vinvin on 27.11.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MemoDataBase.dp";

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dp) {
        dp.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dp, int newBase, int oldBase){
        dp.execSQL("");
        onCreate(dp);
    }
}
