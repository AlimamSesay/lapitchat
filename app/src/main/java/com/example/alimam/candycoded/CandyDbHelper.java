package com.example.alimam.candycoded;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alimam on 11/18/17.
 */

public class CandyDbHelper extends SQLiteOpenHelper {

    public CandyDbHelper(Context context){
        super(context,
            CandyContract.DB_NAME ,
            null,
            CandyContract.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CandyContract.SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CandyContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
