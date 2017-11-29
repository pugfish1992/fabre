package com.pugfish1992.fabre.data;

import android.support.annotation.NonNull;

/**
 * Created by daichi on 11/29/17.
 */

public interface Alter<T extends Record> {
    @NonNull public T apply(@NonNull T source);
}
