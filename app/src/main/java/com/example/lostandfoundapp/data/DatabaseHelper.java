package com.example.lostandfoundapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lostandfoundapp.model.Advert;
import com.example.lostandfoundapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_AD_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                Util.AD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                Util.AD_NAME + " TEXT, " + Util.AD_PHONE + " TEXT, " + Util.AD_DESCRIPTION + " TEXT, " +
                Util.AD_DATE + " TEXT, " + Util.AD_LOCATION + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_AD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_AD_TABLE = "DROP TABLE IF EXISTS''";
        sqLiteDatabase.execSQL(DROP_AD_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(sqLiteDatabase);
    }

    public long insertAd(Advert advert){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.AD_NAME, advert.getAdName());
        contentValues.put(Util.AD_PHONE, advert.getPhone());
        contentValues.put(Util.AD_DESCRIPTION, advert.getDescription());
        contentValues.put(Util.AD_DATE, advert.getDate());
        contentValues.put(Util.AD_LOCATION, advert.getLocation());

        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();

        return newRowId;


    }

    public List<Advert> fetchAllAds(){
        List<Advert> adList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst())
        {
            do
            {
                Advert advert = new Advert();
                advert.setAd_id(cursor.getInt(0));
                advert.setAdName(cursor.getString(1));
                advert.setPhone(cursor.getString(2));
                advert.setDescription(cursor.getString(3));
                advert.setDate(cursor.getString(4));
                advert.setLocation(cursor.getString(5));

                adList.add(advert);

            } while(cursor.moveToNext());
        }

        return adList;

    }

    public int deleteAd(Advert advert)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(Util.TABLE_NAME, Util.AD_ID + "=?", new String[]{String.valueOf(advert.getAd_id())});

    }



}
