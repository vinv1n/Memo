package com.example.vinvin.memoapp.database;

/**
 *
 * Created by vinvin on 12.12.2017.
 */

public class MemoObject {

    private long id;
    private String memo;
    private String date;
    private String priority;

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getPriority() {
        return priority;
    }

    public String getMemo() {
        return memo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    // might not be needed!
    public MemoObject getObject(){
        return this;
    }
}
