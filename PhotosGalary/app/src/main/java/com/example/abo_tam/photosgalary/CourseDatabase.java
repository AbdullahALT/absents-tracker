package com.example.abo_tam.photosgalary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abo_tam on 8/19/2015.
 */
public class CourseDatabase extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "database.db";
    private final static String TABLE_NAME = "Course";
    private final static String COURSE_ID = "ID";
    private final static String COURSE_NAME = "Name";
    private final static String COURSE_ABSENTS = "Absents";


    public CourseDatabase(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO: change TEXT to Char(7)
        db.execSQL("create table " + TABLE_NAME + " (" +
                COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COURSE_NAME + " TEXT," +
                COURSE_ABSENTS + " INTEGER" +
                " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public boolean insertCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_NAME, course.getName());
        contentValues.put(COURSE_ABSENTS, course.getAbsents());
        if (db.insert(TABLE_NAME, null, contentValues) == -1)
            return false;
        else
            return true;
    }

    public void removeCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Course WHERE ID = " + course.getId());
    }

    public void increaseAbsents(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE Course SET Absents = Absents + 1 WHERE ID = " + course.getId());
    }

    public void decreaseAbsents(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE Course SET Absents = Absents - 1 WHERE ID = " + course.getId());
    }


}
