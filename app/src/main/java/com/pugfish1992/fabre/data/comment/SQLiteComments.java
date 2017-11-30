package com.pugfish1992.fabre.data.comment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.pugfish1992.fabre.data.Record;
import com.pugfish1992.fabre.data.sqlite.SQLiteStorage;
import com.pugfish1992.fabre.data.sqlite.SQLiteTableDef;

import java.util.Calendar;

/**
 * Created by daichi on 11/29/17.
 */

public class SQLiteComments extends SQLiteStorage<Comment, CommentDiff> implements Comments {

    private static final NullComment NULL_COMMENT = new NullComment();

    private static final String TABLE_NAME = "comment";
    private static final String COL_ID = _ID;
    private static final String COL_TEXT = "text";
    private static final String COL_TS_CREATED_AT = "ts_created_at";
    private static final String COL_TS_LAST_MODIFIED_AT = "ts_last_modified_at";

    public static final SQLiteTableDef TABLE_DEFINITION = SQLiteTableDef
            .tableName(TABLE_NAME)
            .integerPrimaryKeyColumn(COL_ID)
            .textColumn(COL_TEXT)
            .integerColumn(COL_TS_CREATED_AT)
            .integerColumn(COL_TS_LAST_MODIFIED_AT)
            .build();

    public SQLiteComments(@NonNull Context context) {
        super(context);
    }

    @NonNull
    public Comment load(long id) {
        SQLiteDatabase db = openReadableDatabase();
        Comment comment = this.load(id, db);
        db.close();
        return comment;
    }

    @NonNull
    @Override
    public Comment load(long id, @NonNull SQLiteDatabase db) {
        String where = COL_ID + "=" + String.valueOf(id);
        Cursor cursor = db.query(TABLE_NAME, null, where,
                null, null, null, null);

        Comment comment;
        if (cursor.getCount() == 1) {
            ContentValues out = new ContentValues();
            DatabaseUtils.cursorRowToContentValues(cursor, out);
            comment = this.unpackRowData(out);
        } else {
            comment = NULL_COMMENT;
        }

        cursor.close();
        return comment;
    }

    @NonNull
    @Override
    public Comment alter(@NonNull Comment record, @NonNull CommentDiff diff) {
        SQLiteDatabase db = openWritableDatabase();
        Comment comment = this.alter(record, diff, db);
        db.close();
        return comment;
    }

    @NonNull
    @Override
    public Comment alter(@NonNull Comment record, @NonNull CommentDiff diff, @NonNull SQLiteDatabase db) {
        if (record instanceof NullComment) return record;
        Comment newComment = diff.apply(record);
        String where = COL_ID + "=" + record.id();
        ContentValues data = packRowData(newComment, true);
        boolean wasSuccessful;

        db.beginTransaction();
        try {
            int affected = db.update(TABLE_NAME, data, where, null);
            wasSuccessful = (affected == 1);
            if (wasSuccessful) db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return (wasSuccessful) ? newComment : record;
    }

    @Override
    public void delete(@NonNull Comment record) {
        SQLiteDatabase db = openWritableDatabase();
        this.delete(record, db);
        db.close();
    }

    @Override
    public void delete(@NonNull Comment record, @NonNull SQLiteDatabase db) {
        if (record instanceof NullComment) return;
        String where = COL_ID + "=" + record.id();
        db.beginTransaction();
        try {
            int affected = db.delete(TABLE_NAME, where, null);
            if (affected == 1) db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @NonNull
    @Override
    public Comment insert(String text, @NonNull Calendar createdAt, @NonNull Calendar lastModifiedAt) {
        Comment newComment = new CommentImpl(Record.INVALID_ID, text, createdAt, lastModifiedAt);
        ContentValues data = packRowData(newComment, false);
        SQLiteDatabase db = openWritableDatabase();
        long newRowId = db.insert(TABLE_NAME, null, data);
        db.close();
        if (newRowId != -1) {
            return new CommentImpl(newRowId, text, newComment.createdAt(), newComment.lastModifiedAt());
        } else {
            return NULL_COMMENT;
        }
    }

    @NonNull
    private ContentValues packRowData(@NonNull Comment comment, boolean includeID) {
        ContentValues values = new ContentValues();
        if (includeID) values.put(COL_ID, comment.id());
        values.put(COL_TEXT, comment.text());
        values.put(COL_TS_CREATED_AT, comment.createdAt().getTimeInMillis());
        values.put(COL_TS_LAST_MODIFIED_AT, comment.lastModifiedAt().getTimeInMillis());
        return values;
    }

    @NonNull
    private Comment unpackRowData(@NonNull ContentValues values) {
        long id = values.getAsLong(COL_ID);
        String text = values.getAsString(COL_TEXT);
        Calendar createdAt = Calendar.getInstance();
        createdAt.setTimeInMillis(values.getAsLong(COL_TS_CREATED_AT));
        Calendar lastModifiedAt = Calendar.getInstance();
        lastModifiedAt.setTimeInMillis(values.getAsLong(COL_TS_LAST_MODIFIED_AT));
        return new CommentImpl(id, text, createdAt, lastModifiedAt);
    }
}
