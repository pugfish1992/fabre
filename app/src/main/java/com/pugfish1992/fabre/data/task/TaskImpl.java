package com.pugfish1992.fabre.data.task;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pugfish1992.fabre.data.comment.Comment;

import java.util.Collections;
import java.util.List;

/**
 * Created by daichi on 11/30/17.
 */

/* Intentional package-private visibility */
class TaskImpl implements Task {

    private final long mId;
    @Nullable private final String mName;
    @NonNull private final Comment mPrimaryComment;
    @NonNull private final List<Comment> mComments;

    TaskImpl(long id, @Nullable String name,
             @NonNull Comment primaryComment,
             @NonNull List<Comment> comments) {

        mId = id;
        mName = name;
        mPrimaryComment = primaryComment;
        mComments = Collections.unmodifiableList(comments);
    }

    @Override
    public long id() {
        return mId;
    }

    @Nullable
    @Override
    public String name() {
        return mName;
    }

    @NonNull
    @Override
    public Comment primaryComment() {
        return mPrimaryComment;
    }

    @NonNull
    @Override
    public List<Comment> comments() {
        return mComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskImpl task = (TaskImpl) o;

        if (mId != task.mId) return false;
        if (mName != null ? !mName.equals(task.mName) : task.mName != null) return false;
        if (!mPrimaryComment.equals(task.mPrimaryComment)) return false;
        return mComments.equals(task.mComments);
    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + mPrimaryComment.hashCode();
        result = 31 * result + mComments.hashCode();
        return result;
    }
}
