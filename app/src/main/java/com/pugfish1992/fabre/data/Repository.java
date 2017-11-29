package com.pugfish1992.fabre.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pugfish1992.fabre.data.comment.Comments;
import com.pugfish1992.fabre.data.comment.SQLiteComments;

/**
 * Created by daichi on 11/29/17.
 */

public class Repository {

    /* Intentional private visibility */
    private Repository() {}

    public static Comments comments(@NonNull Context context) {
        return new SQLiteComments(context);
    }
}
