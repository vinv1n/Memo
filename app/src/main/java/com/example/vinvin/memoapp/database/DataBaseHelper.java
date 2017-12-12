package com.example.vinvin.memoapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * Created by vinvin on 12.12.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String TABLE_MEMO = "memos";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MEMO = "memo";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PRIORITY = "priority";

    private static final String DATABASE_NAME = "memo.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table " + TABLE_MEMO +
                "( " + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_MEMO
                + " text not null, " + COLUMN_DATE + " text not null, " + COLUMN_PRIORITY
                + " text not null);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMO);
        onCreate(sqLiteDatabase);
    }
}
