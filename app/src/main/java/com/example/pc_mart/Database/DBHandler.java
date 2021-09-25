package com.example.pc_mart.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.Users.TABLE_NAME + " (" +
                    FeedReaderContract.Users._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.Users.COLUMN_1 + " TEXT," +
                    FeedReaderContract.Users.COLUMN_2 + " TEXT," +
                    FeedReaderContract.Users.COLUMN_3 + " TEXT," +
                    FeedReaderContract.Users.COLUMN_4 + " TEXT," +
                    FeedReaderContract.Users.COLUMN_5 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.Users.TABLE_NAME;

// Add User Method
    public long addInfo (String name, String email, String phone, String username, String password){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.Users.COLUMN_1, name);
        values.put(FeedReaderContract.Users.COLUMN_2, email);
        values.put(FeedReaderContract.Users.COLUMN_3, phone);
        values.put(FeedReaderContract.Users.COLUMN_4, username);
        values.put(FeedReaderContract.Users.COLUMN_5, password);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedReaderContract.Users.TABLE_NAME, null, values);

        return newRowId;
    }
// Update Method
    public Boolean updateInfo (String name, String email, String phone, String username, String password){
        SQLiteDatabase db = getWritableDatabase();

        // New value for one column

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.Users.COLUMN_1, name);
        values.put(FeedReaderContract.Users.COLUMN_2, email);
        values.put(FeedReaderContract.Users.COLUMN_3, phone);
        values.put(FeedReaderContract.Users.COLUMN_4, username);
        values.put(FeedReaderContract.Users.COLUMN_5, password);

        // Which row to update, based on the title
        String selection = FeedReaderContract.Users.COLUMN_4 + " LIKE ?";
        String[] selectionArgs = { username };

        int count = db.update(
                FeedReaderContract.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count >= 1){
            return true;
        }
        else {
            return false;
        }
    }
// Delete Method
    public void deleteInfo (String username) {

        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = FeedReaderContract.Users.COLUMN_4 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { username };
        // Issue SQL statement.
        int deletedRows = db.delete(FeedReaderContract.Users.TABLE_NAME, selection, selectionArgs);
    }

    public List readAllInfo () {
        String username = "Nishan";
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.Users.COLUMN_1,
                FeedReaderContract.Users.COLUMN_2,
                FeedReaderContract.Users.COLUMN_3,
                FeedReaderContract.Users.COLUMN_4,
                FeedReaderContract.Users.COLUMN_5
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.Users.COLUMN_4 + " = ?";
        String[] selectionArgs = { username };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.Users.COLUMN_4 + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List usernames = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.Users.COLUMN_4));
            usernames.add(user);
        }
        cursor.close();
        return usernames;

    }
    public List readAllInfo (String username) {

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.Users.COLUMN_1,
                FeedReaderContract.Users.COLUMN_2,
                FeedReaderContract.Users.COLUMN_3,
                FeedReaderContract.Users.COLUMN_4,
                FeedReaderContract.Users.COLUMN_5
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.Users.COLUMN_4 + " LIKE ?";
        String[] selectionArgs = { username };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.Users.COLUMN_4 + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List userInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.Users.COLUMN_4));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.Users.COLUMN_1));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.Users.COLUMN_2));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.Users.COLUMN_3));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.Users.COLUMN_5));

            userInfo.add(name);//0
            userInfo.add(email);//1
            userInfo.add(phone);//2
            userInfo.add(user);//3
            userInfo.add(pass);//4
        }
        cursor.close();
        return userInfo;

    }
}
