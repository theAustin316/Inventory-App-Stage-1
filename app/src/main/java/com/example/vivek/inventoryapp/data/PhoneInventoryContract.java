package com.example.vivek.inventoryapp.data;

import android.provider.BaseColumns;

public class PhoneInventoryContract {

    private PhoneInventoryContract() {

    }
    public static final class DeviceEntry implements BaseColumns {

        private DeviceEntry() {

        }

        public static final String TABLE_NAME = "devices";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DEVICE_NAME = "Device_Name";
        public static final String COLUMN_DEVICE_COST = "Cost";
        public static final String COLUMN_DEVICE_QUANTITY = "Quantity";
        public static final String COLUMN_SUPPLIER_NAME = "Supplier_Name";
        public static final String COLUMN_SUPPLIER_PHONE_NO = "Supplier_Phone_No";

    }
}
