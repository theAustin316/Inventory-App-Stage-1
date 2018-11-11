package com.example.vivek.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vivek.inventoryapp.data.PhoneInventoryContract;
import com.example.vivek.inventoryapp.data.PhoneInventoryDBHelper;

public class EditorActivity extends AppCompatActivity {

    public PhoneInventoryDBHelper dbHelper;
    private EditText deviceNameEditText;
    private EditText deviceCostEditText;
    private EditText deviceQuantityEditText;
    private EditText supplierNameEditText;
    private EditText supplierContactEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        deviceNameEditText = findViewById(R.id.device_name_editText);
        deviceCostEditText = findViewById(R.id.device_cost_editText);
        deviceQuantityEditText = findViewById(R.id.device_quantity_editText);
        supplierNameEditText = findViewById(R.id.supplier_name__editText);
        supplierContactEditText = findViewById(R.id.supplier_phone_editText);
        dbHelper = new PhoneInventoryDBHelper(this);
    }

    private void insertDevice() {

        String deviceName;
        if (TextUtils.isEmpty(deviceNameEditText.getText())) {
            deviceNameEditText.setError(getString(R.string.field_error));
            return;
        } else {
            deviceName = deviceNameEditText.getText().toString().trim();
        }

        String deviceCost;
        if (TextUtils.isEmpty(deviceCostEditText.getText())) {
            deviceCostEditText.setError(getString(R.string.field_error));
            return;
        } else {
            deviceCost = deviceCostEditText.getText().toString().trim();
        }

        String deviceQuantity;
        if (TextUtils.isEmpty(deviceQuantityEditText.getText())) {
            deviceQuantityEditText.setError(getString(R.string.field_error));
            return;
        } else {
            deviceQuantity = deviceQuantityEditText.getText().toString().trim();
        }

        String supplierName;
        if (TextUtils.isEmpty(supplierNameEditText.getText())) {
            supplierNameEditText.setError(getString(R.string.field_error));
            return;
        } else {
            supplierName = supplierNameEditText.getText().toString().trim();
        }

        String supplierContact;
        if (TextUtils.isEmpty(supplierContactEditText.getText())) {
            supplierContactEditText.setError(getString(R.string.field_error));
            return;
        } else {
            supplierContact = supplierContactEditText.getText().toString().trim();
        }

        int deviceCostInt = Integer.parseInt(deviceCost);
        if (deviceCostInt < 0) {
            deviceCostEditText.setError(getString(R.string.cost_error));
            Toast.makeText(this, getString(R.string.cost_error), Toast.LENGTH_SHORT).show();
            return;
        }
        int deviceQuantityInt = Integer.parseInt(deviceQuantity);
        if (deviceQuantityInt < 0) {
            deviceQuantityEditText.setError(getString(R.string.quantity_error));
            Toast.makeText(this, getString(R.string.quantity_error), Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_NAME, deviceName);
        values.put(PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_COST, deviceCostInt);
        values.put(PhoneInventoryContract.DeviceEntry.COLUMN_DEVICE_QUANTITY, deviceQuantityInt);
        values.put(PhoneInventoryContract.DeviceEntry.COLUMN_SUPPLIER_NAME, supplierName);
        values.put(PhoneInventoryContract.DeviceEntry.COLUMN_SUPPLIER_PHONE_NO, supplierContact);

        long newRowId = db.insert(PhoneInventoryContract.DeviceEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, getString(R.string.device_error), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.device_saved) + " " + newRowId, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_saveInfo:
                insertDevice();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
