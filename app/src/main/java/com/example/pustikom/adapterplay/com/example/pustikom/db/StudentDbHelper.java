package com.example.pustikom.adapterplay.com.example.pustikom.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.pustikom.adapterplay.com.example.pustikom.db.StudentContract.StudentTable;
import com.example.pustikom.adapterplay.com.example.pustikom.user.Student;
import com.example.pustikom.adapterplay.com.example.pustikom.user.StudentList;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alitinia on 11/12/2016.
 */

public class StudentDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG=StudentDbHelper.class.getSimpleName();
    private static final String DB_NAME = "student.db";
    private static final int DB_VERSION = 1;

    public StudentDbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + StudentTable.TABLE_NAME + " ("
                + StudentTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StudentTable.COLUMN_NIM + " TEXT, "
                + StudentTable.COLUMN_NAME + " TEXT NOT NULL, "
                + StudentTable.COLUMN_GENDER + " INTEGER NOT NULL, "
                + StudentTable.COLUMN_MAIL + " TEXT, "
                + StudentTable.COLUMN_PHONE + " TEXT )";
        db.execSQL(CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        //empty
    }

    public void insertStudent(SQLiteDatabase db, Student student){
        ContentValues values = new ContentValues();
        values.put(StudentTable.COLUMN_NIM, student.getNoreg());
        values.put(StudentTable.COLUMN_NAME, student.getName());
        values.put(StudentTable.COLUMN_GENDER, student.getGender());
        values.put(StudentTable.COLUMN_MAIL, student.getMail());
        values.put(StudentTable.COLUMN_PHONE, student.getPhone());
        db.insert(StudentTable.TABLE_NAME,null,values);
    }

    public void updateStudent(SQLiteDatabase db, Student student){
        ContentValues values = new ContentValues();
        values.put(StudentTable.COLUMN_NIM, student.getNoreg());
        values.put(StudentTable.COLUMN_NAME, student.getName());
        values.put(StudentTable.COLUMN_GENDER, student.getGender());
        values.put(StudentTable.COLUMN_MAIL, student.getMail());
        values.put(StudentTable.COLUMN_PHONE, student.getPhone());
        String condition = StudentTable._ID + "= ?";
        String[] conditionArgs = {student.getId()+""};
        db.update(StudentTable.TABLE_NAME,values,condition,conditionArgs);
    }

    public void deleteStudent(SQLiteDatabase db, Student student) {
        ContentValues values = new ContentValues();
        values.put(StudentTable.COLUMN_NIM, student.getNoreg());
        values.put(StudentTable.COLUMN_NAME, student.getName());
        values.put(StudentTable.COLUMN_GENDER, student.getGender());
        values.put(StudentTable.COLUMN_MAIL, student.getMail());
        values.put(StudentTable.COLUMN_PHONE, student.getPhone());
        db.delete(StudentTable.TABLE_NAME, student.getId()+ " = ?",
                new String[] { String.valueOf(student.getId()) });
        db.close();
    }

    public void truncateStudent(SQLiteDatabase db, Student student){
        ContentValues values = new ContentValues();
        values.put(StudentTable.COLUMN_NIM, student.getNoreg());
        values.put(StudentTable.COLUMN_NAME, student.getName());
        values.put(StudentTable.COLUMN_GENDER, student.getGender());
        values.put(StudentTable.COLUMN_MAIL, student.getMail());
        values.put(StudentTable.COLUMN_PHONE, student.getPhone());
        String sql = "DELETE FROM " + StudentTable.TABLE_NAME + ";VACUUM;";
        db.execSQL(sql);
    }

    public StudentList fetchData(SQLiteDatabase db) {
        String[] projection = {
                StudentTable._ID,
                StudentTable.COLUMN_NIM,
                StudentTable.COLUMN_NAME,
                StudentTable.COLUMN_GENDER,
                StudentTable.COLUMN_MAIL,
                StudentTable.COLUMN_PHONE};

        Cursor cursor = db.query(
                StudentTable.TABLE_NAME,    // The table to query
                projection,                 // The columns to return
                null,                       // The columns for the WHERE clause
                null,                       // The values for the WHERE clause
                null,                       // Don't group the rows
                null,                       // Don't filter by row groups
                null);                      // The sort order

        // Figure out the index of each column
        int idIndex = cursor.getColumnIndex(StudentTable._ID);
        int nimIndex = cursor.getColumnIndex(StudentTable.COLUMN_NIM);
        int nameIndex = cursor.getColumnIndex(StudentTable.COLUMN_NAME);
        int genderIndex = cursor.getColumnIndex(StudentTable.COLUMN_GENDER);
        int phoneIndex = cursor.getColumnIndex(StudentTable.COLUMN_PHONE);
        int mailIndex = cursor.getColumnIndex(StudentTable.COLUMN_MAIL);

        StudentList studentList = new StudentList();
        while (cursor.moveToNext()) {
            int currentID = cursor.getInt(idIndex);
            String currentNim = cursor.getString(nimIndex);
            String currentName = cursor.getString(nameIndex);
            int currentGender = cursor.getInt(genderIndex);
            String currentMail = cursor.getString(mailIndex);
            String currentPhone = cursor.getString(phoneIndex);

            Student student = new Student((int, toString()) currentID, currentNim, currentName, currentGender, currentMail, currentPhone);

            studentList.add(student);
        }

        cursor.close();
        return studentList;
    }

}
