package com.example.vinvin.memoapp;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
    public ArrayList<String> getAllMemos(){
        ArrayList<String> Memos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("", null);
        res.moveToFirst();

        while (!res.isAfterLast()){
            /*
            Should be checked if this real works
             */
            Memos.add(res.getString(res.getColumnIndex("")));
            res.moveToNext();
        }
        return Memos;
    }

}
