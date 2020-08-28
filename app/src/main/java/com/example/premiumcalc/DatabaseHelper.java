package com.example.premiumcalc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "caldb";
    public static final String TABLE_NAME ="recentcals";

    public static final String COL_0 ="id";
    public static final String COL_1 ="name";
    public static final String COL_2 ="natdeath";
    public static final String COL_3 ="accdeath";
    public static final String COL_4 ="illdeath";
    public static final String COL_5 ="hospay";
    public static final String COL_6 ="monthly";
    public static final String COL_7 ="annual";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,natdeath TEXT, accdeath TEXT, illdeath TEXT, hospay TEXT, monthly TEXT,annual TEXT )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name, String nat, String acc, String crit, String hos, String month, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_1,name);
        contentvalues.put(COL_2,nat);
        contentvalues.put(COL_3,acc);
        contentvalues.put(COL_4,crit);
        contentvalues.put(COL_5,hos);
        contentvalues.put(COL_6,month);
        contentvalues.put(COL_7,year);

        long result =  db.insert(TABLE_NAME, null,contentvalues);
        if(result == -1 ){
            return false;
        }
        else
        {
            return true;
        }


    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor res  = db.rawQuery("select * from "+TABLE_NAME,null );

        return db.rawQuery("SELECT * FROM  recentcals ORDER BY id DESC LIMIT 5",null );
    }
}
