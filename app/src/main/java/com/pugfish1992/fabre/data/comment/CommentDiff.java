package com.pugfish1992.fabre.data.comment;

import android.support.annotation.NonNull;

import com.pugfish1992.fabre.data.Diff;
import com.pugfish1992.fabre.component.ImmCalendar;

import java.util.Calendar;

/**
 * Created by daichi on 11/29/17.
 */

public class CommentDiff implements Diff {

    private String mNewText;
    private Calendar mNewCreatedAt;
    private Calendar mNewLastModifiedAt;

    private boolean mSetNewText = false;
    private boolean mSetNewCreatedAt = false;
    private boolean mSetNewLastModifiedAt = false;

    public CommentDiff newText(String newText) {
        mNewText = newText;
        mSetNewText = true;
        return this;
    }

    public CommentDiff newCreatedAt(@NonNull Calendar newCreatedAt) {
        mNewCreatedAt = newCreatedAt;
        mSetNewCreatedAt = true;
        return this;
    }

    public CommentDiff newLastModifiedAt(@NonNull Calendar newLastModifiedAt) {
        mNewLastModifiedAt = newLastModifiedAt;
        mSetNewLastModifiedAt = true;
        return this;
    }

    /* Intentional package-private visibility */
    @NonNull
    Comment apply(@NonNull Comment source) {
        String text = (mSetNewText) ? mNewText : source.text();

        ImmCalendar createdAt = (mSetNewCreatedAt)
                ? ImmCalendar.createImmutableCalendar(mNewCreatedAt)
                : source.createdAt();

        ImmCalendar lastModifiedAt = (mSetNewLastModifiedAt)
                ? ImmCalendar.createImmutableCalendar(mNewLastModifiedAt)
                : source.lastModifiedAt();

        return new CommentImpl(source.id(), text, createdAt, lastModifiedAt);
    }
}
