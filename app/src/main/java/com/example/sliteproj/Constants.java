package com.example.sliteproj;

public class Constants {

    // db name
    public static final String DB_NAME = "PERSON2 _INFO_DB";
    // db version
    public static final int DB_VERSION = 1;
    // db table name
    public static final String TABLE_NAME = "PERSON2_INFO_TABLE";
    // columns of table
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_DATE = "DATE";
    public static final String C_TIME = "TIME";
    public static final String C_TYPE = "TYPE";
    public static final String C_NOTES = "NOTES";
    public static final String C_CNUMBER = "CNUMBER";
    public static final String C_CVC = "CVC";
    public static final String C_EDATE = "EDATE";
    public static final String C_AMOUNT = "AMOUNT";
    public static final String C_PAID = "PAID";
    public static final String C_IMAGE = "IMAGE";
    public static final String C_Add_TIMESTAMP = "TIMESTAMP";
    public static final String C_UPDATED_TIMESTAMP = "UPDATED_TIMESTAMP";
    // create table query
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NAME + " TEXT,"
            + C_DATE + " TEXT,"
            + C_TIME + " TEXT,"
            + C_IMAGE + " TEXT,"
            + C_TYPE + " TEXT,"
            + C_NOTES + " TEXT,"
            + C_CNUMBER + " TEXT,"
            + C_CVC + " TEXT,"
            + C_EDATE + " TEXT,"
            + C_AMOUNT + " TEXT,"
            + C_PAID + " TEXT,"
            + C_Add_TIMESTAMP + " TEXT,"
            + C_UPDATED_TIMESTAMP + " TEXT"
            + ");";

}
