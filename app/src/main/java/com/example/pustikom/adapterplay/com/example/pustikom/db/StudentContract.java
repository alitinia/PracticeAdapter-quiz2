package com.example.pustikom.adapterplay.com.example.pustikom.db;

import android.provider.BaseColumns;

import com.example.pustikom.adapterplay.com.example.pustikom.user.Student;

/**
 * Created by Alitinia on 11/12/2016.
 */



public final class StudentContract {

    private StudentContract(){

    }

    public static final class StudentTable implements BaseColumns{
        public final static String TABLE_NAME = "StudentTable";
        public final static String COLUMN_ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "Name";
        public final static String COLUMN_NIM = "NIM";
        public final static String COLUMN_PHONE = "Phone";
        public final static String COLUMN_MAIL = "Mail";
        public final static String COLUMN_GENDER = "Gender";

        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
    }


}
