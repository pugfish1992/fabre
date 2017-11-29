package com.pugfish1992.fabre.data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.pugfish1992.fabre.data.Alter;
import com.pugfish1992.fabre.data.Record;

/**
 * Created by daichi on 11/29/17.
 */

abstract public class SQLiteStorage<T extends Record, U extends Alter> implements BaseColumns {

    @NonNull private final SQLiteOpenHelper mOpenHelper;

    public SQLiteStorage(@NonNull Context context) {
        mOpenHelper = new SqliteOpenHelper(context);
    }

    protected SQLiteDatabase openWritableDatabase() {
        return mOpenHelper.getWritableDatabase();
    }

    protected SQLiteDatabase openReadableDatabase() {
        return mOpenHelper.getReadableDatabase();
    }

    @NonNull abstract public T load(long id, @NonNull SQLiteDatabase db);
    @NonNull abstract public T alter(@NonNull T record, @NonNull U alter, @NonNull SQLiteDatabase db);
    abstract public void delete(@NonNull T record, @NonNull SQLiteDatabase db);
}
