package com.pugfish1992.fabre.util;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by daichi on 11/29/17.
 */

public class SQLiteTableUtils {

    /* Intentional private visibility */
    private SQLiteTableUtils() {}

    public static void createTables(@NonNull SQLiteDatabase db, @NonNull SQLiteTableDef... tableDefs) {
        for (SQLiteTableDef tableDef : tableDefs) {
            String statement = String.format("create table %s(%s);",
                    tableDef.getTableName(), TextUtils.join(",", tableDef.getColumnDefinitions()));
            db.execSQL(statement);
        }
    }

    public static void createTablesIfNotExists(@NonNull SQLiteDatabase db, @NonNull SQLiteTableDef... tableDefs) {
        for (SQLiteTableDef tableDef : tableDefs) {
            String statement = String.format("create table if not exists %s(%s);",
                    tableDef.getTableName(), TextUtils.join(",", tableDef.getColumnDefinitions()));
            db.execSQL(statement);
        }
    }

    public static void dropTable(@NonNull String table, @NonNull SQLiteDatabase db) {
        String statement = "drop table " + table;
        db.execSQL(statement);
    }

    public static void dropTableIfExists(@NonNull String table, @NonNull SQLiteDatabase db) {
        String statement = "drop table if exists " + table;
        db.execSQL(statement);
    }
}
