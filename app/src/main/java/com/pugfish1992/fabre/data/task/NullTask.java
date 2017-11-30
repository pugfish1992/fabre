package com.pugfish1992.fabre.data.task;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pugfish1992.fabre.data.comment.Comment;
import com.pugfish1992.fabre.data.comment.NullComment;

import java.util.Collections;
import java.util.List;

/**
 * Created by daichi on 11/30/17.
 */

public class NullTask implements Task {


    @NonNull private static final List<Comment> EMPTY_COMMENT_LIST
            = Collections.unmodifiableList(Collections.<Comment>emptyList());

    @NonNull private final Comment mNullComment;

    public NullTask() {
        mNullComment = new NullComment();
    }

    @Override
    public long id() {
        return INVALID_ID;
    }

    @Nullable
    @Override
    public String name() {
        return "INVALID TASK DATA";
    }

    @NonNull
    @Override
    public Comment primaryComment() {
        return mNullComment;
    }

    @NonNull
    @Override
    public List<Comment> comments() {
        return EMPTY_COMMENT_LIST;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NullTask nullTask = (NullTask) o;

        return mNullComment.equals(nullTask.mNullComment);
    }

    @Override
    public int hashCode() {
        return mNullComment.hashCode();
    }
}
