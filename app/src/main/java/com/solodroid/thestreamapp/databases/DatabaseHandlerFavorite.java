package com.solodroid.thestreamapp.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.solodroid.thestreamapp.models.Channel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandlerFavorite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_channel_favorite";
    private static final String TABLE_NAME = "tbl_channel_favorite";
    private static final String KEY_ID = "id";

    //    private static final String KEY_CAT_ID = "catid";
    private static final String KEY_CAT_NAME = "category_name";

    private static final String KEY_CHANNEL_ID = "channel_id";
    private static final String KEY_CHANNEL_NAME = "channel_name";
    private static final String KEY_CHANNEL_IMAGE = "channel_image";
    private static final String KEY_CHANNEL_URL = "channel_url";
    private static final String KEY_CHANNEL_DESCRIPTION = "channel_description";


    public DatabaseHandlerFavorite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_CAT_NAME + " TEXT,"
                + KEY_CHANNEL_ID + " TEXT,"
                + KEY_CHANNEL_NAME + " TEXT,"
                + KEY_CHANNEL_IMAGE + " TEXT,"
                + KEY_CHANNEL_URL + " TEXT,"
                + KEY_CHANNEL_DESCRIPTION + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    //Adding Record in Database

    public void AddtoFavorite(Channel pj) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CAT_NAME, pj.getCategory_name());
        values.put(KEY_CHANNEL_ID, pj.getChannel_id());
        values.put(KEY_CHANNEL_NAME, pj.getChannel_name());
        values.put(KEY_CHANNEL_IMAGE, pj.getChannel_image());
        values.put(KEY_CHANNEL_URL, pj.getChannel_url());
        values.put(KEY_CHANNEL_DESCRIPTION, pj.getChannel_description());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection

    }

    // Getting All Data
    public List<Channel> getAllData() {
        List<Channel> dataList = new ArrayList<Channel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Channel contact = new Channel();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setCategory_name(cursor.getString(1));
                contact.setChannel_id(cursor.getString(2));
                contact.setChannel_name(cursor.getString(3));
                contact.setChannel_image(cursor.getString(4));
                contact.setChannel_url(cursor.getString(5));
                contact.setChannel_description(cursor.getString(6));

                // Adding contact to list
                dataList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

    //getting single row
    public List<Channel> getFavRow(String id) {
        List<Channel> dataList = new ArrayList<Channel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE channel_id=" + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Channel contact = new Channel();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setCategory_name(cursor.getString(1));
                contact.setChannel_id(cursor.getString(2));
                contact.setChannel_name(cursor.getString(3));
                contact.setChannel_image(cursor.getString(4));
                contact.setChannel_url(cursor.getString(5));
                contact.setChannel_description(cursor.getString(6));

                // Adding contact to list
                dataList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

    //for remove favorite
    public void RemoveFav(Channel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_CHANNEL_ID + " = ?",
                new String[]{String.valueOf(contact.getChannel_id())});
        db.close();
    }

    public enum DatabaseManager {
        INSTANCE;
        private SQLiteDatabase db;
        private boolean isDbClosed = true;
        DatabaseHandlerFavorite dbHelper;

        public void init(Context context) {
            dbHelper = new DatabaseHandlerFavorite(context);
            if (isDbClosed) {
                isDbClosed = false;
                this.db = dbHelper.getWritableDatabase();
            }

        }

        public boolean isDatabaseClosed() {
            return isDbClosed;
        }

        public void closeDatabase() {
            if (!isDbClosed && db != null) {
                isDbClosed = true;
                db.close();
                dbHelper.close();
            }
        }
    }

}
