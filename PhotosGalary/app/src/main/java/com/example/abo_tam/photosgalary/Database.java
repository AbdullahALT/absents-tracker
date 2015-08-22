package com.example.abo_tam.photosgalary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abo_tam on 8/19/2015.
 */
public class Database extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "database.db";
    private final static String TABLE_NAME = "Course";
    private final static String COURCE_ID = "ID";
    private final static String COURSE_NAME = "Name";
    private final static String COURCE_ABSENTS = "Absents";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO: change TEXT to Char(7)
        db.execSQL("create table " + TABLE_NAME + " (" +
                COURCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COURSE_NAME + " TEXT," +
                COURCE_ABSENTS + " INTEGER" +
                " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertCource(Cource cource) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_NAME, cource.getName());
        contentValues.put(COURCE_ABSENTS, cource.getAbsents());
        if (db.insert(TABLE_NAME, null, contentValues) == -1)
            return false;
        else
            return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public void execute(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

}
