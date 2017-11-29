package com.pugfish1992.fabre.data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pugfish1992.fabre.data.comment.SQLiteComments;
import com.pugfish1992.fabre.util.SQLiteTableUtils;

/**
 * Created by daichi on 11/29/17.
 */

public class SqliteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Fabre.db";
    private static final int DATABASE_VERSION = 1;

    public SqliteOpenHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SQLiteTableUtils.createTablesIfNotExists(db,
                SQLiteComments.TABLE_DEFINITION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
