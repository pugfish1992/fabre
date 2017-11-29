package com.pugfish1992.fabre.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by daichi on 11/29/17.
 */

public class SQLiteTableDef {

    public static SQLiteTableDef.Builder tableName(@NonNull String tableName) {
        return new SQLiteTableDef.Builder(tableName);
    }

    @NonNull private final String mTableName;
    @NonNull private final List<String> mColumnDefs;

    /* Intentional private visibility */
    private SQLiteTableDef(@NonNull String tableName, @NonNull List<String> columnDefs) {
        mTableName = tableName;
        mColumnDefs = Collections.unmodifiableList(columnDefs);
    }

    @NonNull
    public String getTableName() {
        return mTableName;
    }

    @NonNull
    public List<String> getColumnDefinitions() {
        return mColumnDefs;
    }

    @Override
    public String toString() {
        return String.format("%s(%s);", mTableName, TextUtils.join(",", mColumnDefs));
    }

    /**
     * BUILDER CLASS
     * -------------- */

    public static class Builder {

        @NonNull private final String mTableName;
        @NonNull private final List<String> mColumnDefs;

        /* Intentional private visibility */
        private Builder(@NonNull String tableName) {
            mTableName = tableName;
            mColumnDefs = new ArrayList<>();
        }

        public Builder column(@NonNull String columnName) {
            addColumn(columnName, null);
            return this;
        }

        public Builder integerPrimaryKeyColumn(@NonNull String columnName) {
            addColumn(columnName, "INTEGER PRIMARY KEY");
            return this;
        }

        public Builder integerColumn(@NonNull String columnName) {
            addColumn(columnName, "INTEGER");
            return this;
        }

        public Builder textColumn(@NonNull String columnName) {
            addColumn(columnName, "TEXT");
            return this;
        }

        public Builder realColumn(@NonNull String columnName) {
            addColumn(columnName, "REAL");
            return this;
        }

        public Builder numericColumn(@NonNull String columnName) {
            addColumn(columnName, "NUMERIC");
            return this;
        }

        public Builder blobColumn(@NonNull String columnName) {
            addColumn(columnName, "BLOB");
            return this;
        }

        private void addColumn(@NonNull String columnName, String options) {
            if (options != null) {
                mColumnDefs.add(columnName + " " + options);
            } else {
                mColumnDefs.add(columnName);
            }
        }

        public SQLiteTableDef build() {
            return new SQLiteTableDef(mTableName, mColumnDefs);
        }
    }
}
