package com.pugfish1992.fabre.data;

/**
 * Created by daichi on 11/29/17.
 * A class which implements this interface must be immutable.
 */

public interface Record {

    long INVALID_ID = -1;

    long id();
    @Override boolean equals(Object o);
    @Override int hashCode();
}
