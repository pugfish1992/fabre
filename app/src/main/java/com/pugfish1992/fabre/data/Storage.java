package com.pugfish1992.fabre.data;

import android.support.annotation.NonNull;

/**
 * Created by daichi on 11/29/17.
 */

public interface Storage<T extends Record, U extends Diff> {
    @NonNull T load(long id);
    @NonNull T alter(@NonNull T record, @NonNull U diff);
    void delete(@NonNull T record);
}
