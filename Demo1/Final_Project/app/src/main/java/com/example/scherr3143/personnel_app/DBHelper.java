package com.example.scherr3143.personnel_app;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scherr3143 on 3/7/2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myContacts";
    private static final String TABLE_NAME = "people";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_IMAGEURI = "imageUri";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, "
                + KEY_EMAIL + " TEXT, " + KEY_ADDRESS + " TEXT, " + KEY_PHONE + " TEXT, " + KEY_IMAGEURI + " TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void createContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();

        String insert = "INSERT or replace INTO " + TABLE_NAME + "(" + KEY_NAME + ", " + KEY_EMAIL + ", " + KEY_ADDRESS
                + ", " + KEY_PHONE + ", " + KEY_IMAGEURI + ") VALUES('" + contact.getName() + "', '" + contact.getEmail()
                + "', '" + contact.getAddress() + "', '" + contact.getPhone()
                + "', '" + contact.getPhotoUri() + "')";

        db.execSQL(insert);
        db.close();
    }

    public void updateContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.getName());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_ADDRESS, contact.getAddress());
        values.put(KEY_PHONE, contact.getPhone());
        values.put(KEY_IMAGEURI, contact.getPhotoUri().toString());

        db.update(TABLE_NAME, values, KEY_ID + "=?", new String[]{String.valueOf(contact.getID())});
        db.close();
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(contact.getID())});
    }

    public int getContactsCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        int count = cursor.getCount();
        db.close();
        cursor.close();

        return count;
    }

    public List<Contact> getAllContacts(){
        List<Contact> allContacts = new ArrayList<Contact>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do{
                allContacts.add(new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), Uri.parse(cursor.getString(5))));
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return allContacts;
    }
}