package com.pugfish1992.fabre.data.task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.pugfish1992.fabre.data.comment.Comment;
import com.pugfish1992.fabre.data.comment.CommentImpl;
import com.pugfish1992.fabre.data.comment.SQLiteComments;
import com.pugfish1992.fabre.data.sqlite.SQLiteStorage;
import com.pugfish1992.fabre.data.sqlite.SQLiteTableDef;

import java.util.Calendar;
import java.util.List;

/**
 * Created by daichi on 11/30/17.
 */

public class SQLiteTasks extends SQLiteStorage<Task, TaskDiff> implements Tasks {

    private static final NullTask NULL_TASK = new NullTask();

    private static final String TABLE_NAME_TASK = "task";
    private static final String T_COL_ID = _ID;
    private static final String T_COL_NAME = "name";
    private static final String T_COL_PRIMARY_COMMENT_ID = "primary_comment_id";

    public static final SQLiteTableDef TASK_TABLE_DEFINITION = SQLiteTableDef
            .tableName(TABLE_NAME_TASK)
            .integerPrimaryKeyColumn(T_COL_ID)
            .textColumn(T_COL_NAME)
            .integerColumn(T_COL_PRIMARY_COMMENT_ID)
            .build();

    // Junction table
    private static final String TABLE_NAME_COMMENT_TASK = "task_comment";
    private static final String CT_COL_ID = _ID;
    private static final String CT_COL_TASK_ID = "task_id";
    private static final String CT_COL_COMMENT_ID = "comment_id";

    public static final SQLiteTableDef COMMENT_TASK_TABLE_DEFINITION = SQLiteTableDef
            .tableName(TABLE_NAME_COMMENT_TASK)
            .integerPrimaryKeyColumn(CT_COL_ID)
            .integerColumn(CT_COL_TASK_ID)
            .integerColumn(CT_COL_COMMENT_ID)
            .build();

    public SQLiteTasks(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public Task load(long id) {
        SQLiteDatabase db = openReadableDatabase();
        Task task = this.load(id, db);
        db.close();
        return task;
    }

    @NonNull
    @Override
    public Task load(long id, @NonNull SQLiteDatabase db) {
        String where = T_COL_ID + "=" + String.valueOf(id);
        Cursor cursor = db.query(TABLE_NAME_TASK, null, where,
                null, null, null, null);

        Task task;
        if (cursor.getCount() == 1) {
            ContentValues out = new ContentValues();
            DatabaseUtils.cursorRowToContentValues(cursor, out);
            task = this.unpackRowData(out);
        } else {
            task = NULL_COMMENT;
        }

        cursor.close();
        return task;
    }

    @NonNull
    @Override
    public Task alter(@NonNull Task record, @NonNull TaskDiff diff) {
        return null;
    }

    @NonNull
    @Override
    public Task alter(@NonNull Task record, @NonNull TaskDiff diff, @NonNull SQLiteDatabase db) {
        return null;
    }

    @Override
    public void delete(@NonNull Task record) {

    }

    @Override
    public void delete(@NonNull Task record, @NonNull SQLiteDatabase db) {

    }

    @NonNull
    @Override
    public Task insert(String name, @NonNull Comment primaryComment) {
        return null;
    }

    @NonNull
    @Override
    public Task insert(String name, @NonNull Comment primaryComment, @NonNull List<Comment> comments) {
        return null;
    }

    @NonNull
    private ContentValues packRowData(@NonNull Task task, boolean includeID) {
        ContentValues values = new ContentValues();
        if (includeID) values.put(T_COL_ID, task.id());
        values.put(T_COL_NAME, task.name());
        values.put(T_COL_PRIMARY_COMMENT_ID, task.primaryComment().id());
        return values;
    }

    @NonNull
    private Task unpackRowData(@NonNull ContentValues values,
                               @NonNull SQLiteComments comments,
                               @NonNull SQLiteDatabase db) {

        long id = values.getAsLong(T_COL_ID);
        String name = values.getAsString(T_COL_NAME);
        long primaryCommentId = values.getAsLong(T_COL_PRIMARY_COMMENT_ID);

        Comment primaryComment = comments.load(primaryCommentId, db);

        return new TaskImpl(id, name, primaryComment);
    }
}
