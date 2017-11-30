package com.pugfish1992.fabre.data.comment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pugfish1992.fabre.component.ImmCalendar;

import java.util.Calendar;

/**
 * Created by daichi on 11/29/17.
 */

/* Intentional package-private visibility */
class CommentImpl implements Comment {

    private final long mId;
    @Nullable private final String mText;
    @NonNull private final ImmCalendar mCreatedAt;
    @NonNull private final ImmCalendar mLastModifiedAt;

    CommentImpl(long id,
                @Nullable String text,
                @NonNull Calendar createdAt,
                @NonNull Calendar lastModifiedAt) {

        mId = id;
        mText = text;
        mCreatedAt = ImmCalendar.createImmutableCalendar(createdAt);
        mLastModifiedAt = ImmCalendar.createImmutableCalendar(lastModifiedAt);
    }

    CommentImpl(long id,
                @Nullable String text,
                @NonNull ImmCalendar createdAt,
                @NonNull ImmCalendar lastModifiedAt) {

        mId = id;
        mText = text;
        mCreatedAt = createdAt;
        mLastModifiedAt = lastModifiedAt;
    }

    @Override
    public long id() {
        return mId;
    }

    @Nullable
    @Override
    public String text() {
        return mText;
    }

    @NonNull
    @Override
    public ImmCalendar createdAt() {
        return mCreatedAt;
    }

    @NonNull
    @Override
    public ImmCalendar lastModifiedAt() {
        return mLastModifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentImpl comment = (CommentImpl) o;

        if (mId != comment.mId) return false;
        if (mText != null ? !mText.equals(comment.mText) : comment.mText != null) return false;
        if (!mCreatedAt.equals(comment.mCreatedAt)) return false;
        return mLastModifiedAt.equals(comment.mLastModifiedAt);
    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mText != null ? mText.hashCode() : 0);
        result = 31 * result + mCreatedAt.hashCode();
        result = 31 * result + mLastModifiedAt.hashCode();
        return result;
    }
}
