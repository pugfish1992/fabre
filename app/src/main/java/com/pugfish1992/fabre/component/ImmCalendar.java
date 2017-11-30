package com.pugfish1992.fabre.component;

import android.support.annotation.NonNull;

import java.util.Calendar;

/**
 * Created by daichi on 11/29/17.
 * Immutable Calendar class.
 */

public final class ImmCalendar implements Comparable<Calendar> {

    public static ImmCalendar createImmutableCalendar(@NonNull Calendar calendar) {
        return new ImmCalendar(calendar);
    }

    public static ImmCalendar createImmutableCalendar(long timeInMillis) {
        return new ImmCalendar(timeInMillis);
    }

    @NonNull private final Calendar mCalendar;

    /* Intentional private visibility */
    private ImmCalendar(@NonNull Calendar calendar) {
        this(calendar.getTimeInMillis());
    }

    /* Intentional private visibility */
    private ImmCalendar(long timeInMillis) {
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(timeInMillis);
    }

    public int year() {
        return mCalendar.get(Calendar.YEAR);
    }

    public int month() {
        return mCalendar.get(Calendar.MONTH);
    }

    public int dayOfMonth() {
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public int dayOfWeek() {
        return mCalendar.get(Calendar.DAY_OF_WEEK);
    }

    public int hour() {
        return mCalendar.get(Calendar.HOUR);
    }

    public int hourOfDay() {
        return mCalendar.get(Calendar.HOUR_OF_DAY);
    }

    public int minute() {
        return mCalendar.get(Calendar.MINUTE);
    }

    public int second() {
        return mCalendar.get(Calendar.SECOND);
    }

    public int millsecond() {
        return mCalendar.get(Calendar.MILLISECOND);
    }

    public boolean before(Object when) {
        return mCalendar.before(when);
    }

    public boolean after(Object when) {
        return mCalendar.after(when);
    }

    public Calendar asMutableCalendar() {
        Calendar mutable = Calendar.getInstance();
        mutable.setTimeInMillis(mCalendar.getTimeInMillis());
        return mutable;
    }

    public long getTimeInMillis() {
        return mCalendar.getTimeInMillis();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImmCalendar that = (ImmCalendar) o;

        return mCalendar.equals(that.mCalendar);
    }

    @Override
    public int hashCode() {
        return mCalendar.hashCode();
    }

    @Override
    public int compareTo(@NonNull Calendar anotherCalendar) {
        return mCalendar.compareTo(anotherCalendar);
    }
}
