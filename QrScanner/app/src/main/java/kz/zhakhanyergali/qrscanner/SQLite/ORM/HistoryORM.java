package kz.zhakhanyergali.qrscanner.SQLite.ORM;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kz.zhakhanyergali.qrscanner.Entity.History;
import kz.zhakhanyergali.qrscanner.SQLite.DatabaseWrapper;

/**
 * Created by zhakhanyergali on 21.11.17.
 */

public class HistoryORM implements InterfaceORM<History> {

    private static final String TAG = HistoryORM.class.getCanonicalName();

    private static final String TABLE_NAME = "history";
    private static final String COMMA_SEPARATOR = ", ";

    private static final String COLUMN_ID_TYPE = "integer PRIMARY KEY AUTOINCREMENT";
    private static final String COLUMN_ID = "id";

    private static final String COLUMN_CONTEXT_TYPE = "TEXT";
    private static final String COLUMN_CONTEXT = "context";

    private static final String COLUMN_DATE_TYPE = "TEXT";
    private static final String COLUMN_DATE = "date";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " " + COLUMN_ID_TYPE + COMMA_SEPARATOR +
            COLUMN_DATE + " " + COLUMN_DATE_TYPE + COMMA_SEPARATOR + COLUMN_CONTEXT + " " + COLUMN_CONTEXT_TYPE + ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    @Override
    public History cursorToObject(Cursor cursor) {
        History h = new History();
        h.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        h.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
        h.setContext(cursor.getString(cursor.getColumnIndex(COLUMN_CONTEXT)));
        return h;
    }

    @Override
    public void add(Context context, History h) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        String query = "INSERT INTO " + TABLE_NAME + "(" + COLUMN_DATE + ", " + COLUMN_CONTEXT + ") VALUES ( '" + h.getDate() + "', '" + h.getContext() + "' )";
        database.execSQL(query);

        database.close();
    }

    @Override
    public void clearAll(Context context) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();
        database.delete(TABLE_NAME, null, null);
    }

    @Override
    public List<History> getAll(Context context) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();
        List<History> historyList = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    History h = cursorToObject(cursor);
                    historyList.add(h);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get history from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        database.close();

        return historyList;
    }
}
