package com.pcmart.deliverytest;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;


        import androidx.annotation.Nullable;

        import java.util.ArrayList;
        import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "delivery";
    private static final String TABLE_NAME = "delivery";

    //column names
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE_NO = "phone_no";
    private static final String Email = "email";
    private static final String ADDRESS = "address";
    private static final String ZIP_CODE = "zip_code";
    private static final String QUENTITY ="quentity";



    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = " CREATE TABLE " +TABLE_NAME+ " "+
                " ( "
                +ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NAME+ " TEXT, "
                +PHONE_NO+ " INTEGER, "
                +Email+ " TEXT, "
                +ADDRESS+ " TEXT, "
                +ZIP_CODE+ " INTEGER, "
                +QUENTITY+ " INTEGER " +
                " ) ";

         /*   CREATE TABLE delivery (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_no TEXT,
                email TEXT, address TEXT, country TEXT, zip_code TEXT );*/

        db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        //Drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        //Create table again
        onCreate(db);
    }

    //add single delivery
    public void addDelivery(Delivery_Model delivery){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,delivery.getName());
        contentValues.put(PHONE_NO,delivery.getPhone());
        contentValues.put(Email,delivery.getEmail());
        contentValues.put(ADDRESS,delivery.getAddress());
        contentValues.put(ZIP_CODE,delivery.getZipcode());
        contentValues.put(QUENTITY,delivery.getQuentity());


        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        //close database
        sqLiteDatabase.close();

    }

    //get delivery count
    public int countDelivery(){
        SQLiteDatabase db = getReadableDatabase();
        String query = " SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    //get all deliveries into a list
    public List<Delivery_Model> getAllDeliveries(){

        List<Delivery_Model> deliveries = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                //create new delinerylist object
                Delivery_Model deliverylist = new Delivery_Model();
                //pass values to deliverylist object
                deliverylist.setId(cursor.getInt(0));
                deliverylist.setName(cursor.getString(1));
                deliverylist.setPhone(cursor.getInt(2));
                deliverylist.setEmail(cursor.getString(3));
                deliverylist.setAddress(cursor.getString(4));
                deliverylist.setZipcode(cursor.getInt(5));
                deliverylist.setQuentity(cursor.getInt(6));


                //deliveries =[obj,objs,]
                deliveries.add(deliverylist);
            }while (cursor.moveToNext());
        }
        return deliveries;

    }

    //Delete item
    public void deleteDelivery(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =? ",new String[]{String.valueOf(id)});
        db.close();

    }

    //get a single delivery to update
    public Delivery_Model getSingleDelivery(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,NAME,PHONE_NO,Email,ADDRESS,ZIP_CODE,QUENTITY},
                ID + "= ?",new String[]{String.valueOf(id)},null,null,null);

        Delivery_Model delivery;
        if(cursor != null){
            cursor.moveToFirst();
            delivery = new Delivery_Model(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6)
            );
            return delivery;
        }
        return  null;
    }

    //update single delivery
    public int updateSingleDelivery(Delivery_Model delivery){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,delivery.getName());
        contentValues.put(PHONE_NO,delivery.getPhone());
        contentValues.put(Email,delivery.getEmail());
        contentValues.put(ADDRESS,delivery.getAddress());
        contentValues.put(ZIP_CODE,delivery.getZipcode());
        contentValues.put(QUENTITY,delivery.getQuentity());

        int status = db.update(TABLE_NAME,contentValues,ID +" =?",
                new String[]{String.valueOf(delivery.getId())});
        db.close();
        return status;
    }
}

