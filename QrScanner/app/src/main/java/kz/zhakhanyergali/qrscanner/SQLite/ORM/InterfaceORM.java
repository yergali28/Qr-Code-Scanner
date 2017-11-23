package kz.zhakhanyergali.qrscanner.SQLite.ORM;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

/**
 * Created by zhakhanyergali on 21.11.17.
 */

public interface InterfaceORM<T> {

    public T cursorToObject(Cursor cursor);
    public void add(Context context, T t);
    public void clearAll(Context context);
    public List<T> getAll(Context context);

}
