package com.example.vivek.inventoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.vivek.inventoryapp.data.PhoneInventoryContract;
import com.example.vivek.inventoryapp.data.PhoneInventoryDBHelper;

/**
 * Displays list of devices that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private PhoneInventoryDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        FloatingActionButton fab = findViewById(R.id.fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        dbHelper = new PhoneInventoryDBHelper(this);
        displayDatabaseInfo();
    }

    private Cursor queryData() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM devices"
        // to get a Cursor that contains all rows from the device table.
        String[] projection = {
                PhoneInventoryContract.DeviceEntry._ID,
                PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_NAME,
                PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_COST,
                PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_QUANTITY,
                PhoneInventoryContract.DeviceEntry.COLUMN_SUPPLIER_NAME,
                PhoneInventoryContract.DeviceEntry.COLUMN_SUPPLIER_PHONE_NO,
        };

        // perform  a query on devices table
        Cursor cursor;
        cursor = db.query(PhoneInventoryContract.DeviceEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }


    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the devices database.
     */
    private void displayDatabaseInfo() {

        Cursor cursor = queryData();

        TextView displayView = findViewById(R.id.information_textView);
        displayView.setText("The device inventory conatins");
        displayView.append(" " + cursor.getCount() + " ");
        displayView.append("devices" + "\n\n");

        int idColumnIndex = cursor.getColumnIndex(PhoneInventoryContract.DeviceEntry._ID);
        int deviceNameColumnIndex = cursor.getColumnIndex(PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_NAME);
        int deviceCostColumnIndex = cursor.getColumnIndex(PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_COST);
        int deviceQuantityColumnIndex = cursor.getColumnIndex(PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_QUANTITY);
        int supplierNameColumnIndex = cursor.getColumnIndex(PhoneInventoryContract.DeviceEntry.COLUMN_SUPPLIER_NAME);
        int supplierContactColumnIndex = cursor.getColumnIndex(PhoneInventoryContract.DeviceEntry.COLUMN_SUPPLIER_PHONE_NO);

        try {
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentDeviceName = cursor.getString(deviceNameColumnIndex);
                int currentDeviceCost = cursor.getInt(deviceCostColumnIndex);
                int currentDeviceQuantity = cursor.getInt(deviceQuantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierContact = cursor.getString(supplierContactColumnIndex);

                displayView.append("\n" + PhoneInventoryContract.DeviceEntry._ID + "  : " + currentID + "\n" +
                        PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_NAME + "  : " + currentDeviceName + "\n" +
                        PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_COST + "  : " + currentDeviceCost + "\n" +
                        PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_QUANTITY + "  : " + currentDeviceQuantity + "\n" +
                        PhoneInventoryContract.DeviceEntry.COLUMN_SUPPLIER_NAME + "  : " + currentSupplierName + "\n" +
                        PhoneInventoryContract.DeviceEntry.COLUMN_SUPPLIER_PHONE_NO + "  : " + currentSupplierContact + "\n");
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
