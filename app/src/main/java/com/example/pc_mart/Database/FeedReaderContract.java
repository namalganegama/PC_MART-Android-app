package com.example.pc_mart.Database;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_1 = "name";
        public static final String COLUMN_2 = "email";
        public static final String COLUMN_3 = "phone";
        public static final String COLUMN_4 = "username";
        public static final String COLUMN_5 = "password";

    }
}
