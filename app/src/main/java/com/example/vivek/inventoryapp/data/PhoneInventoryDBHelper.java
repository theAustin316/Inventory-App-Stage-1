package com.example.vivek.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhoneInventoryDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "devices_inventory.db";

    public PhoneInventoryDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + PhoneInventoryContract.DeviceEntry.TABLE_NAME + " ("
                + PhoneInventoryContract.DeviceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_NAME + " TEXT NOT NULL, "
                + PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_COST + " INTEGER NOT NULL, "
                + PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_QUANTITY + " INTEGER NOT NULL, "
                + PhoneInventoryContract.DeviceEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + PhoneInventoryContract.DeviceEntry.COLUMN_SUPPLIER_PHONE_NO + " TEXT NOT NULL );";
        sqLiteDatabase.execSQL(SQL_CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
