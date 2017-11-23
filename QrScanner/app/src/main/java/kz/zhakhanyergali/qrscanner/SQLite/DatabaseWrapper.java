package kz.zhakhanyergali.qrscanner.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import kz.zhakhanyergali.qrscanner.SQLite.ORM.HistoryORM;

/**
 * Created by zhakhanyergali on 21.11.17.
 */

public class DatabaseWrapper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseWrapper.class.getCanonicalName();

    public static final String DATABASE_NAME = "QrScanner.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseWrapper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating database [" + DATABASE_NAME + "]");

        db.execSQL(HistoryORM.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrading database [" + DATABASE_NAME + "v." + oldVersion + "to v." + newVersion + "]");

        db.execSQL(HistoryORM.SQL_DROP_TABLE);

        onCreate(db);
    }

}
