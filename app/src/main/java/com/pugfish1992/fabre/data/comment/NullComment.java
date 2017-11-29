package com.pugfish1992.fabre.data.comment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pugfish1992.fabre.util.ImmCalendar;

import java.util.Calendar;

/**
 * Created by daichi on 11/29/17.
 */

/* Intentional package-private visibility */
class NullComment implements Comment {

    @NonNull private final ImmCalendar mTimeStamp;

    NullComment() {
        mTimeStamp = ImmCalendar.createImmutableCalendar(Calendar.getInstance());
    }

    @Override
    public long id() {
        return INVALID_ID;
    }

    @Nullable
    @Override
    public String text() {
        return "Sorry, failed to load comment data.";
    }

    @NonNull
    @Override
    public ImmCalendar createdAt() {
        return mTimeStamp;
    }

    @NonNull
    @Override
    public ImmCalendar lastModifiedAt() {
        return mTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NullComment that = (NullComment) o;

        return mTimeStamp.equals(that.mTimeStamp);
    }

    @Override
    public int hashCode() {
        return mTimeStamp.hashCode();
    }
}