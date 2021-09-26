package com.example.myapplication.database;

import android.provider.BaseColumns;

public final class UsersMaster {
    private UsersMaster() {}

    public static  class  Users implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_CARDNUMBER ="cardnumber";
        public static final String COLUMN_NAME_OWNERNAME ="ownername";
        public static final String COLUMN_NAME_EXPIREDATE ="expiredate";
        public static final String COLUMN_NAME_POSTCORD ="postcord";
    }
}
