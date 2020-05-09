package com.demo.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentDatabaseSource {

    private StudentDatabaseHelper studentDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Student student;

    public StudentDatabaseSource(Context context) {

        studentDatabaseHelper = new StudentDatabaseHelper(context);

    }

    public void openDatabase() {
        sqLiteDatabase = studentDatabaseHelper.getWritableDatabase();
    }

    private void closeDatabase() {
        studentDatabaseHelper.close();
    }


    public boolean addStudent(Student student) {

        this.openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentDatabaseHelper.COL_NAME, student.getName());
        contentValues.put(StudentDatabaseHelper.COL_AGE, student.getAge());
        contentValues.put(StudentDatabaseHelper.COL_ADDRESS, student.getAddress());
        Long insertRowId = sqLiteDatabase.insert(StudentDatabaseHelper.STUDENT_TABLE, null, contentValues);
        this.closeDatabase();

        if (insertRowId > 0) {
            return true;
        } else
            return false;

    }

    public List<Student> getAllStudent() {

        this.openDatabase();
        List<Student> studentList = new ArrayList<>();

        //Cursor cursor = sqLiteDatabase.rawQuery("select * from " + studentDatabaseHelper.STUDENT_TABLE, null);

        Cursor cursor = sqLiteDatabase.query(studentDatabaseHelper.STUDENT_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                String name = cursor.getString(cursor.getColumnIndex(studentDatabaseHelper.COL_NAME));
                int age = cursor.getInt(cursor.getColumnIndex(studentDatabaseHelper.COL_AGE));
                String address = cursor.getString(cursor.getColumnIndex(studentDatabaseHelper.COL_ADDRESS));
                int id = cursor.getInt(cursor.getColumnIndex(studentDatabaseHelper.COL_ID));

                Student student = new Student(id, name, age, address);
                studentList.add(student);

            } while (cursor.moveToNext());
        }
        this.closeDatabase();
        cursor.close();

        return studentList;

    }

    public boolean updateStudent(Student student) {

        this.openDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentDatabaseHelper.COL_NAME, student.getName());
        contentValues.put(StudentDatabaseHelper.COL_AGE, student.getAge());
        contentValues.put(StudentDatabaseHelper.COL_ADDRESS, student.getAddress());

        int updateRow = sqLiteDatabase.update(StudentDatabaseHelper.STUDENT_TABLE, contentValues, StudentDatabaseHelper.COL_ID + " =?", new String[]{String.valueOf(student.getId())});
        this.closeDatabase();

        if (updateRow > 0)
            return true;
        else
            return false;

    }

    public boolean deleteStudent(Student student){

        this.openDatabase();

        int deleteRow = sqLiteDatabase.delete(StudentDatabaseHelper.STUDENT_TABLE, StudentDatabaseHelper.COL_ID+" =?", new String[]{String.valueOf(student.getId())});

        this.closeDatabase();
        if (deleteRow>0){
            return true;
        }else
            return false;

    }
}
