package com.example.vinvin.memoapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinvin on 12.12.2017.
 */

public class DataBaseSource {

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private String[] allColumns = {DataBaseHelper.COLUMN_ID, DataBaseHelper.COLUMN_MEMO,
            DataBaseHelper.COLUMN_DATE, DataBaseHelper.COLUMN_PRIORITY};

    public DataBaseSource(Context context) {
        helper = new DataBaseHelper(context);
    }

    public void open() throws SQLException {
        db = helper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public MemoObject createMemo(String memo, String date, String priority) {

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.COLUMN_MEMO, memo);
        values.put(DataBaseHelper.COLUMN_DATE, date);
        values.put(DataBaseHelper.COLUMN_PRIORITY, priority);

        // makes database entry
        long insertId = db.insert(DataBaseHelper.TABLE_MEMO, null, values);

        Cursor cursor = db.query(DataBaseHelper.TABLE_MEMO, allColumns,
                DataBaseHelper.COLUMN_ID + " = " + insertId,
                null, null, null, null);
        cursor.moveToFirst();
        MemoObject newObject = cursorToMemo(cursor);
        cursor.close();

        return newObject;
    }

    public List<MemoObject> getAllComments() {
        List<MemoObject> objectList = new ArrayList<MemoObject>();

        Cursor cursor = db.query(DataBaseHelper.TABLE_MEMO,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MemoObject object = cursorToMemo(cursor);
            objectList.add(object);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return objectList;
    }

    public void deleteMemo(MemoObject object) {
        long id = object.getId();
        System.out.println("Comment deleted with id: " + id);
        db.delete(DataBaseHelper.TABLE_MEMO, DataBaseHelper.COLUMN_ID
                + " = " + id, null);
    }

    public void resetDatabase(){
        helper.onUpgrade(db, 1, 1);
    }

    private MemoObject cursorToMemo(Cursor cursor) {

        MemoObject object = new MemoObject();
        object.setId(cursor.getLong(0));
        object.setMemo(cursor.getString(1));
        object.setDate(cursor.getString(2));
        object.setPriority(cursor.getString(3));

        return object;
    }
}
