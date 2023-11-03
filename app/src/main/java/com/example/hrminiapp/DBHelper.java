package com.example.hrminiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    static String DBNAME = "Company.db";
    static int VERSION = 1;
    static String TABLE_NAME = "Employee";
    static String COL1 = "id";
    static String COL2 = "name";
    static String COL3 = "designation";
    static String COL4 = "department";
    static String COL5 = "emailid";
    static String COL6 = "salary";

    static final String CREATE_TABLE = "create table " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT NOT NULL, "
            + COL3 + " TEXT, "  + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " INTEGER); ";
    static final String DROP_TABLE="DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(Context context){

        super(context, DBNAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public boolean InsertEmployee(Employee objEmp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, objEmp.getName());
        cv.put(COL3, objEmp.getDesig());
        cv.put(COL4, objEmp.getDept());
        cv.put(COL5, objEmp.getEmailId());
        cv.put(COL6, objEmp.getSalary());
        long result = db.insert(TABLE_NAME, null,cv);
        return ((result==-1)? false : true);
    }

    public Integer DeleteEmployee(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id="+id, null);
    }

    public Cursor readEmployees()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorObj;
        cursorObj = db.rawQuery("select * from " + TABLE_NAME, null);
        if(cursorObj != null){
            cursorObj.moveToFirst();
        }
        return cursorObj;
    }
}
