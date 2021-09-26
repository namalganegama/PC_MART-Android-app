package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME = "UserInfo.db";

    public DBHelper(Context context){super(context,DATABASE_NAME,null, 1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UsersMaster.Users.TABLE_NAME + " (" +
                        UsersMaster.Users._ID+ "INTEGER PRIMARY KEY," +
                        UsersMaster.Users.COLUMN_NAME_CARDNUMBER+"TEXT,"+
                        UsersMaster.Users.COLUMN_NAME_OWNERNAME+"TEXT,"+
                        UsersMaster.Users.COLUMN_NAME_EXPIREDATE+"TEXT,"+
                        UsersMaster.Users.COLUMN_NAME_POSTCORD+"TEXT)";

         db.execSQL(SQL_CREATE_ENTRIES);

    }

    public long addInfo(String cardnumber,String ownername,String expiredate,String postcord ){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(UsersMaster.Users.COLUMN_NAME_CARDNUMBER, cardnumber);
        values.put(UsersMaster.Users.COLUMN_NAME_OWNERNAME, ownername);
        values.put(UsersMaster.Users.COLUMN_NAME_EXPIREDATE,expiredate);
        values.put(UsersMaster.Users.COLUMN_NAME_POSTCORD,postcord);

        return db.insert(UsersMaster.Users.TABLE_NAME,null,values);


    }

    public List readAll(){
        SQLiteDatabase db = getReadableDatabase();

        String [] projection ={
                UsersMaster.Users._ID,
                UsersMaster.Users.COLUMN_NAME_CARDNUMBER,
                UsersMaster.Users.COLUMN_NAME_OWNERNAME,
                UsersMaster.Users.COLUMN_NAME_EXPIREDATE,
                UsersMaster.Users.COLUMN_NAME_POSTCORD
        };
        String sortorder =UsersMaster.Users._ID + "DESC";

        Cursor cursor =db.query(
                UsersMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortorder
        );
        List info =new ArrayList<>();
        while (cursor.moveToNext()){
            String cardnumber =cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_CARDNUMBER));
            String ownername =cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_OWNERNAME));
            String expiredate =cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_EXPIREDATE));
            String postcord =cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_POSTCORD));

            info.add(cardnumber+":"+ownername+":"+expiredate+":"+postcord);



        }
        cursor.close();

        return  info;
    }

    public  void  deleteInfo(String cardnumber){
        SQLiteDatabase db =getReadableDatabase();

        String selection =UsersMaster.Users.COLUMN_NAME_CARDNUMBER+"LIKE ?";
        String[] stringsARGS ={cardnumber};

        db.delete(UsersMaster.Users.TABLE_NAME,selection,stringsARGS);
    }

    public void  updateInfo(View view,String cardnumber, String ownername) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_CARDNUMBER, ownername);

        String selection = UsersMaster.Users.COLUMN_NAME_CARDNUMBER + "LIKE ?";
        String[] selectionArgs = {cardnumber};

        int count = db.update(
                UsersMaster.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
