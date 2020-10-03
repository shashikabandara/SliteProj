package com.example.sliteproj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME);
        onCreate(db);
    }

    // insert information
    public long insertInfo(String image, String name, String date, String time, String type, String notes, String cnumber, String cvc, String edate,String amount,String paid, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_NAME, name);
        values.put(Constants.C_DATE, date);
        values.put(Constants.C_TIME, time);
        values.put(Constants.C_IMAGE, image);
        values.put(Constants.C_TYPE, type);
        values.put(Constants.C_NOTES, notes);
        values.put(Constants.C_CNUMBER, cnumber);
        values.put(Constants.C_CVC, cvc);
        values.put(Constants.C_EDATE, edate);
        values.put(Constants.C_AMOUNT, amount);
        values.put(Constants.C_PAID, paid);
        values.put(Constants.C_Add_TIMESTAMP, addTimeStamp);
        values.put(Constants.C_UPDATED_TIMESTAMP, updateTimeStamp);

        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    // update information
    public void updateInfo(String id, String image, String name, String date, String time, String type, String notes, String cnumber, String cvc, String edate,String amount,String paid, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_NAME, name);
        values.put(Constants.C_DATE, date);
        values.put(Constants.C_TIME, time);
        values.put(Constants.C_IMAGE, image);
        values.put(Constants.C_TYPE, type);
        values.put(Constants.C_NOTES, notes);
        values.put(Constants.C_CNUMBER, cnumber);
        values.put(Constants.C_CVC, cvc);
        values.put(Constants.C_EDATE, edate);
        values.put(Constants.C_AMOUNT, amount);
        values.put(Constants.C_PAID, paid);
        values.put(Constants.C_Add_TIMESTAMP, addTimeStamp);
        values.put(Constants.C_UPDATED_TIMESTAMP, updateTimeStamp);

        db.update(Constants.TABLE_NAME, values, Constants.C_ID + " = ?", new String[]{id});
        db.close();
    }

    // delete information
    public void deleteInfo(String id) {

        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.C_ID + " = ?", new String[]{id});
        db.close();
    }

    public ArrayList<Model> getAllData(String orderBy) {

        ArrayList<Model> arrayList = new ArrayList<>();
        // query for select info
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()) {
            do {
                Model Model = new Model(
                ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_IMAGE)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAME)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_DATE)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_TIME)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_TYPE)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_NOTES)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_CNUMBER)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_CVC)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_EDATE)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_AMOUNT)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_PAID)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_Add_TIMESTAMP)),
                ""+cursor.getString(cursor.getColumnIndex(Constants.C_UPDATED_TIMESTAMP))

                );
                arrayList.add(Model);
            }while (cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }

    /*public int getCounts() {
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        return count;
    }*/
}
