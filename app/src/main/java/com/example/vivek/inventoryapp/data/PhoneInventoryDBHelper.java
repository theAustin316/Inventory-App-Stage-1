package com.example.vivek.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database helper for Inventory app. Manages database creation and version management.
 */
public class PhoneInventoryDBHelper extends SQLiteOpenHelper {

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "devices_inventory.db";

    /**
     * Constructs a new instance of {@link PhoneInventoryDBHelper}.
     *
     * @param context of the app
     */
    public PhoneInventoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_DEVICES_TABLE = "CREATE TABLE " + PhoneInventoryContract.DeviceEntry.TABLE_NAME + " ("
                + PhoneInventoryContract.DeviceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_NAME + " TEXT NOT NULL, "
                + PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_COST + " INTEGER NOT NULL, "
                + PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_QUANTITY + " INTEGER NOT NULL, "
                + PhoneInventoryContract.DeviceEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + PhoneInventoryContract.DeviceEntry.COLUMN_SUPPLIER_PHONE_NO + " TEXT NOT NULL );";
        sqLiteDatabase.execSQL(SQL_CREATE_DEVICES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
