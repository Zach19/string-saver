package com.example.zachhauser.databasepractice1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.SyncStateContract;

/**
 * Created by zachhauser on 2016-12-22.
 */

public class DBHandler extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "practice.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_QUOTES = "quotes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_QUOTE = "quote";

//    private static DBHandler sInstance;
//
//    public static synchronized DBHandler getInstance(Context context) {
//        if(sInstance == null) {
//            sInstance = new DBHandler(context.getApplicationContext());
//        }
//        return sInstance;
//    }

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_QUOTE = "CREATE TABLE " + TABLE_QUOTES +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_AUTHOR + " TEXT," +
                COLUMN_QUOTE + " TEXT" + ")";

        db.execSQL(CREATE_TABLE_QUOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUOTES);
            onCreate(db);

    }

    public void addQuote(Quotes quotes) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_AUTHOR, quotes.getName());
        values.put(COLUMN_QUOTE, quotes.getQuote());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_QUOTES, null, values);
        db.close();
    }

    public Quotes findQuotes(String author) {

        String query = "Select * FROM " + TABLE_QUOTES + " WHERE " + COLUMN_AUTHOR + " = \"" + author + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Quotes quote = new Quotes();

        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            quote.setId(Integer.parseInt(cursor.getString(0)));
            quote.setName(cursor.getString(1));
            quote.setQuote(cursor.getString(2));
            cursor.close();
        }

        else {
            quote = null;
        }

        db.close();
        return quote;
    }

    public boolean deleteQuotes(String author) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_QUOTES + " WHERE " + COLUMN_AUTHOR + " = \"" + author + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Quotes quote = new Quotes();

        if(cursor.moveToFirst()) {
            quote.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_QUOTES, COLUMN_ID + " = ?",
                    new String[] {String.valueOf(quote.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }



}
