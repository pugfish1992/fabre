package com.pugfish1992.fabre.data.comment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pugfish1992.fabre.data.Record;
import com.pugfish1992.fabre.util.ImmCalendar;

/**
 * Created by daichi on 11/29/17.
 */

public interface Comment extends Record {
    @Nullable String text();
    @NonNull ImmCalendar createdAt();
    @NonNull ImmCalendar lastModifiedAt();
}
