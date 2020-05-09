package com.demo.sqlitedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final int DATABASE_VERSION = 1;

    public static final String STUDENT_TABLE = "student_table";

    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_AGE = "age";
    public static final String COL_ADDRESS = "address";

    public static final String STUDENT_TABLE_CREATE = "create table " + STUDENT_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COL_AGE + " INTEGER NOT NULL, " + COL_ADDRESS + " TEXT);";
    public static final String DROP_STUDENT_TABLE = "drop table if exists " + STUDENT_TABLE;

    public StudentDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(STUDENT_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_STUDENT_TABLE);
        this.onCreate(db);
    }
}
